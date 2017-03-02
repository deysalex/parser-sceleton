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
import server.dao.VacancyDAO;
import server.entity.Vacancy;
import server.entity.ExportState;

import java.util.List;

@Service
@Transactional
@DependsOn({ "liquibase" })
public class VacancyServiceImpl implements VacancyService {

    private final Logger log = LoggerFactory.getLogger(VacancyServiceImpl.class);

    @Autowired
    private VacancyDAO vacancyDAO;

    @Override
    public long create(Vacancy vacancy) {
        return vacancyDAO.create(vacancy);
    }

    @Override
    public Vacancy update(Vacancy vacancy) {
        return vacancyDAO.update(vacancy);
    }

    @Override
    public Vacancy safeUpdateExportState(Vacancy vacancy, ExportState exportState) {
        try {
            vacancy.setExportState(exportState);
            return update(vacancy);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(long id) {
        vacancyDAO.delete(id);
    }

    @Override
    public List<Vacancy> getAll() {
        return vacancyDAO.getAll();
    }

    @Override
    public List<Vacancy> getByCategory(Long categoryId) {
        return vacancyDAO.getByCategory(categoryId);
    }

    @Override
    public List<Vacancy> getByExportState(ExportState exportState) {
        return vacancyDAO.getByExportState(exportState);
    }

    @Override
    public List<Vacancy> getByText(String text) {
        return vacancyDAO.getByText(text);
    }

    @Override
    public Vacancy getOne(long id) {
        return vacancyDAO.getOne(id);
    }

    @Override
    public boolean existUrl(String url) {
        return vacancyDAO.existUrl(url);
    }
}