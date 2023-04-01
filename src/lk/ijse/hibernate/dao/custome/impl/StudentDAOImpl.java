package lk.ijse.hibernate.dao.custome.impl;

import lk.ijse.hibernate.dao.custome.StudentDAO;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.utill.session.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Student entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(entity);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(session.load(Student.class,s));
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public List<Student> loadAll() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Student";
        Query query = session.createQuery(hql);
        List<Student> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
    @Override
public List<String> loadId() throws Exception{
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT student_id FROM Student ";
        Query query = session.createQuery(hql);
        List<String> list = query.list();
        transaction.commit();
        session.close();
        return list;
}
    @Override
    public Student find(String s) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String id = s;
        String hql = "FROM Student  WHERE student_id = :search_id";
        Query query = session.createQuery(hql);
        query.setParameter("search_id", id);
        List<Student> studentsList = query.list();
        for (Student student : studentsList) {
            return new Student(student.getStudent_id(),student.getName(),student.getAddress(),student.getContact_no(),student.getDob(),student.getGender());
        }
        transaction.commit();
        session.close();
        return null;

    }
    public List<Student> loadStudent(String s){
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String id = s;
        String hql = "FROM Student  WHERE student_id = :search_id";
        Query query = session.createQuery(hql);
        query.setParameter("search_id", id);
        List<Student> studentsList = query.list();
        transaction.commit();
        session.close();
        return studentsList;
    }

}
