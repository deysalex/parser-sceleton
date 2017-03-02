package server.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import server.form.EstateForm;

/**
 * Created by Deys on 30.06.2015.
 */
@Component
public class EstateValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return EstateForm.class.equals(aClass);
    }

    public void validate(Object obj, Errors errors) {
        EstateForm estateForm = (EstateForm) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.title", "Пустое значение");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "error.text", "Пустое значение");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.text", "Пустое значение");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url", "error.text", "Пустое значение");
    }
}