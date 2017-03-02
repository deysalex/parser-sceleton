package server.dao;

/**
 * Created by Deys on 11.06.2015.
 */

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import server.entity.Estate;
import server.entity.ExportState;

import java.io.Serializable;
import java.util.List;

@Repository
public class EstateDAOImpl implements EstateDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long create(Estate estate) {
        Serializable id = sessionFactory.getCurrentSession().save(estate);
        return (Long)id;
    }

    @Override
    public Estate update(Estate estate) {
        sessionFactory.getCurrentSession().update(estate);
        return estate;
    }

    @Override
    public void delete(long id) {
        Estate estate = new Estate();
        estate.setId(id);
        sessionFactory.getCurrentSession().delete(estate);
    }

    @Override
    public List<Estate> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT e FROM Estate e");

        return query.list();
    }

    @Override
    public List<Estate> getByExportState(ExportState exportState) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT e FROM Estate e WHERE e.exportState = :exportState");
        query.setParameter("exportState", exportState);

        return query.list();
    }

    @Override
    public Estate getOne(long id) {
        return (Estate)sessionFactory.getCurrentSession().get(Estate.class, id);
    }
}
