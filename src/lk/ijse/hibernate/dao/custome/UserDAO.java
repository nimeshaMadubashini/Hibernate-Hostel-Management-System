package lk.ijse.hibernate.dao.custome;

import lk.ijse.hibernate.dao.CrudDao;
import lk.ijse.hibernate.entity.User;

public interface UserDAO extends CrudDao<User,String> {
boolean verifyUser(String username,String passWord)throws Exception;
}
