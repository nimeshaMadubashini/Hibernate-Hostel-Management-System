package lk.ijse.hibernate.dao.custome;

import lk.ijse.hibernate.dao.SuperDAO;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Object[]> getPendingKeyPayments() throws Exception;
}
