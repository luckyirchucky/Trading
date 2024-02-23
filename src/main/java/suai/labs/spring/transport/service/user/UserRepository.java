package suai.labs.spring.transport.service.user;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private List<User> users;

    public UserRepository() {
        this.users = List.of(
                new User("Irina", "12"),
                new User("Tom", "123")
        );
    }

    public User getByUsername(String username) {
        return this.users.stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst()
                .orElse(null);
    }

    public List<User> getAll() {
        return this.users;
    }
}