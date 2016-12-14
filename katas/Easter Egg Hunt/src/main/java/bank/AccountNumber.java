package bank;

public class AccountNumber {
    private final int value;

    public static AccountNumber of(int value) {
        return new AccountNumber(value);
    }

    private AccountNumber(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
