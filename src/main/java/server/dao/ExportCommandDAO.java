package server.dao;

import server.entity.ExportCommand;
import server.entity.ExportCommandState;

import java.util.Calendar;
import java.util.List;

public interface ExportCommandDAO {

    long create(ExportCommand exportCommand);

    ExportCommand update(ExportCommand exportCommand);

    void delete(long id);

    List<ExportCommand> getAll();

    List<ExportCommand> getByState(ExportCommandState exportCommandState);

    List<ExportCommand> getByUpdateDate(Calendar date, int limit);

    ExportCommand getOne(long id);
}
