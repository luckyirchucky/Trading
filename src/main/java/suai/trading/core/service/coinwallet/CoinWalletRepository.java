package suai.trading.core.service.coinwallet;

import suai.trading.core.service.JpaRepository;
import suai.trading.core.service.client.Client;

public interface CoinWalletRepository extends JpaRepository<CoinWallet> {
    CoinWallet findCoinWalletByClient(Client client);
}
