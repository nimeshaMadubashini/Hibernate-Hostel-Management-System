package lk.ijse.hibernate.bo.custome.impl;

import lk.ijse.hibernate.bo.custome.ReservationBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custome.RoomDAO;
import lk.ijse.hibernate.dao.custome.StudentDAO;

import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    RoomDAO roomDAO= (RoomDAO) DAOFactory.getBoFactory().getDo(DAOFactory.DOType.ROOM);
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getBoFactory().getDo(DAOFactory.DOType.STUDENT);
    @Override
    public List<String> loadRoomId() throws Exception {
        List<String> list=roomDAO.loadId();
        return list;
    }

    @Override
    public List<String> loadStudentId() throws Exception {
        List<String> list=studentDAO.loadId();
        return list;
    }
}
