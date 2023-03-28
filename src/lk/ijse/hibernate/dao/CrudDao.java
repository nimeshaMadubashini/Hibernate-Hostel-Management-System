package lk.ijse.hibernate.dao;

public interface CrudDao <T, ID> extends SuperDAO {

    boolean save(T entity) throws Exception;

    boolean update(T entity) throws Exception;

    boolean delete(ID id) throws Exception;
}
