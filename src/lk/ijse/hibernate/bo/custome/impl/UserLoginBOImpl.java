package lk.ijse.hibernate.bo.custome.impl;

import lk.ijse.hibernate.bo.custome.UserLoginBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custome.UserDAO;
import lk.ijse.hibernate.dto.UserDto;
import lk.ijse.hibernate.entity.User;

public class UserLoginBOImpl implements UserLoginBO {
    UserDAO userDAO= (UserDAO) DAOFactory.getBoFactory().getDo(DAOFactory.DOType.User);
    @Override
    public boolean verifyUser(UserDto dto) throws Exception {
        return userDAO.verifyUser(new User(dto.getNic(), dto.getName(),dto.getUserName(), dto.getPassword()));
    }
}
