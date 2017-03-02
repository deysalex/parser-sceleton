package server.scheduler;

/**
 * Created by Deys on 11.06.2015.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import server.entity.ExportCommand;
import server.manager.ParseManager;
import server.service.ExportCommandService;

import java.util.Calendar;

@Service
public class ParseScheduler {

    private final Logger log = LoggerFactory.getLogger(ParseScheduler.class);

    @Autowired
    private ExportCommandService exportCommandService;

    @Autowired
    private ParseManager parseManager;

    @Scheduled(fixedDelay = 10 * 60 * 1000) //10 min
    public void run()
    {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH, -1);

        for (ExportCommand exportCommand : exportCommandService.getByUpdateDate(date, 5)) {
            parseManager.parse(exportCommand);
        }
    }

}