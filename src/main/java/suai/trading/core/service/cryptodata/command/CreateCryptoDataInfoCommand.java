package suai.trading.core.service.cryptodata.command;

import lombok.Builder;
import lombok.Data;
import suai.trading.core.service.cryptodata.model.cryptodata.CryptoData;
import suai.trading.core.service.cryptodata.model.info.Info;

import java.util.List;

@Data
@Builder
public class CreateCryptoDataInfoCommand {

    private final List<CryptoData> cryptoData;
    private final Info info;
}
