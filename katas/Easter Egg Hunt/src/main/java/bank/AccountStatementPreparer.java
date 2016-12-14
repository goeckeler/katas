package bank;

public class AccountStatementPreparer {
    
    private final TransactionRepository transactionRepository;

    public AccountStatementPreparer(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public String prepareAccountStatement(Account account) {
        String result = "Account number: " + account.getAccountNumber() + "\n"
                + "Account currency: " + account.getCurrency() + "\n"
                + "Date       |    Amount |  Balance\n"
                + "=================================\n";
        AccountState accountState = account.initialState();
        for (Transaction transaction : transactionRepository.lookupTransactions(account.getAccountNumber())) {
            accountState.apply(transaction);
            result += transaction.getTransactionDate().toString()
                    + " | " + transaction.getAmount().format(4)
                    + " | " + accountState.getBalance().format(4) + "\n";
        }
        return result;
    }
}
