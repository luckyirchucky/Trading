package suai.trading.core.service.cryptodata.command;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suai.trading.core.service.Id;
import suai.trading.core.service.cryptodata.model.cryptodata.CryptoData;
import suai.trading.core.service.cryptodata.model.cryptodata.CryptoDataRepository;
import suai.trading.core.service.cryptodata.model.cryptodatainfo.CryptoDataInfo;
import suai.trading.core.service.cryptodata.model.cryptodatainfo.CryptoDataInfoRepository;
import suai.trading.core.service.cryptodata.model.info.Info;
import suai.trading.core.service.cryptodata.model.info.InfoRepository;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CryptoDataInfoCommandServiceImpl implements CryptoDataInfoCommandService {

    private final CryptoDataInfoRepository cryptoDataInfoRepository;
    private final CryptoDataRepository cryptoDataRepository;
    private final InfoRepository infoRepository;

    @Override
    public Id create(CreateCryptoDataInfoCommand command) {
        var info = new Info(command.getInfo().getCoinsCount(), command.getInfo().getTime(), null);
        var cryptoData = command.getCryptoData().stream()
                .map(data -> new CryptoData(
                        data.getIdCryptoData(),
                        data.getSymbol(),
                        data.getName(),
                        data.getNameId(),
                        data.getRank(),
                        data.getPriceUsd(),
                        data.getPercentChange24h(),
                        data.getPercentChange1h(),
                        data.getPercentChange7d(),
                        data.getPriceBtc(),
                        data.getMarketCapUsd(),
                        data.getVolume24(),
                        data.getVolume24a(),
                        data.getCirculatingSupply(),
                        data.getTotalSupply(),
                        data.getMaxSupply(),
                        null
                )).collect(Collectors.toList());
        var cryptoDataInfo = new CryptoDataInfo(cryptoData, info);

        for (CryptoData data : cryptoData) {
            data.setCryptoDataInfo(cryptoDataInfo);
            cryptoDataRepository.save(data);
        }

        info.setCryptoDataInfo(cryptoDataInfo);
        infoRepository.save(info);

        cryptoDataInfoRepository.save(cryptoDataInfo);

        return new Id(cryptoDataInfo.getId());
    }
}
