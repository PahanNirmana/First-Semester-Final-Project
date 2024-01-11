package lk.ijse.metleCrusher.dao;

import lk.ijse.metleCrusher.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;
    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)? daoFactory = new DAOFactory():daoFactory;
    }
    public enum DAOTypes{
        CUSTOMER,ITEM,ORDERS,ORDER_DETAIL,QUERY
    }
    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDERS:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            case QUERY:
                return (SuperDAO) new QueryDAOImpl();
            default:
                return null;
        }
    }

}
