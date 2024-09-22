package suai.trading.core.service.role;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ClientRoleView {
    private final UUID id;
    private final String name;
    private final boolean admin;
}
