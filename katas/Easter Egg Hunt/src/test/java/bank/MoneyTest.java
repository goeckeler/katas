package bank;

import org.junit.Test;

import com.google.common.testing.EqualsTester;

public class MoneyTest {
    @Test
    public void equalsShouldWork() {
        new EqualsTester()
                .addEqualityGroup(new Money(100, 0, Currency.EUR))
                .addEqualityGroup(new Money(101, 0, Currency.EUR))
                .addEqualityGroup(new Money(100, 1, Currency.EUR))
                .addEqualityGroup(new Money(100, 0, Currency.USD))
                .testEquals();
    }
}
