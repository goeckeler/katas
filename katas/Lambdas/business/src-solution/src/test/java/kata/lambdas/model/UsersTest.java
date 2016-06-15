package kata.lambdas.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UsersTest {
    @Test
    public void shouldContainUsers() {
        assertTrue(Users.list().stream().allMatch(user -> user.getSurname().equalsIgnoreCase("doe")));
    }
}
