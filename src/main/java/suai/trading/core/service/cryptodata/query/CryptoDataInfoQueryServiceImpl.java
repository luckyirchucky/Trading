package suai.trading.core.service.cryptodata.query;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;
import suai.trading.core.service.EntityConverter;
import suai.trading.core.service.cryptodata.command.CreateCryptoDataInfoCommand;
import suai.trading.core.service.cryptodata.command.CryptoDataInfoCommandService;
import suai.trading.core.service.cryptodata.model.cryptodatainfo.CryptoDataInfo;
import suai.trading.core.service.cryptodata.model.cryptodatainfo.CryptoDataInfoRepository;
import suai.trading.core.service.cryptodata.model.cryptodatainfo.CryptoDataInfoView;
import suai.trading.core.service.cryptodata.service.CryptoDataInfoService;

@Service
@RequiredArgsConstructor
public class CryptoDataInfoQueryServiceImpl implements CryptoDataInfoQueryService {

    private final CryptoDataInfoService cryptoDataInfoService;
    private final EntityConverter<CryptoDataInfo, CryptoDataInfoView> cryptoDataInfoConverter;
    private final CryptoDataInfoCommandService cryptoDataInfoCommandService;
    private final CryptoDataInfoRepository cryptoDataInfoRepository;

    @Override
    public CryptoDataInfoView getCryptoData() {
        var cryptoDataInfo = cryptoDataInfoService.getCryptoData();
        if (cryptoDataInfoRepository.count() == 0) {
            var command = CreateCryptoDataInfoCommand.builder()
                    .cryptoData(cryptoDataInfo.getCryptoData())
                    .info(cryptoDataInfo.getInfo())
                    .build();
            cryptoDataInfoCommandService.create(command);
        }
        return cryptoDataInfoConverter.convertToView(cryptoDataInfo);
    }
}
