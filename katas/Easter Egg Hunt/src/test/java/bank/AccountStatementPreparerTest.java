package bank;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Test;

public class AccountStatementPreparerTest {
    private static final AccountNumber ACCOUNT_NUMBER = AccountNumber.of(12345678);

    private static final Account ACCOUNT = new Account(ACCOUNT_NUMBER, Currency.EUR);

    private final TransactionRepository transactionRepository = new InMemoryTransactionRepository();

    private final AccountStatementPreparer subject = new AccountStatementPreparer(transactionRepository);
    
    @Test
    public void accountStatementPreparerShouldWriteAccountNumber() {
        String result = subject.prepareAccountStatement(ACCOUNT);
        
        assertThat(result, containsString("Account number: 12345678\n"));
    }
    
    @Test
    public void accountStatementPreparerShouldWriteAccountCurrency() {
        String result = subject.prepareAccountStatement(ACCOUNT);
        
        assertThat(result, containsString("Account currency: EUR\n"));
    }
    
    @Test
    public void accountStatementPreparerShouldWriteTableHeader() {
        String result = subject.prepareAccountStatement(ACCOUNT);
        
        assertThat(result, containsString(
                "Date       |    Amount |  Balance\n" +
                "=================================\n"));
    }
    
    @Test
    public void accountStatementPreparerShouldWriteOneTransaction() {
        transactionRepository.persist(new Transaction(ACCOUNT_NUMBER, LocalDate.of(2016, 4, 15), new Money(100, 0, Currency.EUR)));
        
        String result = subject.prepareAccountStatement(ACCOUNT);
        
        assertThat(result, containsString("2016-04-15 |   100.00  |   100.00 \n"));
    }

    @Test
    public void accountStatementPreparerShouldWriteOneNegativeTransaction() {
        transactionRepository.persist(new Transaction(ACCOUNT_NUMBER, LocalDate.of(2016, 4, 15), new Money(-100, 0, Currency.EUR)));
        
        String result = subject.prepareAccountStatement(ACCOUNT);
        
        assertThat(result, containsString("2016-04-15 |  (100.00) |  (100.00)\n"));
    }
    
    @Test
    public void accountStatementPreparerShouldWriteTwoTransactions() {
        transactionRepository.persist(new Transaction(ACCOUNT_NUMBER, LocalDate.of(2016, 4, 15), new Money(100, 0, Currency.EUR)));
        transactionRepository.persist(new Transaction(ACCOUNT_NUMBER, LocalDate.of(2016, 4, 15), new Money(-50, 0, Currency.EUR)));
        
        String result = subject.prepareAccountStatement(ACCOUNT);
        
        assertThat(result, containsString("2016-04-15 |   100.00  |   100.00 \n"));
        assertThat(result, containsString("2016-04-15 |   (50.00) |    50.00 \n"));
    }
}
