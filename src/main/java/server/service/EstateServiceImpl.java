package server.service;

/**
 * Created by Deys on 11.06.2015.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.dao.EstateDAO;
import server.entity.Estate;
import server.entity.ExportState;

import java.util.List;

@Service
@Transactional
@DependsOn({ "liquibase" })
public class EstateServiceImpl implements EstateService {

    private final Logger log = LoggerFactory.getLogger(EstateServiceImpl.class);

    @Autowired
    private EstateDAO estateDAO;

    @Override
    public long create(Estate estate) {
        return estateDAO.create(estate);
    }

    @Override
    public Estate update(Estate estate) {
        return estateDAO.update(estate);
    }

    @Override
    public Estate safeUpdateExportState(Estate estate, ExportState exportState) {
        try {
            estate.setExportState(exportState);
            return update(estate);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(long id) {
        estateDAO.delete(id);
    }

    @Override
    public List<Estate> getAll() {
        return estateDAO.getAll();
    }

    @Override
    public List<Estate> getByExportState(ExportState exportState) {
        return estateDAO.getByExportState(exportState);
    }

    @Override
    public Estate getOne(long id) {
        return estateDAO.getOne(id);
    }
}