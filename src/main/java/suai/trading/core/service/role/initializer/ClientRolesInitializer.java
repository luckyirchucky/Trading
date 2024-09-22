package suai.trading.core.service.role.initializer;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suai.trading.core.service.role.ClientRole;
import suai.trading.core.service.role.ClientRoleRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientRolesInitializer {

    private final ClientRoleRepository clientRoleRepository;

    public void initialize() {
        var clientRoles = readClientRoles();
        clientRoles.forEach(this::addIfNotExists);
    }

    @SneakyThrows
    private List<ClientRole> readClientRoles() {
        var inputStream = getClass().getResourceAsStream("/data/roles.txt");
        try (var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines()
                    .filter(line -> !line.isEmpty())
                    .map(ClientRole::new)
                    .collect(Collectors.toList());
        }
    }

    private void addIfNotExists(ClientRole clientRole) {
        var exists = clientRoleRepository.existsByName(clientRole.getName());
        if (!exists) {
            if (clientRole.getName().equals("Администратор")) {
                clientRole.setAdmin(true);
            }
            clientRoleRepository.save(clientRole);
        }
    }
}
