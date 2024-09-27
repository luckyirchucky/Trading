package suai.trading.core.service.client.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import suai.trading.core.service.AbstractEntityConverter;
import suai.trading.core.service.EntityConverter;
import suai.trading.core.service.bankaccount.BankAccount;
import suai.trading.core.service.bankaccount.BankAccountView;
import suai.trading.core.service.client.Client;
import suai.trading.core.service.client.ClientView;
import suai.trading.core.service.role.ClientRole;
import suai.trading.core.service.role.ClientRoleView;

@Component
@RequiredArgsConstructor
public class ClientConverter extends AbstractEntityConverter<Client, ClientView> {

    private final EntityConverter<ClientRole, ClientRoleView> clientRoleConverter;
    private final EntityConverter<BankAccount, BankAccountView> bankAccountConverter;

    @Override
    public ClientView convertToView(Client entity) {
        return ClientView.builder()
                .id(entity.getId())
                .clientRole(clientRoleConverter.convertToView(entity.getRole()))
                .username(entity.getUserName())
                .password(entity.getPassword())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .lastName(entity.getLastName())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .dateOfBirth(entity.getDateOfBirth())
                .bankAccount(bankAccountConverter.convertToView(entity.getBankAccount()))
                .build();
    }
}
