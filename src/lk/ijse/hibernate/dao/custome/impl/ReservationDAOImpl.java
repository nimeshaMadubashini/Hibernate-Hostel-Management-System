package lk.ijse.hibernate.dao.custome.impl;

import lk.ijse.hibernate.dao.custome.ReservationDAO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.utill.session.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public List<Reservation> loadAll() throws Exception {
        return null;
    }

    @Override
    public List<String> loadId() throws Exception {
        return null;
    }

    @Override
    public Reservation find(String s) throws Exception {
        return null;
    }
}
