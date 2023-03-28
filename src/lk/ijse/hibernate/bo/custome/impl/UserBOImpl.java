package lk.ijse.hibernate.bo.custome.impl;

import lk.ijse.hibernate.bo.custome.UserBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custome.UserDAO;

import lk.ijse.hibernate.dto.UserDto;
import lk.ijse.hibernate.entity.User;

public class UserBOImpl implements UserBO {
 UserDAO userDAO= (UserDAO) DAOFactory.getBoFactory().getDo(DAOFactory.DOType.User);
    public boolean saveUser(UserDto dto) throws Exception { // We're getting a DTO type from the controller
return userDAO.save(new User(dto.getNic(),dto.getName(),dto.getUserName(),dto.getPassword()));
    }
}
