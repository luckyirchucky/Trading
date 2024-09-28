package suai.trading.core.service.cryptodata.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import suai.trading.core.service.AbstractEntityConverter;
import suai.trading.core.service.cryptodata.model.cryptodatainfo.CryptoDataInfo;
import suai.trading.core.service.cryptodata.model.cryptodatainfo.CryptoDataInfoView;

@Component
@RequiredArgsConstructor
public class CryptoDataConverter extends AbstractEntityConverter<CryptoDataInfo, CryptoDataInfoView> {

    @Override
    public CryptoDataInfoView convertToView(CryptoDataInfo entity) {
        return CryptoDataInfoView.builder()
                .cryptoData(entity.getCryptoData())
                .infoData(entity.getInfo())
                .build();
    }
}
