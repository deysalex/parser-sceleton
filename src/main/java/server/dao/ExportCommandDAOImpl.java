package server.dao;

/**
 * Created by Deys on 11.06.2015.
 */

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import server.entity.ExportCommand;
import server.entity.ExportCommandState;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Repository
public class ExportCommandDAOImpl implements ExportCommandDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long create(ExportCommand exportCommand) {
        Serializable id = sessionFactory.getCurrentSession().save(exportCommand);
        return (Long)id;
    }

    @Override
    public ExportCommand update(ExportCommand exportCommand) {
        sessionFactory.getCurrentSession().update(exportCommand);
        return exportCommand;
    }

    @Override
    public void delete(long id) {
        ExportCommand exportCommand = new ExportCommand();
        exportCommand.setId(id);
        sessionFactory.getCurrentSession().delete(exportCommand);
    }

    @Override
    public List<ExportCommand> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT e FROM ExportCommand e");

        return query.list();
    }

    @Override
    public List<ExportCommand> getByState(ExportCommandState exportCommandState) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from ExportCommand e where e.state = :state ");
        query.setParameter("state", exportCommandState);

        return query.list();
    }

    @Override
    public List<ExportCommand> getByUpdateDate(Calendar date, int limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from ExportCommand e where e.updateDate < :updateDate ");
        query.setParameter("updateDate", date);
        query.setFetchSize(limit);

        return query.list();
    }

    @Override
    public ExportCommand getOne(long id) {
        return (ExportCommand)sessionFactory.getCurrentSession().get(ExportCommand.class, id);
    }
}
