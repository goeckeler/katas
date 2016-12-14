package bank;

import static bank.Matchers.accountNumber;
import static bank.Matchers.amount;
import static bank.Matchers.has;
import static bank.Matchers.hasAccountNumber;
import static bank.Matchers.transactionDate;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class BankAccountServiceTest {
    private static final AccountNumber ACCOUNT_NUMBER = AccountNumber.of(100);
    private static final LocalDate TRANSACTION_DATE = LocalDate.of(2016, 4, 15);
    private static final Money DEPOSIT_AMOUNT = new Money(100, 0, Currency.EUR);
    private static final Money WITHDRAW_AMOUNT = new Money(50, 0, Currency.EUR);

    @Spy
    private AccountRepository accountRepository = new InMemoryAccountRepository();

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountStatementPreparer accountStatementPreparer;
    
    @Mock
    private AuditService auditService;

    private BankAccountService subject;
    
    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        subject = new BankAccountService(
                accountRepository, transactionRepository, accountStatementPreparer, auditService);
    }

    @Test
    public void bankAccountServiceShouldCreateAccount() {
        subject.createAccount(ACCOUNT_NUMBER, Currency.EUR);
        
        verify(accountRepository).persist(argThat(hasAccountNumber(ACCOUNT_NUMBER)));
    }
    
    @Test
    public void bankAccountServiceShouldPersistDeposits() {
        subject.createAccount(ACCOUNT_NUMBER, Currency.EUR);
        
        subject.performDeposit(ACCOUNT_NUMBER, TRANSACTION_DATE, DEPOSIT_AMOUNT);
        
        verify(transactionRepository).persist(argThat(has(
                accountNumber(ACCOUNT_NUMBER),
                transactionDate(TRANSACTION_DATE),
                amount(DEPOSIT_AMOUNT))));
    }
    
    @Test
    public void bankAccountServiceShouldRecordTransactionsWithAuditService() {
        subject.createAccount(ACCOUNT_NUMBER, Currency.EUR);
        
        subject.performDeposit(ACCOUNT_NUMBER, TRANSACTION_DATE, DEPOSIT_AMOUNT);
        
        verify(auditService).recordTransaction(new Transaction(ACCOUNT_NUMBER, TRANSACTION_DATE, DEPOSIT_AMOUNT));
    }
    
    @Test
    public void bankAccountServiceShouldPersistWithdraws() {
        subject.createAccount(ACCOUNT_NUMBER, Currency.EUR);
        subject.performDeposit(ACCOUNT_NUMBER, TRANSACTION_DATE.minusDays(1), DEPOSIT_AMOUNT);
        reset(transactionRepository);
        
        subject.performWithdraw(ACCOUNT_NUMBER, TRANSACTION_DATE, WITHDRAW_AMOUNT);
        
        verify(transactionRepository).persist(argThat(has(
                accountNumber(ACCOUNT_NUMBER),
                transactionDate(TRANSACTION_DATE),
                amount(WITHDRAW_AMOUNT.negate()))));
    }
    
    @Test
    public void bankAccountServiceShouldRejectDepositToUnknownAccount() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Unknown account number 12345678 for transaction");
        
        subject.performDeposit(AccountNumber.of(12345678), TRANSACTION_DATE, DEPOSIT_AMOUNT);
    }
    
    @Test
    public void bankAccountServiceShouldRejectDepositWithIncorrectCurrency() {
        subject.createAccount(ACCOUNT_NUMBER, Currency.USD);
        
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Cannot deposit");
        
        subject.performDeposit(ACCOUNT_NUMBER, TRANSACTION_DATE, DEPOSIT_AMOUNT);
    }
    
    @Test
    public void bankAccountServiceShouldPrepareStatement() {
        subject.createAccount(ACCOUNT_NUMBER, Currency.USD);
        when(accountStatementPreparer.prepareAccountStatement(argThat(hasAccountNumber(ACCOUNT_NUMBER))))
            .thenReturn("Statement");
        
        String result = subject.prepareStatement(ACCOUNT_NUMBER);
        
        assertThat(result, is(equalTo("Statement")));
    }
    
    @Test
    public void prepareAccountStatementShouldThrowExceptionOnUnknownAccountNumber() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Unknown account number 12345678");
        
        subject.prepareStatement(AccountNumber.of(12345678));
    }
}
