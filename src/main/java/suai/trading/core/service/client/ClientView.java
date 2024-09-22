package suai.trading.core.service.client;

import lombok.Builder;
import lombok.Data;
import suai.trading.core.service.role.ClientRoleView;

import java.util.UUID;

@Data
@Builder
public class ClientView {
    private final UUID id;
    private final ClientRoleView clientRole;
    private final String username;
    private final String password;
}
