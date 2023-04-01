package lk.ijse.hibernate.bo.custome;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    boolean save(StudentDTO dto) throws Exception;

    boolean update(StudentDTO dto) throws Exception;

    boolean delete(String id) throws Exception;
    List<StudentDTO> loadAllStudent() throws Exception;
}
