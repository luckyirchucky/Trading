package suai.trading.core.service.client.query;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suai.trading.core.service.EntityConverter;
import suai.trading.core.service.client.Client;
import suai.trading.core.service.client.ClientRepository;
import suai.trading.core.service.client.ClientView;
import suai.trading.core.service.client.command.ClientCommandService;
import suai.trading.core.service.client.command.CreateClientCommand;
import suai.trading.core.service.coinwallet.CoinWalletRepository;
import suai.trading.core.service.role.ClientRoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientQueryServiceImpl implements ClientQueryService {

    private final ClientRepository clientRepository;
    private final EntityConverter<Client, ClientView> clientConverter;
    private final PasswordEncoder passwordEncoder;
    private final ClientRoleRepository clientRoleRepository;
    private final CoinWalletRepository coinWalletRepository;
    private final ClientCommandService clientCommandService;

    @Override
    public Optional<ClientView> getClientByUserName(String userName) {
        var client = clientRepository.findByUserName(userName);
        return Optional.ofNullable(clientConverter.convertToView(client));
    }

    @Override
    public boolean saveUser(Client client) {
        if (isClientExistByUserName(client.getUserName())) {
            return false;
        }
        var commandClient = CreateClientCommand.builder()
                .userName(client.getUserName())
                .password(passwordEncoder.encode(client.getPassword()))
                .role(clientRoleRepository.findByName("Пользователь"))
                .firstName(client.getFirstName())
                .middleName(client.getMiddleName())
                .lastName(client.getLastName())
                .phoneNumber(client.getPhoneNumber())
                .email(client.getEmail())
                .bankAccount(client.getBankAccount())
                .dateOfBirth(client.getDateOfBirth())
                .build();
        try {
            clientCommandService.create(commandClient);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ClientView> getAllClients() {
        var clients = clientRepository.findAll();
        return clientConverter.convertToView(clients);
    }

    @Transactional
    @Override
    public void deleteClient(UUID id) {
        if (isClientExistById(id)) {
            var client = findClient(id);
            if (!client.getRole().getName().equals("Администратор")) {
                var wallet = coinWalletRepository.findCoinWalletByClient(client);
                coinWalletRepository.delete(wallet);
                clientRepository.deleteClientById(id);
            }
        }
    }

    @Transactional
    @Override
    public boolean updateClient(UUID id, Client updatedClient) {
        if (!isClientExistById(id)) {
            return false;
        }
        var client = findClient(id);
        return setNewClientData(updatedClient, client, clientRepository);
    }

    private boolean setNewClientData(Client updatedClient, Client client, ClientRepository clientRepository) {
        client.setFirstName(updatedClient.getFirstName());
        client.setMiddleName(updatedClient.getMiddleName());
        client.setLastName(updatedClient.getLastName());
        client.setPhoneNumber(updatedClient.getPhoneNumber());
        client.setEmail(updatedClient.getEmail());
        client.setDateOfBirth(updatedClient.getDateOfBirth());
        clientRepository.save(client);
        return true;
    }

    private boolean isClientExistById(UUID id) {
        return clientRepository.existsClientById(id);
    }

    private boolean isClientExistByUserName(String userName) {
        return clientRepository.existsClientByUserName(userName);
    }

    private Client findClient(UUID id) {
        return clientRepository.findById(id);
    }
}
