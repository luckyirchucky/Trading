package suai.trading.core.service.cryptodata.model.cryptodatainfo;

import lombok.Builder;
import lombok.Data;
import suai.trading.core.service.cryptodata.model.info.Info;
import suai.trading.core.service.cryptodata.model.cryptodata.CryptoData;

import java.util.List;

@Data
@Builder
public class CryptoDataInfoView {

    private final List<CryptoData> cryptoData;
    private final Info info;
}
