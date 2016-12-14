package bank;

import java.time.LocalDate;

import org.junit.Test;

import com.google.common.testing.EqualsTester;

public class TransactionTest {
    @Test
    public void equalsShouldWork() {
        new EqualsTester()
                .addEqualityGroup(new Transaction(AccountNumber.of(1234), LocalDate.of(2016, 1, 1), new Money(100, 0, Currency.EUR)))
                .addEqualityGroup(new Transaction(AccountNumber.of(5678), LocalDate.of(2016, 1, 1), new Money(100, 0, Currency.EUR)))
                .addEqualityGroup(new Transaction(AccountNumber.of(1234), LocalDate.of(2015, 12, 31), new Money(100, 0, Currency.EUR)))
                .addEqualityGroup(new Transaction(AccountNumber.of(1234), LocalDate.of(2016, 1, 1), new Money(50, 0, Currency.EUR)))
                .testEquals();
    }
}
