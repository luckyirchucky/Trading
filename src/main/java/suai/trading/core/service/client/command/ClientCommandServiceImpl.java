package suai.trading.core.service.client.command;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suai.trading.core.infrastructure.exception.UserException;
import suai.trading.core.service.Id;
import suai.trading.core.service.bankaccount.command.BankAccountCommandService;
import suai.trading.core.service.bankaccount.command.CreateBankAccountCommand;
import suai.trading.core.service.client.Client;
import suai.trading.core.service.client.ClientRepository;
import suai.trading.core.service.role.ClientRoleRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientCommandServiceImpl implements ClientCommandService {

    private final ClientRepository clientRepository;
    private final ClientRoleRepository clientRoleRepository;
    private final BankAccountCommandService bankAccountCommandService;

    @Override
    public Id create(CreateClientCommand command) {
        var role = clientRoleRepository.findById(command.getRole().getId())
                .orElseThrow(() -> new UserException("Роль не найдена"));
        var commandBankAccount = CreateBankAccountCommand.builder()
                .cvv(command.getBankAccount().getCvv())
                .cardholderName(command.getBankAccount().getCardholderName())
                .cardNumber(command.getBankAccount().getCardNumber())
                .expirationDate(command.getBankAccount().getExpirationDate())
                .build();
        bankAccountCommandService.create(commandBankAccount);
        var client = new Client(role, command.getUserName(), command.getLastName(), command.getFirstName(), command.getMiddleName(),
                command.getEmail(), command.getPhoneNumber(), command.getDateOfBirth(), command.getBankAccount(), command.getPassword());
        clientRepository.save(client);
        return new Id(client.getId());
    }
}
