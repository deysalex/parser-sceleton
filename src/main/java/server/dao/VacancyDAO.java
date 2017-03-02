package server.dao;

import server.entity.Vacancy;
import server.entity.ExportState;

import java.util.List;

public interface VacancyDAO {

    long create(Vacancy vacancy);

    Vacancy update(Vacancy vacancy);

    void delete(long id);

    List<Vacancy> getAll();

    List<Vacancy> getByCategory(Long categoryId);

    List<Vacancy> getByExportState(ExportState exportState);

    List<Vacancy> getByText(String text);

    Vacancy getOne(long id);

    boolean existUrl(String url);
}
