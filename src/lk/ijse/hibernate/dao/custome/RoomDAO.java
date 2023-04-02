package lk.ijse.hibernate.dao.custome;

import lk.ijse.hibernate.dao.CrudDao;
import lk.ijse.hibernate.dao.SuperDAO;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;

import java.util.List;

public interface RoomDAO extends CrudDao<Room,String> {
    List<Room> loadRoom(String s) throws Exception;
}
