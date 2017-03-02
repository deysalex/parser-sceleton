package server.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import server.form.ExportCommandForm;

/**
 * Created by Deys on 30.06.2015.
 */
@Component
public class ExportCommandValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return ExportCommandForm.class.equals(aClass);
    }

    public void validate(Object obj, Errors errors) {
        ExportCommandForm exportCommandForm = (ExportCommandForm) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "command", "error.command", "Пустое значение");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "externalUrl", "error.externalUrl", "Пустое значение");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "requestUrl", "error.requestUrl", "Пустое значение");
    }
}