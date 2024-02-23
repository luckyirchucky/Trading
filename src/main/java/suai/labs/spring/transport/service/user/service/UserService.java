package suai.labs.spring.transport.service.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import suai.labs.spring.transport.service.user.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAll();

    User getByUsername(String username);

    @Override
    UserDetails loadUserByUsername(String username);
}
