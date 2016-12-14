package bank;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryAccountRepository implements AccountRepository {
    
    private final Map<AccountNumber, Account> persistedAccounts = new HashMap<AccountNumber, Account>();

    public void persist(Account account) {
        persistedAccounts.put(account.getAccountNumber(), account);
    }

    public Optional<Account> lookup(AccountNumber accountNumber) {
        if(persistedAccounts.containsKey(accountNumber)) {
            return Optional.of(persistedAccounts.get(accountNumber));
        } else {
            return Optional.empty();
        }
    }
}
