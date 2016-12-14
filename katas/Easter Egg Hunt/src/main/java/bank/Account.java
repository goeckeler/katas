package bank;

public class Account {
    
    private final AccountNumber accountNumber;
    private final Currency currency;

    public Account(AccountNumber accountNumber, Currency currency) {
        this.accountNumber = accountNumber;
        this.currency = currency;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }
    
    public Currency getCurrency() {
        return currency;
    }

    public AccountState initialState() {
        return AccountState.withCurrency(currency);
    }
}
