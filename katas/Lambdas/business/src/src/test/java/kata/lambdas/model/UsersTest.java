package kata.lambdas.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UsersTest {
    @Test
    public void shouldContainUsers() {
        assertTrue(Users.list().stream().allMatch(user -> user.getSurname().equalsIgnoreCase("doe")));
    }
}
