package bank;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

public class InMemoryTransactionRepository implements TransactionRepository {
    
    private final Map<AccountNumber, List<Transaction>> persistedTransactions = new HashMap<>();

    public void persist(Transaction transaction) {
        if(!persistedTransactions.containsKey(transaction.getAccountNumber())) {
            persistedTransactions.put(transaction.getAccountNumber(), new ArrayList<>());
        }
        
        List<Transaction> transactions = persistedTransactions.get(transaction.getAccountNumber());
        transactions.add(transaction);
    }

    public List<Transaction> lookupTransactions(AccountNumber accountNumber) {
        if(persistedTransactions.containsKey(accountNumber)) {
            return ImmutableList.copyOf(persistedTransactions.get(accountNumber));
        } else {
            return emptyList();
        }
    }
}
