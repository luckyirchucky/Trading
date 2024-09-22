package suai.trading.core.security.userdetails;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.var;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suai.trading.core.service.client.Client;
import suai.trading.core.service.client.ClientRepository;
import suai.trading.core.service.client.ClientView;
import suai.trading.core.service.client.command.ClientCommandService;
import suai.trading.core.service.client.command.CreateClientCommand;
import suai.trading.core.service.client.converter.ClientConverter;
import suai.trading.core.service.client.query.ClientQueryService;
import suai.trading.core.service.role.ClientRoleRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClientQueryService clientQueryService;
    private final ClientCommandService clientCommandService;
    private final ClientConverter clientConverter;
    private final ClientRoleRepository clientRoleRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        var client = clientQueryService.getClientByName(username);
        if (!client.isPresent()) {
            throw new UsernameNotFoundException("Не найден пользователь с именем: " + username);
        }
        return User.builder()
                .username(client.get().getUsername())
                .password(client.get().getPassword())
                .roles(client.get().getClientRole().getName())
                .build();
    }

    public boolean saveUser(Client client) {
        if (clientRepository.existsClientByUsername(client.getUsername())) {
            return false;
        }
        var command = CreateClientCommand.builder()
                .name(client.getUsername())
                .password(passwordEncoder.encode(client.getPassword()))
                .role(clientRoleRepository.findByName("Пользователь"))
                .build();
        try {
            clientCommandService.create(command);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ClientView> getAllUsers(){
        var clients = clientRepository.findAll();
        return clientConverter.convertToView(clients);
    }

    @Transactional
    public void deleteUser(UUID id) {
        if (clientRepository.existsClientById(id)) {
            var client = clientRepository.findById(id);
            if (!client.getRole().getName().equals("Администратор")) {
                clientRepository.deleteClientById(id);
            }
        }
    }
}
