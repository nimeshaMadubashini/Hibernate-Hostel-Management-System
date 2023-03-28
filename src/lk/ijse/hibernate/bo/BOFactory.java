package lk.ijse.hibernate.bo;


import lk.ijse.hibernate.bo.custome.impl.UserBOImpl;
import lk.ijse.hibernate.bo.custome.impl.UserLoginBOImpl;

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
        UserSignUp,UserLogin
    }
    public SuperBO getBO(BOType boType){
        switch (boType){
            case UserSignUp:
               return (SuperBO) new UserBOImpl();
            case UserLogin:
                return (SuperBO)new UserLoginBOImpl();
//            case BOOKSUPPLYDETAIL1:
//                return (SuperBO) new BookSupplyDetailBOImpl();
//            case CATEGORY1:
//                return (SuperBO) new ManageBookCategoryBOIImpl();
//            case EXPENDITURE1:
//                return (SuperBO) new ManageOtherExpenditureBOImpl();
//            case GRANTER1:
//                return (SuperBO) new ManageGranterBOImpl();
//            case INSURENCE1:
//                return (SuperBO) new ManageInsuranceBOImpl();
//
        }
        return null;
    }
}
