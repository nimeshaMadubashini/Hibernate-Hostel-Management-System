package lk.ijse.hibernate.dao.custome.impl;

import lk.ijse.hibernate.dao.custome.UserDAO;
import lk.ijse.hibernate.entity.User;
import lk.ijse.hibernate.utill.session.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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
    public List<User> loadAll() throws Exception {
        return null;
    }

    @Override
    public List<String> loadId() throws Exception {
        return null;
    }

    @Override
    public boolean verifyUser(String username,String passWord)throws Exception {
        User user=null;
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql="FROM User WHERE userName=:userName AND password=:password";
            user = (User) session.createQuery(hql).setParameter("userName", username)
                    .setParameter("password",passWord).uniqueResult();
           /* query.setParameter("userName", entity.getUserName());
            query.setParameter("password", entity.getPassword());*/
            if(user!=null &&(user.getUserName().equals(username) && user.getPassword().equals(passWord))){
                return true;
            }
            transaction.commit();
            session.close();
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();

        }
        return false;
    }
}
