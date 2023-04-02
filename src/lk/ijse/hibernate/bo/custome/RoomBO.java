package lk.ijse.hibernate.bo.custome;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;

import java.util.List;

public interface RoomBO extends SuperBO {
    boolean save(RoomDTO dto) throws Exception;

    boolean update(RoomDTO dto) throws Exception;

    boolean delete(String id) throws Exception;
    List<RoomDTO> loadAllRoomTOTable() throws Exception;
    public List<String> loadRoomId() throws Exception;
    List<RoomDTO> findRoomCM(String s) throws Exception;
}
