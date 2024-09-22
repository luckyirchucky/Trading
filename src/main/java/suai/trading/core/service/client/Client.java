package suai.trading.core.service.client;

import lombok.*;
import suai.trading.core.service.JpaEntity;
import suai.trading.core.service.role.ClientRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class Client extends JpaEntity {

    @ManyToOne
    private ClientRole role;

    @NotNull
    private String username;

    @NotNull
    @Size(min=5, message = "Не меньше 5 знаков")
    private String password;

    public Client(ClientRole role, String password, String username) {
        this.role = role;
        this.password = password;
        this.username = username;
    }
}
