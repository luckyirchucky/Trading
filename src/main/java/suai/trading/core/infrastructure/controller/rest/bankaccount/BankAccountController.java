package suai.trading.core.infrastructure.controller.rest.bankaccount;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suai.trading.core.security.currentclient.CurrentClient;
import suai.trading.core.service.bankaccount.BankAccount;
import suai.trading.core.service.bankaccount.BankAccountView;
import suai.trading.core.service.client.query.ClientQueryService;

import java.util.Optional;

@RestController
@RequestMapping("/bank-account")
@RequiredArgsConstructor
public class BankAccountController {

    private final ClientQueryService clientQueryService;
    private final CurrentClient currentClient;

    @GetMapping
    public ResponseEntity<Optional<BankAccountView>> getBankAccount() {
        var clientId = currentClient.getId();
        var bankAccount = clientQueryService.findBankAccountByClientId(clientId);
        return ResponseEntity.ok(bankAccount);
    }

    @PutMapping
    public ResponseEntity<Void> updateBankAccount(@RequestBody BankAccount updatedBankAccount) {
        var clientId = currentClient.getId();
        clientQueryService.updateBankAccount(clientId, updatedBankAccount);
        return ResponseEntity.noContent().build();
    }
}
