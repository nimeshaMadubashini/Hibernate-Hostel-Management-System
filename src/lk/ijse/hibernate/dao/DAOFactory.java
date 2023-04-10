package lk.ijse.hibernate.dao;

import lk.ijse.hibernate.dao.SuperDAO;
import lk.ijse.hibernate.dao.custome.impl.*;


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
        User,STUDENT,ROOM,RESERVATON,QUERY

    }
    public SuperDAO getDo(DAOFactory.DOType doType){
        switch (doType){
            case User:
                return (SuperDAO) new UserDAOImpl();
            case ROOM:
                return (SuperDAO) new RoomDAOImpl();
            case RESERVATON:
                return (SuperDAO) new ReservationDAOImpl();
            case STUDENT:
                return (SuperDAO) new StudentDAOImpl();
            case QUERY:
                return (SuperDAO) new QueryDAOImpl();

        }
        return null;
    }
}
