package suai.trading.core.service.client.query;

import suai.trading.core.service.bankaccount.BankAccount;
import suai.trading.core.service.bankaccount.BankAccountView;
import suai.trading.core.service.client.Client;
import suai.trading.core.service.client.ClientView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientQueryService {

    Optional<ClientView> getClientByUserName(String userName);
    Optional<ClientView> getClientById(UUID clientId);
    boolean saveUser(Client client);
    List<ClientView> getAllClients();
    void deleteClient(UUID id);
    boolean updateClient(UUID id, Client updatedClient);
    Optional<BankAccountView> findBankAccountByClientId(UUID clientId);
    void updateBankAccount(UUID clientId, BankAccount updatedBankAccount);
}
