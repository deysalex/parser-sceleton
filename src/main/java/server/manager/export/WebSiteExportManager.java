package server.manager.export;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import server.entity.Vacancy;

/**
 * Created by Deys on 15.06.2015.
 */
@Service
public class WebSiteExportManager {

    private final Logger log = LoggerFactory.getLogger(WebSiteExportManager.class);

    @Value("${export.url.web.site}")
    private String url;

    public boolean export(Vacancy vacancy)
    {
        return !vacancy.getText().isEmpty() && sendRequest(vacancy);
    }

    private boolean sendRequest(Vacancy vacancy)
    {
        boolean result = true;
        try {
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
            map.add("category", vacancy.getCategoryId());
            map.add("city", vacancy.getCityId());
            map.add("title", vacancy.getTitle());
            map.add("text", vacancy.getText());
            map.add("price", vacancy.getPrice());
            map.add("url_resource", vacancy.getUrl());

            RestTemplate rest = new RestTemplate();
            rest.postForObject(url, map, String.class);
        } catch (Exception e) {
            log.error(e.getMessage());

            result = false;
        }
        return result;
    }
}
