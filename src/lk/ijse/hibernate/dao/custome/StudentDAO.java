package lk.ijse.hibernate.dao.custome;

import lk.ijse.hibernate.dao.CrudDao;
import lk.ijse.hibernate.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDao<Student,String> {
    List<Student> loadStudent(String s) throws Exception;
}
