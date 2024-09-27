package suai.trading.core.service.client;

import lombok.Builder;
import lombok.Data;
import suai.trading.core.service.bankaccount.BankAccountView;
import suai.trading.core.service.role.ClientRoleView;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class ClientView {
    private final UUID id;
    private final ClientRoleView clientRole;
    private final String username;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final String email;
    private final String phoneNumber;
    private final LocalDate dateOfBirth;
    private final BankAccountView bankAccount;
    private final String password;
}
