package suai.trading.core.service.client.command;

import lombok.Builder;
import lombok.Data;
import suai.trading.core.service.bankaccount.BankAccount;
import suai.trading.core.service.role.ClientRole;

import java.time.LocalDate;

@Data
@Builder
public class CreateClientCommand {
    private final ClientRole role;
    private final String userName;
    private final String password;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final String email;
    private final String phoneNumber;
    private final LocalDate dateOfBirth;
    private final BankAccount bankAccount;
}
