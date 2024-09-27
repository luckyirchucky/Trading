package suai.trading.core.service.cryptodata.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import suai.trading.core.service.cryptodata.model.cryptodatainfo.CryptoDataInfo;

@FeignClient(value = "globalCrypto", url = "${coinlore.url}")
public interface CryptoDataInfoService {

    @GetMapping("/api/tickers/")
    CryptoDataInfo getCryptoData();
}
