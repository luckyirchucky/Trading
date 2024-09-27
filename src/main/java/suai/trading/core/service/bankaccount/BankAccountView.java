package suai.trading.core.service.bankaccount;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankAccountView {

    private final String cardNumber;
    private final String cardholderName;
    private final String expirationDate;
    private final int cvv;
}
