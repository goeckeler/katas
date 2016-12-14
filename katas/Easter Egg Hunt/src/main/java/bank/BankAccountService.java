package bank;

import static com.google.common.base.Preconditions.checkArgument;

import java.time.LocalDate;
import java.util.Optional;

public class BankAccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AccountStatementPreparer accountStatementPreparer;
    private final AuditService auditService;

    public BankAccountService(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository,
            AccountStatementPreparer accountStatementPreparer,
            AuditService auditService) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.accountStatementPreparer = accountStatementPreparer;
        this.auditService = auditService;
    }

    public void createAccount(AccountNumber accountNumber, Currency accountCurrency) {
        Account account = new Account(accountNumber, accountCurrency);
        accountRepository.persist(account);
    }

    public void performDeposit(AccountNumber accountNumber, LocalDate transactionDate, Money money) {
        validateTransaction(accountNumber, money);
        Transaction transaction = new Transaction(accountNumber, transactionDate, money);
        transactionRepository.persist(transaction);
        auditService.recordTransaction(transaction);
    }

    public void performWithdraw(AccountNumber accountNumber, LocalDate transactionDate, Money money) {
        validateTransaction(accountNumber, money);
        Transaction transaction = new Transaction(accountNumber, transactionDate, money.negate());
        transactionRepository.persist(transaction);
        auditService.recordTransaction(transaction);
    }

    private void validateTransaction(AccountNumber accountNumber, Money money) {
        Optional<Account> account = accountRepository.lookup(accountNumber);
        checkArgument(account.isPresent(), "Unknown account number %s for transaction", accountNumber);
        checkArgument(money.hasCurrency(account.get().getCurrency()),
                "Cannot deposit %s to account %s due to incorrect currency",
                money, accountNumber);
    }

    public String prepareStatement(AccountNumber accountNumber) {
        Optional<Account> account = accountRepository.lookup(accountNumber);
        checkArgument(account.isPresent(), "Unknown account number %s", accountNumber);
        return accountStatementPreparer.prepareAccountStatement(account.get());
    }
}
