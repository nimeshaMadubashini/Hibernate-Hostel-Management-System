package lk.ijse.hibernate.bo.custome.impl;

import lk.ijse.hibernate.bo.custome.StudentBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custome.StudentDAO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getBoFactory().getDo(DAOFactory.DOType.STUDENT);
    @Override
    public boolean save(StudentDTO dto) throws Exception {
        return studentDAO.save(new Student(dto.getStudent_id(),dto.getName(),dto.getAddress(),
                dto.getContact_no(),dto.getDob(),dto.getGender())) ;
    }

    @Override
    public boolean update(StudentDTO dto) throws Exception {
        return studentDAO.update(new Student(dto.getStudent_id(),dto.getName(),dto.getAddress(),
                dto.getContact_no(),dto.getDob(),dto.getGender()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public List<StudentDTO> loadAllStudent() throws Exception {

        List<StudentDTO>studentDTOList =new ArrayList<>();
        List<Student>list = studentDAO.loadAll();
        for (Student s:list
        ) {
            studentDTOList.add(new StudentDTO(s.getStudent_id(),s.getName(),s.getAddress(),s.getContact_no(),s.getDob(),s.getGender()));

        }
        return studentDTOList;

    }

    @Override
    public List<String> loadStudentId() throws Exception {
        List<String> list=studentDAO.loadId();
        return list;
    }

    @Override
    public List<StudentDTO> findStudent(String id) throws Exception {
        List<StudentDTO>studentDTOList =new ArrayList<>();
        List<Student>list = studentDAO.loadStudent(id);
        for (Student s:list
        ) {
            studentDTOList.add(new StudentDTO(s.getStudent_id(),s.getName(),s.getAddress(),s.getContact_no(),s.getDob(),s.getGender()));

        }
        return studentDTOList;
    }

   /* @Override
    public StudentDTO findStudent(String s) throws Exception {
        Student student=studentDAO.find(s);
        return new StudentDTO(student.getStudent_id(),student.getName(),student.getAddress(),student.getContact_no(),
                student.getDob(),student.getGender());
    }*/
}
