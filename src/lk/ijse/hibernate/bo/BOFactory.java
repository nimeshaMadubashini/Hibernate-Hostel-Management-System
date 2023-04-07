package lk.ijse.hibernate.bo;


import lk.ijse.hibernate.bo.custome.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory() {
    }

    public static BOFactory getBoFactory(){
        if(boFactory==null){
            boFactory= new BOFactory();
        }
        return boFactory;
    }

    public enum BOType {
        UserSignUp,UserLogin,STUDENT,ROOM,RESERVATION,USEREDIT,HOME
    }
    public SuperBO getBO(BOType boType){
        switch (boType){
            case UserSignUp:
               return (SuperBO) new UserBOImpl();
            case UserLogin:
                return (SuperBO)new UserLoginBOImpl();
            case STUDENT:
        return (SuperBO) new StudentBOImpl();
            case ROOM:
               return (SuperBO) new RoomBOImpl();
            case RESERVATION:
              return (SuperBO) new ReservationBOImpl();
            case USEREDIT:
                return (SuperBO) new UserEditBOImpl();
        /* case INSURENCE1:
              return (SuperBO) new ManageInsuranceBOImpl();*/
//
        }
        return null;
    }
}
