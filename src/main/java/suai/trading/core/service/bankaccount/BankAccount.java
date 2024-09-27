package suai.trading.core.service.bankaccount;

import lombok.*;
import suai.trading.core.service.JpaEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@AllArgsConstructor
public class BankAccount extends JpaEntity {

    @NotNull
    @Size(min = 16, max = 19)
    @Pattern(regexp = "\\d{16,19}")
    private String cardNumber;

    @NotNull
    private String cardholderName;

    @NotNull
    @Pattern(regexp = "(0[1-9]|1[0-2])/[0-9]{2}")
    private String expirationDate; //MM/YY

    @NotNull
    @Size(min = 3, max = 3)
    private int cvv;
}
