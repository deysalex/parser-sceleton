package server.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import server.form.VacancyForm;

/**
 * Created by Deys on 30.06.2015.
 */
@Component
public class VacancyValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return VacancyForm.class.equals(aClass);
    }

    public void validate(Object obj, Errors errors) {
        VacancyForm vacancyForm = (VacancyForm) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.title", "Пустое значение");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "error.text", "Пустое значение");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.text", "Пустое значение");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url", "error.text", "Пустое значение");

        Utils.validateLong(errors, "categoryId", vacancyForm.getCategoryId());
        Utils.validateLong(errors, "cityId", vacancyForm.getCityId());
    }
}