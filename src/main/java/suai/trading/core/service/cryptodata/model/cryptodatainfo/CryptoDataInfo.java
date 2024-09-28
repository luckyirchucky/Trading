package suai.trading.core.service.cryptodata.model.cryptodatainfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import suai.trading.core.service.JpaEntity;
import suai.trading.core.service.cryptodata.model.cryptodata.CryptoData;
import suai.trading.core.service.cryptodata.model.info.Info;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CryptoDataInfo extends JpaEntity {

    @JsonProperty("data")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "crypto_data_id")
    private List<CryptoData> cryptoData;

    @OneToOne
    @JoinColumn(name = "info_id")
    private Info info;
}
