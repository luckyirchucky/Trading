package suai.trading.core.service.coinwallet;

import suai.trading.core.service.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CoinWalletRepository extends JpaRepository<CoinWallet> {

    List<CoinWallet> findCoinWalletsByClientId(UUID client);
    void deleteCoinById(UUID id);
    //void deleteCoin(CoinView coin);
}
