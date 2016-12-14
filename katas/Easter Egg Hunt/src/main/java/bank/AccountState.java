package bank;

import static com.google.common.base.Preconditions.checkArgument;

public class AccountState {
    private final Currency currency;
    private Money balance;

    public static AccountState withCurrency(Currency currency) {
        return new AccountState(currency);
    }

    private AccountState(Currency currency) {
        this.currency = currency;
        this.balance = Money.zero(currency);
    }

    public Money getBalance() {
        return balance;
    }

    public void apply(Transaction transaction) {
        checkArgument(transaction.hasCurrency(currency),
                "Cannot apply transaction %s to account with currency %s", transaction, currency);
        balance = balance.plus(transaction.getAmount());
    }
}
