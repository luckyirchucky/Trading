package suai.trading.core.service.cryptodata.converter;

import org.springframework.stereotype.Component;
import suai.trading.core.service.AbstractEntityConverter;
import suai.trading.core.service.cryptodata.model.cryptodatainfo.CryptoDataInfo;
import suai.trading.core.service.cryptodata.model.cryptodatainfo.CryptoDataInfoView;

@Component
public class CryptoDataConverter extends AbstractEntityConverter<CryptoDataInfo, CryptoDataInfoView> {

    @Override
    public CryptoDataInfoView convertToView(CryptoDataInfo entity) {
        return CryptoDataInfoView.builder()
                .cryptoData(entity.getCryptoData())
                .info(entity.getInfo())
                .build();
    }
}
