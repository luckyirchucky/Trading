package suai.trading.core.service.client;

import lombok.*;
import suai.trading.core.service.JpaEntity;
import suai.trading.core.service.bankaccount.BankAccount;
import suai.trading.core.service.role.ClientRole;
import suai.trading.core.validation.client.Adult;
import suai.trading.core.validation.client.OnCreate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@AllArgsConstructor
public class Client extends JpaEntity {

    @ManyToOne
    private ClientRole role;

    @NotNull(groups = OnCreate.class)
    private String userName;

    @NotNull
    @Size(min = 1, message = "Фамилия не может быть пустой")
    private String lastName;

    @NotNull
    @Size(min = 1, message = "Имя не может быть пустым")
    private String firstName;

    private String middleName;

    @NotNull
    @Email(message = "Некорректный email")
    private String email;

    @NotNull
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Некорректный номер телефона")
    private String phoneNumber;

    @NotNull
    @Adult
    private LocalDate dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    @NotNull(groups = OnCreate.class)
    @Size(min=5, message = "Не меньше 5 знаков")
    private String password;
}
