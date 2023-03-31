package lk.ijse.hibernate.dao;

import lk.ijse.hibernate.dao.SuperDAO;
import lk.ijse.hibernate.dao.custome.impl.ReservationDAOImpl;
import lk.ijse.hibernate.dao.custome.impl.RoomDAOImpl;
import lk.ijse.hibernate.dao.custome.impl.StudentDAOImpl;
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
        User,STUDENT,ROOM,RESERVATON

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

        }
        return null;
    }
}
