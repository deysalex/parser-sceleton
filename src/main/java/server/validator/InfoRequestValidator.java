package server.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import server.form.ExportCommandForm;
import server.form.InfoRequestForm;

/**
 * Created by Deys on 30.06.2015.
 */
@Component
public class InfoRequestValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return ExportCommandForm.class.equals(aClass);
    }

    public void validate(Object obj, Errors errors) {
        InfoRequestForm infoRequestForm = (InfoRequestForm) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url", "error.url", "Пустое значение");
    }
}