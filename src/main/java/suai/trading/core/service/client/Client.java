package suai.trading.core.service.client;

import lombok.*;
import suai.trading.core.service.JpaEntity;
import suai.trading.core.service.role.ClientRole;
import suai.trading.core.validation.client.Adult;
import suai.trading.core.validation.client.OnCreate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@AllArgsConstructor
public class Client extends JpaEntity {

    @ManyToOne
    private ClientRole role;

    @NotNull
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

    @NotNull
    @Size(min = 10, message = "Данные банковского счета не могут быть меньше 10 знаков")
    private String bankAccount;

    @NotNull(groups = OnCreate.class)
    @Size(min=5, message = "Не меньше 5 знаков")
    private String password;
}
