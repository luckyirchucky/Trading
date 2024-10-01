package suai.trading.core.service.coinwallet.query;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suai.trading.core.service.EntityConverter;
import suai.trading.core.service.coinwallet.CoinWallet;
import suai.trading.core.service.coinwallet.CoinWalletRepository;
import suai.trading.core.service.coinwallet.CoinWalletView;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CoinWalletQueryServiceImpl implements CoinWalletQueryService {

    private final CoinWalletRepository coinWalletRepository;
    private final EntityConverter<CoinWallet, CoinWalletView> coinWalletConverter;

    @Override
    public List<CoinWalletView> getCoinsByClientId(UUID clientId) {
        var coins = coinWalletRepository.findCoinWalletsByClientId(clientId);
        return coinWalletConverter.convertToView(coins);
    }

    @Override
    public void deleteCoinsByClientId(UUID clientId) {
        var coins = coinWalletRepository.findCoinWalletsByClientId(clientId);
        coinWalletRepository.deleteAll(coins);
    }

    @Override
    @Transactional
    public void deleteCoinById(UUID id) {
        coinWalletRepository.deleteCoinById(id);
    }
}
