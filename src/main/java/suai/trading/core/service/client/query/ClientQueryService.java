package suai.trading.core.service.client.query;

import suai.trading.core.service.client.ClientView;

import java.util.Optional;

public interface ClientQueryService {

    Optional<ClientView> getClientByName(String name);
}
