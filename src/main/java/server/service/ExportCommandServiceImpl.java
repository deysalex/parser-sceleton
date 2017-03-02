package server.service;

/**
 * Created by Deys on 11.06.2015.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.dao.ExportCommandDAO;
import server.entity.ExportCommand;
import server.entity.ExportCommandState;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
@DependsOn({ "liquibase" })
public class ExportCommandServiceImpl implements ExportCommandService {

    @Autowired
    private ExportCommandDAO exportCommandDAO;

    @Override
    public long create(ExportCommand exportCommand)
    {

        return exportCommandDAO.create(exportCommand);
    }

    @Override
    public ExportCommand update(ExportCommand exportCommand)
    {
        exportCommand.setUpdateDate(Calendar.getInstance());
        return exportCommandDAO.update(exportCommand);
    }

    @Override
    public void delete(long id) {
        exportCommandDAO.delete(id);
    }

    @Override
    public List<ExportCommand> getAll() {
        return exportCommandDAO.getAll();
    }

    @Override
    public List<ExportCommand> getByState(ExportCommandState exportCommandState) {
        return exportCommandDAO.getByState(exportCommandState);
    }

    @Override
    public List<ExportCommand> getByUpdateDate(Calendar date, int limit) {
        return exportCommandDAO.getByUpdateDate(date, limit);
    }

    @Override
    public ExportCommand getOne(long id) {
        return exportCommandDAO.getOne(id);
    }
}