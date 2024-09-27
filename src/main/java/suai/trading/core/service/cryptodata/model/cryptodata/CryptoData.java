package suai.trading.core.service.cryptodata.model.cryptodata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import suai.trading.core.service.JpaEntity;
import suai.trading.core.service.cryptodata.model.cryptodatainfo.CryptoDataInfo;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CryptoData extends JpaEntity {

    @JsonProperty("id")
    private String idCryptoData;

    private String symbol;

    private String name;

    private String nameId;

    private int rank;

    @JsonProperty("price_usd")
    private String priceUsd;

    @JsonProperty("percent_change_24h")
    private String percentChange24h;

    @JsonProperty("percent_change_1h")
    private String percentChange1h;

    @JsonProperty("percent_change_7d")
    private String percentChange7d;

    @JsonProperty("price_btc")
    private String priceBtc;

    @JsonProperty("market_cap_usd")
    private String marketCapUsd;

    private double volume24;

    private double volume24a;

    @JsonProperty("csupply")
    private String circulatingSupply;

    @JsonProperty("tsupply")
    private String totalSupply;

    @JsonProperty("msupply")
    private String maxSupply;

    @ManyToOne
    @JoinColumn(name = "crypto_data_info_id")
    private CryptoDataInfo cryptoDataInfo;
}
