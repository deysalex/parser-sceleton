package server.service;

/**
 * Created by Deys on 11.06.2015.
 */
import server.entity.Vacancy;
import server.entity.ExportState;

import java.util.List;

public interface VacancyService {

    long create(Vacancy vacancy);

    Vacancy update(Vacancy vacancy);

    Vacancy safeUpdateExportState(Vacancy vacancy, ExportState exportState);

    void delete(long id);

    List<Vacancy> getAll();

    List<Vacancy> getByCategory(Long categoryId);

    List<Vacancy> getByExportState(ExportState exportState);

    List<Vacancy> getByText(String text);

    Vacancy getOne(long id);

    boolean existUrl(String url);
}
