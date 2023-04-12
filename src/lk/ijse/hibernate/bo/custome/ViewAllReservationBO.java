package lk.ijse.hibernate.bo.custome;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.KeyPaymentDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Student;

import java.util.ArrayList;
import java.util.List;

public interface ViewAllReservationBO extends SuperBO {

    public List<KeyPaymentDTO> loadAllStudent() throws Exception;
    public List<String> loadReserveRoomStId() throws Exception;
    StudentDTO findStudent(String s) throws Exception,NullPointerException;
}
