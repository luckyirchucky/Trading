package suai.trading.core.service.role.converter;

import org.springframework.stereotype.Component;
import suai.trading.core.service.role.ClientRole;
import suai.trading.core.service.role.ClientRoleView;
import suai.trading.core.service.AbstractEntityConverter;

@Component
public class ClientRoleConverter extends AbstractEntityConverter<ClientRole, ClientRoleView> {

    @Override
    public ClientRoleView convertToView(ClientRole entity) {
        return ClientRoleView.builder()
                .id(entity.getId())
                .name(entity.getName())
                .admin(entity.isAdmin())
                .build();
    }
}
