package suai.trading.core.service.coinwallet;

import lombok.Builder;
import lombok.Data;
import suai.trading.core.service.client.ClientView;

@Data
@Builder
public class CoinWalletView {

    private final String coin;
    private final double count;
    private final double costPerOneCoin;
    private final double totalCost;
    private final ClientView clientView;
}
