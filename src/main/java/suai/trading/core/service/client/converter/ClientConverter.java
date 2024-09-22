package suai.trading.core.service.client.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import suai.trading.core.service.AbstractEntityConverter;
import suai.trading.core.service.EntityConverter;
import suai.trading.core.service.client.Client;
import suai.trading.core.service.client.ClientView;
import suai.trading.core.service.role.ClientRole;
import suai.trading.core.service.role.ClientRoleView;

@Component
@RequiredArgsConstructor
public class ClientConverter extends AbstractEntityConverter<Client, ClientView> {

    private final EntityConverter<ClientRole, ClientRoleView> clientRoleEntityConverter;

    @Override
    public ClientView convertToView(Client entity) {
        return ClientView.builder()
                .id(entity.getId())
                .clientRole(clientRoleEntityConverter.convertToView(entity.getRole()))
                .username(entity.getUsername())
                .password(entity.getPassword())
                .build();
    }
}
