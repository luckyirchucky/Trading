package suai.trading.core.service.client.command;

import lombok.Builder;
import lombok.Data;
import suai.trading.core.service.role.ClientRole;

@Data
@Builder
public class CreateClientCommand {
    private final ClientRole role;
    private final String name;
    private final String password;
}
