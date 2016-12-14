package bank;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.Test;

public class BankAccountIntegrationTest {
    private static final AccountNumber ACCOUNT_NUMBER = AccountNumber.of(12345678);

    private final AccountRepository accountRepository = new InMemoryAccountRepository();
    private final TransactionRepository transactionRepository = new InMemoryTransactionRepository();
    private final AccountStatementPreparer accountStatementPreparer = new AccountStatementPreparer(transactionRepository);
    private final AuditService auditService = mock(AuditService.class);
    private final BankAccountService subject = new BankAccountService(
            accountRepository, transactionRepository, accountStatementPreparer, auditService);

    @Test
    public void bankAccountServiceShouldAllowTransactionsAndPrintStatement() {
        subject.createAccount(ACCOUNT_NUMBER, Currency.EUR);
        subject.performDeposit(ACCOUNT_NUMBER, LocalDate.of(2016, 4, 15), new Money(100, 0, Currency.EUR));
        subject.performWithdraw(ACCOUNT_NUMBER, LocalDate.of(2016, 4, 17), new Money(50, 0, Currency.EUR));

        String result = subject.prepareStatement(ACCOUNT_NUMBER);

        assertThat(result, is(equalTo(
                "Account number: 12345678\n" +
                "Account currency: EUR\n" +
                "Date       |    Amount |  Balance\n" +
                "=================================\n" +
                "2016-04-15 |   100.00  |   100.00 \n" + 
                "2016-04-17 |   (50.00) |    50.00 \n")));
    }
}
