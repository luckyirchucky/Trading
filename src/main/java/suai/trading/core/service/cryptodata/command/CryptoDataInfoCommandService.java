package suai.trading.core.service.cryptodata.command;

import suai.trading.core.service.Id;

public interface CryptoDataInfoCommandService {

    Id create(CreateCryptoDataInfoCommand command);
}
