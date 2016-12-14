package bank;

import java.util.Optional;

public interface AccountRepository {

    void persist(Account account);

    Optional<Account> lookup(AccountNumber accountNumber);

}
