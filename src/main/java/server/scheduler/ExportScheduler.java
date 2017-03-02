package server.scheduler;

/**
 * Created by Deys on 11.06.2015.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import server.entity.Vacancy;
import server.entity.ExportState;
import server.manager.ExportManager;
import server.service.VacancyService;

@Service
public class ExportScheduler {

    private final Logger log = LoggerFactory.getLogger(ExportScheduler.class);

    @Autowired
    private ExportManager exportManager;

    @Autowired
    private VacancyService vacancyService;

    @Scheduled(fixedDelay = 60 * 1000) //1 min
    public void run()
    {
        for (Vacancy vacancy : vacancyService.getByExportState(ExportState.NEW)) {
            exportManager.export(vacancy);
        }
    }
}