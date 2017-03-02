package server.translator;

import org.springframework.stereotype.Service;
import server.entity.Estate;
import server.entity.Vacancy;
import server.form.EstateForm;
import server.form.VacancyForm;

@Service
public class EstateTranslator
        extends CommonTranslator<Estate, EstateForm> {

    @Override
    public void businessToData(
        Estate src,
        EstateForm data)
    {
        data.setId(String.valueOf(src.getId()));
        data.setTitle(src.getTitle());
        data.setText(src.getText());
        data.setPrice(src.getPrice());
    }

    @Override
    public void dataToBusiness(
        EstateForm data,
        Estate src)
    {
        src.setId(Long.parseLong(data.getId()));
        src.setTitle(data.getTitle());
        src.setText(data.getText());
        src.setPrice(data.getPrice());
    }

}
