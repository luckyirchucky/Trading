package suai.trading.core.service.cryptodata.model.info;

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
public class Info extends JpaEntity {

    @JsonProperty("coins_num")
    private int coinsCount;

    private long time;

    @ManyToOne
    @JoinColumn(name = "crypto_data_info_id")
    private CryptoDataInfo cryptoDataInfo;
}
