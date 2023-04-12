package lk.ijse.hibernate.dao.custome.impl;

import lk.ijse.hibernate.dao.custome.ReservationDAO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.utill.session.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public boolean save(Reservation entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        Student student = entity.getStudent();
        student.getStudentList().add(entity);
        Room room = entity.getRoom();
        room.setQty(room.getQty()-1);
        room.getRoomList().add(entity);
        session.update(student);
        session.update(room);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reservation entity) throws Exception {
        Session session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public List<Reservation> loadAll() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Reservation ";
        Query query = session.createQuery(hql);
        List<Reservation> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<String> loadId() throws Exception {
        return null;
    }

    @Override
    public Reservation find(String s) throws Exception {
        return null;
    }
    @Override
    public String getReservationId() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<String> list = session.createQuery("SELECT resId FROM Reservation ORDER BY resId DESC").setMaxResults(1).list();
        transaction.commit();
        session.close();
        return list.size()>0? String.format("R%03d",Integer.parseInt(list.get(0).replace("R",""))+1):"R001";
    }
    @Override
    public Reservation get(String s) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Reservation reservation = session.get(Reservation.class, s);
        transaction.commit();
        session.close();
        return reservation;
    }
}
