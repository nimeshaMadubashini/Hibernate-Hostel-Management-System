package lk.ijse.hibernate.utill.session;

import lk.ijse.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {
private static SessionFactoryConfig sessionFactoryConfig;
private final SessionFactory sessionFactory;

    private SessionFactoryConfig() {
        sessionFactory = new Configuration()
                .mergeProperties(Utility.getProperties())
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
    public static SessionFactoryConfig getInstance(){
        return null==sessionFactoryConfig?sessionFactoryConfig=new SessionFactoryConfig():
                sessionFactoryConfig;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
