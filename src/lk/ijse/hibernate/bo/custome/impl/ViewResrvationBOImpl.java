package lk.ijse.hibernate.bo.custome.impl;

import lk.ijse.hibernate.bo.custome.ViewAllReservationBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custome.QueryDAO;
import lk.ijse.hibernate.dao.custome.ReservationDAO;
import lk.ijse.hibernate.dao.custome.StudentDAO;
import lk.ijse.hibernate.dto.KeyPaymentDTO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ViewResrvationBOImpl implements ViewAllReservationBO {
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getBoFactory().getDo(DAOFactory.DOType.STUDENT);
    ReservationDAO reservationDAO= (ReservationDAO) DAOFactory.getBoFactory().getDo(DAOFactory.DOType.RESERVATON);
QueryDAO qDAo= (QueryDAO) DAOFactory.getBoFactory().getDo(DAOFactory.DOType.QUERY);

    public List<KeyPaymentDTO> loadAllStudent() throws Exception {
        List<KeyPaymentDTO> roomDTOList=new ArrayList<>();
        List<Reservation> list=reservationDAO.loadAll();
        for (Reservation r:list) {
            roomDTOList.add(new KeyPaymentDTO(r.getResId(),r.getStudent().getStudent_id(),r.getStudent().getName(),
                    r.getRoom().getRoom_type_id(),r.getRoom().getType(),r.getStatus()));
        }
        return roomDTOList;

    }

    @Override
    public List<String> loadReserveRoomStId() throws Exception {
        List<String> list=qDAo.loadReserveRoomStId();
        return  list;
    }
    public StudentDTO findStudent(String s) throws Exception ,NullPointerException {
        Student student=studentDAO.find(s);
        return new StudentDTO(student.getStudent_id(),student.getName(),student.getAddress(),student.getContact_no(),
                student.getDob(),student.getGender());
    }
}
