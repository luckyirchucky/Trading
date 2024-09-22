package suai.trading.core.service.role;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import suai.trading.core.service.JpaEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class ClientRole extends JpaEntity {

    @NotBlank
    private String name;

    @NotNull
    private boolean admin;

    public ClientRole(String name) {
        this.name = name;
        this.admin = false;
    }
}
