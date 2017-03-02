package server.dao;

/**
 * Created by Deys on 11.06.2015.
 */

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import server.entity.Vacancy;
import server.entity.ExportState;

import java.io.Serializable;
import java.util.List;

@Repository
public class VacancyDAOImpl implements VacancyDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long create(Vacancy vacancy) {
        Serializable id = sessionFactory.getCurrentSession().save(vacancy);
        return (Long)id;
    }

    @Override
    public Vacancy update(Vacancy vacancy) {
        sessionFactory.getCurrentSession().update(vacancy);
        return vacancy;
    }

    @Override
    public void delete(long id) {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(id);
        sessionFactory.getCurrentSession().delete(vacancy);
    }

    @Override
    public List<Vacancy> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT e FROM Vacancy e");

        return query.list();
    }

    @Override
    public List<Vacancy> getByCategory(Long categoryId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT e FROM Vacancy e WHERE e.category.id = :categoryId");
        query.setParameter("categoryId", categoryId);

        return query.list();
    }

    @Override
    public List<Vacancy> getByExportState(ExportState exportState) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT e FROM Vacancy e WHERE e.exportState = :exportState");
        query.setParameter("exportState", exportState);

        return query.list();
    }

    @Override
    public List<Vacancy> getByText(String text) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT e FROM Vacancy e WHERE e.text like lower(:text)");
        query.setParameter("text", text);

        return query.list();
    }

    @Override
    public Vacancy getOne(long id) {
        return (Vacancy)sessionFactory.getCurrentSession().get(Vacancy.class, id);
    }

    @Override
    public boolean existUrl(String url) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT e FROM Vacancy e WHERE e.url = :url");
        query.setParameter("url", url);

        return !query.list().isEmpty();
    }
}
