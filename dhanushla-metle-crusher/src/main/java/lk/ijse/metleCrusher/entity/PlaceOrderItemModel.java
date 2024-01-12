package lk.ijse.metleCrusher.entity;

import lk.ijse.metleCrusher.dao.custom.ItemDAO;
import lk.ijse.metleCrusher.dao.custom.OrderDAO;
import lk.ijse.metleCrusher.dao.custom.OrderDetailDAO;
import lk.ijse.metleCrusher.dao.custom.impl.ItemDAOImpl;
import lk.ijse.metleCrusher.dao.custom.impl.OrderDAOImpl;
import lk.ijse.metleCrusher.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.metleCrusher.db.DbConnection;
import lk.ijse.metleCrusher.dto.ItemDto;
import lk.ijse.metleCrusher.dto.OrderDTO;
import lk.ijse.metleCrusher.dto.OrderDetailDTO;
import lk.ijse.metleCrusher.dto.PlaceOrderDto;
import lk.ijse.metleCrusher.dto.tm.CartTm;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PlaceOrderItemModel {
   private OrderDAO orderDAO = new OrderDAOImpl();

   private ItemDAO itemDAO = new ItemDAOImpl();

   private OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

    public  boolean placeOrder(PlaceOrderDto dto) throws SQLException {

        Connection con = null;
        try{

            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            System.out.println(dto.getOrderId());
            System.out.println(dto.getDate());
            System.out.println(dto.getCustomerId());

            System.out.println(new OrderDTO(dto.getOrderId(),dto.getDate(),dto.getCustomerId()));

            boolean isOrderSaved = orderDAO.save(new OrderDTO(dto.getOrderId(),dto.getDate(),dto.getCustomerId()));
            System.out.println("1 :"+isOrderSaved);
            if (isOrderSaved){
                boolean isItemUpdated = false;
                for (CartTm tm: dto.getCartTmList()) {
                    System.out.println(new ItemDto(
                            tm.getCode(),
                            tm.getDescription(),
                            tm.getUnitPrice(),
                            tm.getQty()
                    ));
                    isItemUpdated = itemDAO.update(new ItemDto(
                                tm.getCode(),
                                tm.getDescription(),
                                tm.getUnitPrice(),
                                tm.getQty()
                    ));
                }
                System.out.println("2 :"+isItemUpdated);

                if (isItemUpdated){
                    boolean isOrderDetailsSaved = false;

                    String oId = dto.getOrderId();

                    for (CartTm tm: dto.getCartTmList()) {

                        isOrderDetailsSaved = orderDetailDAO.save(new OrderDetailDTO(oId,tm.getCode(),tm.getQty(),tm.getUnitPrice()));
                    }
                    System.out.println("3 :"+isOrderDetailsSaved);
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
