package suai.trading.core.service.coinwallet.command;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suai.trading.core.security.currentclient.CurrentClient;
import suai.trading.core.service.Id;
import suai.trading.core.service.client.ClientRepository;
import suai.trading.core.service.coinwallet.CoinWallet;
import suai.trading.core.service.coinwallet.CoinWalletRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CoinWalletWalletCommandServiceImpl implements CoinWalletCommandService {

    private final CoinWalletRepository coinWalletRepository;
    private final CurrentClient currentClient;
    private final ClientRepository clientRepository;

    @Override
    public Id create(CreateCoinWalletCommand command) {
        var client = clientRepository.findById(currentClient.getId());
        var coinWallet = new CoinWallet(command.getCoin(), command.getCount(),
                command.getCostPerOneCoin(), command.getTotalCost(), client);
        coinWalletRepository.save(coinWallet);
        return new Id(coinWallet.getId());
    }
}
