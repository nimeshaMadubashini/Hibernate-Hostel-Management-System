package lk.ijse.hibernate.dao;

import lk.ijse.hibernate.dao.SuperDAO;
import lk.ijse.hibernate.dao.custome.impl.UserDAOImpl;


public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {
    }

    public static DAOFactory getBoFactory(){
        if(daoFactory==null){
            daoFactory= new DAOFactory();
        }
        return   daoFactory;
    }

    public enum DOType {
        User

    }
    public SuperDAO getDo(DAOFactory.DOType doType){
        switch (doType){
            case User:
                return (SuperDAO) new UserDAOImpl();
          /*  case BOOKSUPPLYDETAIL:
                return (SuperDAO) new BookSuplyDetailDAOImpl();
            case CATEGORY:
                return (SuperDAO) new CategoryDAOImpl();
            case EXPENDITURE:
                return (SuperDAO) new ExpenditureDAOImpl();
            case GRANTER:
                return (SuperDAO) new GranterDAOImpl();*/

        }
        return null;
    }
}
