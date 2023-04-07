package lk.ijse.hibernate.bo.custome;

import lk.ijse.hibernate.bo.SuperBO;

import java.util.List;

public interface ReservationBO  extends SuperBO{
    public List<String> loadRoomId() throws Exception;
    public List<String> loadStudentId() throws Exception;



}
