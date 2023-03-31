package lk.ijse.hibernate.bo.custome.impl;

import lk.ijse.hibernate.bo.custome.StudentBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custome.StudentDAO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Student;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getBoFactory().getDo(DAOFactory.DOType.STUDENT);
    @Override
    public boolean save(StudentDTO dto) throws Exception {
        return studentDAO.save(new Student(dto.getStudentId(),dto.getName(),dto.getAddress(),
                dto.getContactNo(),dto.getDob(),dto.getGender())) ;
    }

    @Override
    public boolean update(StudentDTO dto) throws Exception {
        return studentDAO.save(new Student(dto.getStudentId(),dto.getName(),dto.getAddress(),
                dto.getContactNo(),dto.getDob(),dto.getGender()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return studentDAO.delete(id);
    }
}
