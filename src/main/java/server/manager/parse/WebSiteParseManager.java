package server.manager.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import server.entity.Vacancy;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Deys on 15.06.2015.
 */
@Service
public class WebSiteParseManager {

    public Vacancy parse(String url, String categoryId, String cityId) throws IOException
    {
        String ADDITION_INFORMATION = new String("Дополнительная информация по вакансии".getBytes("UTF-8"), "UTF-8");
        String CONTACT_PERSON = new String("Контактное лицо".getBytes("UTF-8"), "UTF-8");
        String EXPERIONS = new String("Опыт работы (лет)".getBytes("UTF-8"), "UTF-8");
        String REQUIREMENTS = new String("Требования:".getBytes("UTF-8"), "UTF-8");
        String EDUCATION = new String("Образование:".getBytes("UTF-8"), "UTF-8");
        String DUTIES = new String("Должностные обязанности".getBytes("UTF-8"), "UTF-8");
        String SPECIALTY = new String("Специальность по образованию".getBytes("UTF-8"), "UTF-8");

        Document doc = Jsoup.connect(url).get();
        doc.outputSettings().charset("UTF-8");

        Vacancy vacancy = new Vacancy();

        Elements title = doc.select("div.vacancy div.row h2");
        if (title.size() > 0) {
            vacancy.setTitle(title.first().text());
        }

        Elements price = doc.select("div.vacancy div.row div.pay");
        if (price.size() > 0) {
            vacancy.setPrice(price.first().text());
        }

        String contact = "";
        StringBuilder text = new StringBuilder();
        Elements rows = doc.select("div.row");
        for (Element row : rows) {
            String tempString = row.text();
            if (tempString.contains(ADDITION_INFORMATION)
                    || tempString.contains(EXPERIONS)
                    || tempString.contains(REQUIREMENTS)
                    || tempString.contains(EDUCATION)
                    //|| tempString.contains(DUTIES)
                    || tempString.contains(SPECIALTY)) {
                clearStyle(row.getAllElements());
                text.append(row.html());
            } else if (tempString.contains(CONTACT_PERSON)) {
                clearStyle(row.getAllElements());
                contact = row.html();
            }
        }
        text.append("<br/>");
        text.append(contact);
        vacancy.setText(text.toString());
        vacancy.setUrl(url);
        vacancy.setCategoryId(categoryId);
        vacancy.setCityId(cityId);
        vacancy.setDate(Calendar.getInstance());
        return vacancy;
    }

    private void clearStyle(Elements elements)
    {
        for (Element element : elements) {
            element = element.attr("style", false).attr("class", false);
            clearStyle(element.children());
        }
    }
}
