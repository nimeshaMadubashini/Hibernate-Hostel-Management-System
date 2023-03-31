package lk.ijse.hibernate.bo.custome;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.StudentDTO;

public interface StudentBO extends SuperBO {
    boolean save(StudentDTO dto) throws Exception;

    boolean update(StudentDTO dto) throws Exception;

    boolean delete(String id) throws Exception;
}
