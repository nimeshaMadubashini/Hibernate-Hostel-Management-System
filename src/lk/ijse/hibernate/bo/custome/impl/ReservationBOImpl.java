package lk.ijse.hibernate.bo.custome.impl;

import lk.ijse.hibernate.bo.custome.ReservationBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custome.ReservationDAO;
import lk.ijse.hibernate.dao.custome.RoomDAO;
import lk.ijse.hibernate.dao.custome.StudentDAO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;

import java.util.List;


public class ReservationBOImpl implements ReservationBO{
    RoomDAO roomDAO= (RoomDAO) DAOFactory.getBoFactory().getDo(DAOFactory.DOType.ROOM);
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getBoFactory().getDo(DAOFactory.DOType.STUDENT);
    ReservationDAO reservationDAO= (ReservationDAO) DAOFactory.getBoFactory().getDo(DAOFactory.DOType.RESERVATON);
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

    @Override
    public RoomDTO findDetail(String s) throws Exception {
        Room room = roomDAO.find(s);
        return new RoomDTO(room.getRoom_type_id(), room.getType(), room.getKey_money(),room.getQty());
    }

    @Override
    public StudentDTO findStudent(String s) throws Exception {
        Student student=studentDAO.find(s);
        return new StudentDTO(student.getStudent_id(),student.getName(),student.getAddress(),student.getContact_no(),
                student.getDob(),student.getGender());
    }

    @Override
    public boolean save(ReservationDTO dto) throws Exception {
        return false;
    }

    @Override
    public String getReservationId() throws Exception {
        return reservationDAO.getReservationId() ;
    }
}
