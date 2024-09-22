package suai.trading.core.service.client.query;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suai.trading.core.service.EntityConverter;
import suai.trading.core.service.client.Client;
import suai.trading.core.service.client.ClientRepository;
import suai.trading.core.service.client.ClientView;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientQueryServiceImpl implements ClientQueryService {

    private final ClientRepository clientRepository;
    private final EntityConverter<Client, ClientView> clientConverter;

    @Override
    public Optional<ClientView> getClientByName(String name) {
        var client = clientRepository.findByUsername(name);
        return Optional.ofNullable(clientConverter.convertToView(client));
    }
}
