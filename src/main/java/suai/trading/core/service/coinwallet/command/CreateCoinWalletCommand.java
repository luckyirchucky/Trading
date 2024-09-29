package suai.trading.core.service.coinwallet.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCoinWalletCommand {

    private final String coin;
    private final double count;
    private final double costPerOneCoin;
    private final double totalCost;
}
