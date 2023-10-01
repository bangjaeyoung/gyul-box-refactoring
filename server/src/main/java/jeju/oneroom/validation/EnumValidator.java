package jeju.oneroom.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumValue, String> {
    private String[] acceptedValues;

    @Override
    public void initialize(EnumValue annotation) {
        acceptedValues = annotation.acceptedValues();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        for (String validValue : acceptedValues) {
            if (validValue.equals(value)) {
                return true;
            }
        }

        return false;
    }
}
