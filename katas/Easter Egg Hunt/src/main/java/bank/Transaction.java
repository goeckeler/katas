package bank;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
    
    private final AccountNumber accountNumber;
    private final LocalDate transactionDate;
    private final Money amount;

    public Transaction(AccountNumber accountNumber, LocalDate transactionDate, Money amount) {
        this.accountNumber = accountNumber;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }
    
    public AccountNumber getAccountNumber() {
        return accountNumber;
    }
    
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public Money getAmount() {
        return amount;
    }

    public boolean hasCurrency(Currency currency) {
        return amount.hasCurrency(currency);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Transaction) {
            Transaction other = (Transaction) obj;
            return Objects.equals(accountNumber, other.accountNumber)
                    && Objects.equals(transactionDate, other.transactionDate)
                    && Objects.equals(amount, other.amount);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, transactionDate, amount);
    }
}
