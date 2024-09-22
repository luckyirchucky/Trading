package suai.trading.core.service.role;

import suai.trading.core.service.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ClientRoleRepository extends JpaRepository<ClientRole> {

    boolean existsByName(String name);
    ClientRole findByName(String name);
    Optional<ClientRole> findById(UUID id);
}
