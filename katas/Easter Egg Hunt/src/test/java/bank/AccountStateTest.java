package bank;

import static bank.Matchers.hasBalance;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AccountStateTest {
    private static final LocalDate TRANSACTION_DATE = LocalDate.of(2016, 1, 1);
    private static final AccountNumber ACCOUNT_NUMBER = AccountNumber.of(100);
    private static final Money TRANSACTION_AMOUNT = new Money(100, 0, Currency.EUR);
    
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void initialAccountStateShouldHaveBalanceZero() {
        assertThat(AccountState.withCurrency(Currency.EUR), hasBalance(Money.zero(Currency.EUR)));
    }
    
    @Test
    public void accountStateShouldHaveBalanceFromTransactionAfterApplyingIt() {
        AccountState accountState = AccountState.withCurrency(Currency.EUR);
        
        accountState.apply(new Transaction(ACCOUNT_NUMBER, TRANSACTION_DATE, TRANSACTION_AMOUNT));
        
        assertThat(accountState, hasBalance(TRANSACTION_AMOUNT));
    }
    
    @Test
    public void accountStateShouldThrowExceptionWhenApplyingTransactionWithWrongCurrency() {
        AccountState accountState = AccountState.withCurrency(Currency.USD);
        
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Cannot apply transaction");
        
        accountState.apply(new Transaction(ACCOUNT_NUMBER, TRANSACTION_DATE, TRANSACTION_AMOUNT));
    }
}
