package lk.ijse.hibernate.bo.custome;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.UserDto;

public interface UserLoginBO extends SuperBO {
    boolean verifyUser(String userName,String password)throws Exception;
}
