package suai.trading.core.infrastructure.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import suai.trading.core.service.cryptodata.model.cryptodatainfo.CryptoDataInfoView;
import suai.trading.core.service.cryptodata.query.CryptoDataInfoQueryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tickers")
public class CryptoRestController {

    private final CryptoDataInfoQueryService cryptoDataInfoQueryService;

    @GetMapping
    public CryptoDataInfoView getCryptoData() {
        return cryptoDataInfoQueryService.getCryptoData();
    }
}
