package server.translator;

import org.springframework.stereotype.Service;
import server.entity.Vacancy;
import server.form.VacancyForm;

@Service
public class VacancyTranslator
        extends CommonTranslator<Vacancy, VacancyForm> {

    @Override
    public void businessToData(
        Vacancy src,
        VacancyForm data)
    {
        data.setId(String.valueOf(src.getId()));
        data.setTitle(src.getTitle());
        data.setText(src.getText());
        data.setPrice(src.getPrice());
        data.setCategoryId(src.getCategoryId());
        data.setCityId(src.getCityId());
    }

    @Override
    public void dataToBusiness(
        VacancyForm data,
        Vacancy src)
    {
        src.setId(Long.parseLong(data.getId()));
        src.setTitle(data.getTitle());
        src.setText(data.getText());
        src.setPrice(data.getPrice());
        src.setCategoryId(data.getCategoryId());
        src.setCityId(data.getCityId());
    }

}
