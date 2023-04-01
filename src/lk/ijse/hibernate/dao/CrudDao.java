package lk.ijse.hibernate.dao;

import lk.ijse.hibernate.entity.Student;

import java.util.List;

public interface CrudDao <T, ID> extends SuperDAO {

    boolean save(T entity) throws Exception;

    boolean update(T entity) throws Exception;

    boolean delete(ID id) throws Exception;
    public List<T> loadAll() throws Exception;
}
