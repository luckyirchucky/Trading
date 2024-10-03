package suai.trading.core.security.currentclient;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import suai.trading.core.infrastructure.exception.UnauthorizedException;
import suai.trading.core.service.client.Client;
import suai.trading.core.service.client.ClientRepository;

import java.util.Optional;
import java.util.UUID;

@Component("currentClient")
@RequiredArgsConstructor
public class CurrentClientImpl implements CurrentClient {

    private final ClientRepository clientRepository;

    @Override
    public UUID getId() {
        return getCurrentClient().map(Client::getId)
                .orElseThrow(() -> new UnauthorizedException("Требуется аутентификация"));
    }

    private Optional<Client> getCurrentClient() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            var username = authentication.getName();
            return Optional.ofNullable(clientRepository.findByUserName(username));
        }
        return Optional.empty();
    }
}
