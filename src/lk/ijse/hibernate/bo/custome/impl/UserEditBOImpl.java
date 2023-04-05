package lk.ijse.hibernate.bo.custome.impl;

import lk.ijse.hibernate.bo.custome.UsrEditBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custome.UserDAO;
import lk.ijse.hibernate.dto.UserDto;
import lk.ijse.hibernate.entity.User;

public class UserEditBOImpl implements UsrEditBO {
    UserDAO userDAO= (UserDAO) DAOFactory.getBoFactory().getDo(DAOFactory.DOType.User);

    @Override
    public boolean updateUser(UserDto dto) throws Exception {
        return  userDAO.update(new User(dto.getNic(), dto.getUserName(), dto.getPassword()));
    }
}
