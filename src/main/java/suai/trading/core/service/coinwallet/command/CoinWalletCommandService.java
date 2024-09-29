package suai.trading.core.service.coinwallet.command;

import suai.trading.core.service.Id;

public interface CoinWalletCommandService {

    Id create(CreateCoinWalletCommand command);
}
