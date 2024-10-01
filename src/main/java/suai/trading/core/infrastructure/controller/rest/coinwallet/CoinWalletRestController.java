package suai.trading.core.infrastructure.controller.rest.coinwallet;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suai.trading.core.security.currentclient.CurrentClient;
import suai.trading.core.service.Id;
import suai.trading.core.service.coinwallet.CoinWalletView;
import suai.trading.core.service.coinwallet.command.CoinWalletCommandService;
import suai.trading.core.service.coinwallet.command.CreateCoinWalletCommand;
import suai.trading.core.service.coinwallet.query.CoinWalletQueryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coinwallet")
public class CoinWalletRestController {

    private final CoinWalletCommandService coinWalletCommandService;
    private final CoinWalletQueryService coinWalletQueryService;
    private final CurrentClient currentClient;

    @PostMapping
    public ResponseEntity<Id> purchaseCoin(@RequestBody CreateCoinWalletCommand command) {
        var coinWalletId = coinWalletCommandService.create(command);
        return ResponseEntity.ok(coinWalletId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoin(@PathVariable("id") String uuid) {
        var id = UUID.fromString(uuid);
        coinWalletQueryService.deleteCoinById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CoinWalletView>> getCoinsWallet() {
        var clientId = currentClient.getId();
        var wallets = coinWalletQueryService.getCoinsByClientId(clientId);
        return ResponseEntity.ok(wallets);
    }
}
