package lk.ijse.hibernate.dao.custome.impl;

import lk.ijse.hibernate.dao.custome.QueryDAO;
import lk.ijse.hibernate.utill.session.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<Object[]> getPendingKeyPayments() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Object[]> list =session.createQuery("SELECT res.resId,s.student_id,s.name, r.room_type_id, r.type, res.status FROM Reservation res JOIN Student s ON res.student=s.student_id JOIN Room r ON res.room=r.room_type_id WHERE res.status='Pay Later'").list();
        transaction.commit();
        session.close();
        return list;
    }
}
