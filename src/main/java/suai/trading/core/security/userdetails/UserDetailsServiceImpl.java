package suai.trading.core.security.userdetails;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.var;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import suai.trading.core.service.client.query.ClientQueryService;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClientQueryService clientQueryService;

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String userName) {
        var client = clientQueryService.getClientByUserName(userName);
        if (!client.isPresent()) {
            throw new UsernameNotFoundException("Не найден пользователь с никнеймом: " + userName);
        }
        return User.builder()
                .username(client.get().getUsername())
                .password(client.get().getPassword())
                .roles(client.get().getClientRole().getName())
                .build();
    }
}
