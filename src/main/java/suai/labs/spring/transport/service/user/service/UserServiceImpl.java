package suai.labs.spring.transport.service.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import suai.labs.spring.transport.service.user.User;
import suai.labs.spring.transport.service.user.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return this.repository.getAll();
    }

    public User getByUsername(String username) {
        return this.repository.getByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = getByUsername(username);
        if (Objects.isNull(u)) {
            throw new UsernameNotFoundException(String.format("User %s is not found", username));
        }
        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), true,
                true, true, true, new HashSet<>());
    }
}
