package lk.ijse.hibernate.dao.custome.impl;

import lk.ijse.hibernate.dao.custome.UserDAO;
import lk.ijse.hibernate.entity.User;
import lk.ijse.hibernate.utill.session.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
            session.close();
            return true;

    } catch (Exception ex) {
        transaction.rollback();
        session.close();
        ex.printStackTrace();
        return false;
    }
    }

    @Override
    public boolean update(User entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean verifyUser(User entity)throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql="FROM User WHERE userName=:userName AND password=:password";
            Query query = session.createQuery(hql);
            query.setParameter("userName", entity.getUserName());
            query.setParameter("password", entity.getPassword());
            query.uniqueResult();
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return false;
        }
    }
}
