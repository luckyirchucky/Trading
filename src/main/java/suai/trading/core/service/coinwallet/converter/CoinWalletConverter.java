package suai.trading.core.service.coinwallet.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import suai.trading.core.service.AbstractEntityConverter;
import suai.trading.core.service.EntityConverter;
import suai.trading.core.service.client.Client;
import suai.trading.core.service.client.ClientView;
import suai.trading.core.service.coinwallet.CoinWallet;
import suai.trading.core.service.coinwallet.CoinWalletView;

@Component
@RequiredArgsConstructor
public class CoinWalletConverter extends AbstractEntityConverter<CoinWallet, CoinWalletView> {

    private final EntityConverter<Client, ClientView> clientConverter;

    @Override
    public CoinWalletView convertToView(CoinWallet entity) {
        return CoinWalletView.builder()
                .coin(entity.getCoin())
                .count(entity.getCount())
                .costPerOneCoin(entity.getCostPerOneCoin())
                .totalCost(entity.getTotalCost())
                .clientView(clientConverter.convertToView(entity.getClient()))
                .build();
    }
}
