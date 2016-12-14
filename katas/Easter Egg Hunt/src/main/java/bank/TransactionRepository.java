package bank;

import java.util.List;

public interface TransactionRepository {

    void persist(Transaction transaction);

    List<Transaction> lookupTransactions(AccountNumber accountNumber);

}
