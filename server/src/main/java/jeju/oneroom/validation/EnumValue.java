package jeju.oneroom.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
public @interface EnumValue {
    String[] acceptedValues();

    String message() default "Invalid value. Accepted values are {acceptedValues}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
