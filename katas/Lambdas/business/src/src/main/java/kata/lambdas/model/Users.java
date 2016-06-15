package kata.lambdas.model;

import static kata.lambdas.model.Actors.*;

import kata.lambdas.domain.User;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Users {
    public static final User JOHN_DOE = new User("John", "Doe");
    public static final User JANE_DOE = new User("Jane", "Doe");

    private static final Set<User> users = new TreeSet<>();

    static {
        JOHN_DOE.likes(BRAD_PITT).likes(GEORGE_CLOONEY);
        JANE_DOE.likes(GEORGE_CLOONEY).likes(HUGH_GRANT).likes(JULIA_ROBERTS);

        users.add(JANE_DOE);
        users.add(JOHN_DOE);
    }

    public static Set<User> list() {
        return Collections.unmodifiableSet(users);
    }
}
