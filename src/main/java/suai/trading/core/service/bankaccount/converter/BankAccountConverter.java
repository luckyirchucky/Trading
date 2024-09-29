package suai.trading.core.service.bankaccount.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import suai.trading.core.service.AbstractEntityConverter;
import suai.trading.core.service.bankaccount.BankAccount;
import suai.trading.core.service.bankaccount.BankAccountView;

@Component
@RequiredArgsConstructor
public class BankAccountConverter extends AbstractEntityConverter<BankAccount, BankAccountView> {

    @Override
    public BankAccountView convertToView(BankAccount entity) {
        return BankAccountView.builder()
                .cardNumber(entity.getCardNumber())
                .cardholderName(entity.getCardholderName())
                .cvv(entity.getCvv())
                .expirationDate(entity.getExpirationDate())
                .money(entity.getMoney())
                .build();
    }
}
