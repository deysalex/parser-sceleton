package server.service;

/**
 * Created by Deys on 11.06.2015.
 */

import server.entity.Estate;
import server.entity.ExportState;

import java.util.List;

public interface EstateService {

    long create(Estate estate);

    Estate update(Estate estate);

    Estate safeUpdateExportState(Estate estate, ExportState exportState);

    void delete(long id);

    List<Estate> getAll();

    List<Estate> getByExportState(ExportState exportState);

    Estate getOne(long id);
}
