package lk.ijse.hibernate.dao.custome.impl;

import lk.ijse.hibernate.dao.custome.RoomDAO;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.utill.session.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean save(Room entity) throws Exception {
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
    public boolean update(Room entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "UPDATE Room SET type = :type, key_money = :keyMoney, qty = :qty WHERE room_type_id = :roomTypeId";
        Query query = session.createQuery(hql);
        query.setParameter("type", entity.getType());
        query.setParameter("keyMoney", entity.getKey_money());
        query.setParameter("qty", entity.getQty());
        query.setParameter("roomTypeId", entity.getRoom_type_id());
        int rowCount = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowCount > 0 ? true : false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(session.load(Room.class,s));
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
    public List<Room> loadAll() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Room ";
        Query query = session.createQuery(hql);
        List<Room> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<String> loadId() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT room_type_id FROM Room ";
        Query query = session.createQuery(hql);
        List<String> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
/*if want load all to derect textfield not use becaouse search conect table*/
    @Override
    public Room find(String s) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String id = s;
        String hql = "FROM Room  WHERE room_type_id = :search_id";
        Query query = session.createQuery(hql);
        query.setParameter("search_id", id);
        List<Room> RoomsList = query.list();
        for (Room room : RoomsList) {
            return new Room(room.getRoom_type_id(),room.getType(),room.getKey_money(),room.getQty());
        }
        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public List<Room> loadRoom(String s) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String id = s;
        String hql = "FROM Room  WHERE room_type_id = :search_id";
        Query query = session.createQuery(hql);
        query.setParameter("search_id", id);
        List<Room> RoomList = query.list();
        transaction.commit();
        session.close();
        return RoomList;
    }
}
