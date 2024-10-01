package suai.trading.core.service.client.query;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suai.trading.core.service.EntityConverter;
import suai.trading.core.service.bankaccount.BankAccount;
import suai.trading.core.service.bankaccount.BankAccountView;
import suai.trading.core.service.client.Client;
import suai.trading.core.service.client.ClientRepository;
import suai.trading.core.service.client.ClientView;
import suai.trading.core.service.client.command.ClientCommandService;
import suai.trading.core.service.client.command.CreateClientCommand;
import suai.trading.core.service.coinwallet.query.CoinWalletQueryService;
import suai.trading.core.service.role.ClientRoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientQueryServiceImpl implements ClientQueryService {

    private final EntityConverter<Client, ClientView> clientConverter;
    private final EntityConverter<BankAccount, BankAccountView> bankAccountConverter;
    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;
    private final ClientRoleRepository clientRoleRepository;
    private final CoinWalletQueryService coinWalletQueryService;
    private final ClientCommandService clientCommandService;

    @Override
    public Optional<ClientView> getClientByUserName(String userName) {
        var client = clientRepository.findByUserName(userName);
        return Optional.ofNullable(clientConverter.convertToView(client));
    }

    @Override
    public Optional<ClientView> getClientById(UUID clientId) {
        var client = clientRepository.findById(clientId);
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
                coinWalletQueryService.deleteCoinsByClientId(id);
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
        return setNewClientData(updatedClient, client);
    }

    private boolean setNewClientData(Client updatedClient, Client client) {
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

    public Optional<BankAccountView> findBankAccountByClientId(UUID clientId) {
        var bankAccount = clientRepository.findById(clientId).getBankAccount();
        return Optional.ofNullable(bankAccountConverter.convertToView(bankAccount));
    }

    public void updateBankAccount(UUID clientId, BankAccount updatedBankAccount) {
        var client = clientRepository.findById(clientId);
        client.setBankAccount(updatedBankAccount);
        clientRepository.save(client);
    }
}
