package lk.ijse.hibernate.bo.custome;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.UserDto;

public interface UserBO extends SuperBO {
     boolean saveUser(UserDto dto) throws Exception;
}
