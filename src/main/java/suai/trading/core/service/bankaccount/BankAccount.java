package suai.trading.core.service.bankaccount;

import lombok.*;
import suai.trading.core.service.JpaEntity;
import suai.trading.core.service.client.Client;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
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

    @Size(min = 3, max = 3)
    private int cvv;

    private double money;

    @OneToOne(mappedBy = "bankAccount")
    private Client client;

    public BankAccount(String cardNumber, String cardholderName, String expirationDate,
                       int cvv, Client client) {
        this.money = 10000;
        this.cardholderName = cardholderName;
        this.cvv = cvv;
        this.client = client;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }

    public BankAccount(String cardNumber, String cardholderName, String expirationDate, int cvv) {
        this(cardNumber, cardholderName, expirationDate, cvv, null);
        this.money = 10000;
    }
}
