package bank;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.Objects;

import com.google.common.base.Strings;

public class Money {
    
    private final int units;
    private final int cents;
    private final Currency currency;

    public Money(int units, int cents, Currency currency) {
        this.units = units;
        this.cents = cents;
        this.currency = currency;
    }

    public Money plus(Money other) {
        return new Money(units + other.units, cents + other.cents, currency);
    }

    public Money negate() {
        return new Money(-units, -cents, currency);
    }
    
    public Currency getCurrency() {
        return currency;
    }

    public boolean hasCurrency(Currency currency) {
        return currency == this.currency;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Money) {
            Money other = (Money) obj;
            return other.units == units 
                    && other.cents == cents
                    && other.currency == currency;
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(units, cents, currency);
    }
    
    @Override
    public String toString() {
        return toStringHelper(this)
                .add("units", units)
                .add("cents", cents)
                .addValue(currency)
                .toString();
    }

    public static Money zero(Currency currency) {
        return new Money(0, 0, currency);
    }

    public String format(int unitPlaces) {
        if(units >= 0) {
            return String.format(" %" + unitPlaces + "d.%02d ", units, cents);
        } else {
            String result = String.format("(%d.%02d)", -units, -cents);
            return Strings.repeat(" ", unitPlaces + 5 - result.length()) + result;
        }
    }
}
