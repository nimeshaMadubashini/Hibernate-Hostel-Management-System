package lk.ijse.hibernate.bo.custome;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Student;

import java.util.List;

public interface StudentBO extends SuperBO {
    boolean save(StudentDTO dto) throws Exception;

    boolean update(StudentDTO dto) throws Exception;

    boolean delete(String id) throws Exception;
    List<StudentDTO> loadAllStudent() throws Exception;
    public List<String> loadStudentId() throws Exception;
    List<StudentDTO> findStudent(String s) throws Exception;
}
