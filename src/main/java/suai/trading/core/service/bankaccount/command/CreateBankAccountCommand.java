package suai.trading.core.service.bankaccount.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateBankAccountCommand {

    private final String cardNumber;
    private final String cardholderName;
    private final String expirationDate;
    private final int cvv;
}
