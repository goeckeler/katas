package bank;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public final class Matchers {
    
    private Matchers() {}

    public static Matcher<Account> hasAccountNumber(AccountNumber accountNumber) {
        return new FeatureMatcher<Account, AccountNumber>(equalTo(accountNumber), "accountNumber", "accountNumber") {
    
            @Override
            protected AccountNumber featureValueOf(Account actual) {
                return actual.getAccountNumber();
            }
        };
    }

    @SafeVarargs
    public static <T> Matcher<T> has(Matcher<? super T>... matchers) {
        return allOf(matchers);
    }

    static Matcher<Transaction> amount(Money money) {
        return new FeatureMatcher<Transaction, Money>(equalTo(money), "amount", "amount") {
    
            @Override
            protected Money featureValueOf(Transaction actual) {
                return actual.getAmount();
            }
        };
    }

    public static Matcher<Transaction> accountNumber(AccountNumber accountNumber) {
        return new FeatureMatcher<Transaction, AccountNumber>(equalTo(accountNumber), "accountNumber", "accountNumber") {
    
            @Override
            protected AccountNumber featureValueOf(Transaction actual) {
                return actual.getAccountNumber();
            }
        };
    }

    public static Matcher<Transaction> transactionDate(LocalDate transactionDate) {
        return new FeatureMatcher<Transaction, LocalDate>(equalTo(transactionDate), "transactionDate", "transactionDate") {
    
            @Override
            protected LocalDate featureValueOf(Transaction actual) {
                return actual.getTransactionDate();
            }
        };
    }

    public static Matcher<AccountState> hasBalance(Money money) {
        return new FeatureMatcher<AccountState, Money>(equalTo(money), "balance", "balance") {
    
            @Override
            protected Money featureValueOf(AccountState actual) {
                return actual.getBalance();
            }
        };
    }
}
