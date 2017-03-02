package server.dao;

import server.entity.Estate;
import server.entity.ExportState;

import java.util.List;

public interface EstateDAO {

    long create(Estate estate);

    Estate update(Estate vacancy);

    void delete(long id);

    List<Estate> getAll();

    List<Estate> getByExportState(ExportState exportState);

    Estate getOne(long id);
}
