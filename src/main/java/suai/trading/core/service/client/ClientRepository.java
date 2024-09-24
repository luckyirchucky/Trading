package suai.trading.core.service.client;

import suai.trading.core.service.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client> {

    Client findById(UUID id);
    Client findByUserName(String name);
    boolean existsClientByUserName(String name);
    boolean existsClientById(UUID id);
    void deleteClientById(UUID id);
}
