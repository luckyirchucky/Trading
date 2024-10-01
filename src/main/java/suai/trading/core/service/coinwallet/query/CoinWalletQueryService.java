package suai.trading.core.service.coinwallet.query;

import suai.trading.core.service.coinwallet.CoinWalletView;

import java.util.List;
import java.util.UUID;

public interface CoinWalletQueryService {

    List<CoinWalletView> getCoinsByClientId(UUID clientId);
    void deleteCoinsByClientId(UUID clientId);
    void deleteCoinById(UUID id);
}
