package suai.trading.core.validation.client;

import lombok.var;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class AdultValidator implements ConstraintValidator<Adult, LocalDate> {

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null) {
            return true;
        }
        var age = Period.between(dateOfBirth, LocalDate.now()).getYears();
        return age >= 18;
    }
}
