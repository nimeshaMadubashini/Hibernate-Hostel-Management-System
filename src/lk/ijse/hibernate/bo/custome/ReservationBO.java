package lk.ijse.hibernate.bo.custome;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Room;

import java.util.List;

public interface ReservationBO  extends SuperBO{
    public List<String> loadRoomId() throws Exception;
    public List<String> loadStudentId() throws Exception;

    RoomDTO findDetail(String s) throws Exception;
    StudentDTO findStudent(String s) throws Exception;
    boolean save(ReservationDTO dto) throws Exception;
    String getReservationId() throws Exception;
}
