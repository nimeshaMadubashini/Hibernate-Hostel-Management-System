package lk.ijse.hibernate.bo.custome;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.UserDto;

public interface UsrEditBO extends SuperBO {
    boolean updateUser(UserDto dto) throws Exception;
}
