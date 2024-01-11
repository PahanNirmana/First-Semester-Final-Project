package lk.ijse.metleCrusher.bo;

import lk.ijse.metleCrusher.bo.custom.impl.CustomerBoImpl;
import lk.ijse.metleCrusher.bo.custom.impl.ItemBoImpl;
import lk.ijse.metleCrusher.bo.custom.impl.PlaceOrderBoImpl;

public class BOFactory {

    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBOFactory(){
        return (boFactory==null)?boFactory=new BOFactory():boFactory;
    }
    public enum BOTypes{
        CUSTOMER,ITEM,PLACE_ORDER
    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBoImpl();
            case ITEM:
                return new ItemBoImpl();
            case PLACE_ORDER:
                return (SuperBO) new PlaceOrderBoImpl();
            default:
                return null;
        }
    }
}
