package suai.trading.core.service.client.command;

import suai.trading.core.service.Id;

public interface ClientCommandService {

    Id create(CreateClientCommand command);
}
