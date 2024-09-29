package suai.trading.core.service.coinwallet;

import lombok.*;
import suai.trading.core.service.JpaEntity;
import suai.trading.core.service.client.Client;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@AllArgsConstructor
public class CoinWallet extends JpaEntity {

    private String coin;

    private double count;

    private double costPerOneCoin;

    private double totalCost;

    @ManyToOne
    private Client client;
}
