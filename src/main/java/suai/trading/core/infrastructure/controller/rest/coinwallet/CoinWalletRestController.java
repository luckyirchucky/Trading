package suai.trading.core.infrastructure.controller.rest.coinwallet;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import suai.trading.core.service.Id;
import suai.trading.core.service.coinwallet.command.CoinWalletCommandService;
import suai.trading.core.service.coinwallet.command.CreateCoinWalletCommand;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coinwallet")
public class CoinWalletRestController {

    private final CoinWalletCommandService coinWalletCommandService;

    @PostMapping("/purchase")
    public ResponseEntity<Id> purchaseCoin(@RequestBody CreateCoinWalletCommand command) {
        var coinWalletId = coinWalletCommandService.create(command);
        return ResponseEntity.ok(coinWalletId);
    }
}
