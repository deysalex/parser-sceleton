package server.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.entity.ExportState;
import server.entity.Vacancy;
import server.manager.export.WebSiteExportManager;
import server.service.VacancyService;

/**
 * Created by Deys on 15.06.2015.
 */
@Service
public class ExportManager {

    private final Logger log = LoggerFactory.getLogger(ExportManager.class);

    @Autowired
    private WebSiteExportManager webSiteExportManager;

    @Autowired
    private VacancyService vacancyService;

    public void export(Vacancy vacancy)
    {
        if (webSiteExportManager.export(vacancy)) {
            vacancyService.safeUpdateExportState(vacancy, ExportState.EXPORTED);
        }
    }

}
