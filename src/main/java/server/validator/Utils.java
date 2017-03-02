package server.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public class Utils {

    public static void validateDouble(
        Errors errors,
        String name,
        String value)
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, name, "field.required", "Пустое значение");
        if (errors.getFieldError(name) == null) {
            try {
                Double.parseDouble(value);
            } catch (NumberFormatException e) {
                errors.rejectValue(name, name, "Не правильное значение");
            }
        }
    }

    public static void validateLong(
        Errors errors,
        String name,
        String value)
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, name, "field.required", "Пустое значение");
        if (errors.getFieldError(name) == null) {
            try {
                Long.parseLong(value);
            } catch (NumberFormatException e) {
                errors.rejectValue(name, name, "Не правильное значение");
            }
        }
    }
}
