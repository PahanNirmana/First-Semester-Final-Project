package lk.ijse.metleCrusher.entity;

import lk.ijse.metleCrusher.db.DbConnection;
import lk.ijse.metleCrusher.dto.PlaceOrderDto;
import lk.ijse.metleCrusher.dto.tm.CartTm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderItemModel {
    public static boolean placeOrder(PlaceOrderDto dto) throws SQLException {
        Connection con = null;
        try{

            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);


            boolean isOrderSaved = OrderModel.saveOrder(dto.getOrderId(), dto.getCustomerId(),dto.getDate());
            if (isOrderSaved){
                boolean isItemUpdated = updateItem(dto.getCartTmList());
                if (isItemUpdated){
                    boolean isOrderDetailsSaved = OrderItem.saveOrderDetails(dto.getOrderId(), dto.getCartTmList());
                    if (isOrderDetailsSaved){

                        con.commit();
                        return true;
                    }else {
                        con.rollback();
                    }
                }else {
                    con.rollback();
                }
            }else {
                con.rollback();
            }

        } catch (SQLException | ClassNotFoundException e) {
            if (con != null) con.rollback();
            e.printStackTrace();
        }finally {
            if (con != null) con.setAutoCommit(true);
        }

        return false;
    }

    private static boolean updateItem(List<CartTm> cartTmList) throws SQLException, ClassNotFoundException {
        for (CartTm tm : cartTmList) {
            boolean isUpdateItem = ItemModel.updateQty(tm.getCode(), tm.getQty());
            if (!isUpdateItem){
                return false;
            }
        }
        return true;
    }
}
