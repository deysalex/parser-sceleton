package server.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import server.entity.ExportCommand;
import server.entity.ExportCommandState;
import server.entity.Vacancy;
import server.service.ExportCommandService;
import server.service.VacancyService;

import java.util.Calendar;

/**
 * Created by Deys on 15.06.2015.
 */
@Service
public class ParseManager {

    private final Logger log = LoggerFactory.getLogger(ParseManager.class);

    @Autowired
    private RuntimeManager runtimeManager;

    @Autowired
    private ExportCommandService exportCommandService;

    public void parse(ExportCommand exportCommand)
    {
        try {
            exportCommand.setState(ExportCommandState.PROGRESS);
            exportCommandService.update(exportCommand);

            runtimeManager.runSlimerJs(exportCommand);

            exportCommand.setState(ExportCommandState.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            exportCommand.setState(ExportCommandState.ERROR);
        }
        exportCommandService.update(exportCommand);
    }
}
