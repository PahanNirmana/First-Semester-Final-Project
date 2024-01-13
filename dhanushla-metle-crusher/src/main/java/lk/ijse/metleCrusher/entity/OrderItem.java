package lk.ijse.metleCrusher.entity;

import lk.ijse.metleCrusher.db.DbConnection;
import lk.ijse.metleCrusher.dto.tm.CartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrderItem {

//    public static boolean saveOrderDetails(String orderId, List<CartTm> cartTmList) throws SQLException, ClassNotFoundException {
//        for (CartTm tm : cartTmList) {
//            if (!saveOrderDetails(orderId, tm)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static boolean saveOrderDetails(String orderId, CartTm tm) throws SQLException, SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "INSERT INTO order_detail VALUES(?, ?, ?, ?)";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        pstm.setString(1, orderId);
//        pstm.setString(2, tm.getCode());
//        pstm.setInt(3, tm.getQty());
//        pstm.setDouble(4, tm.getUnitPrice());
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static boolean saveOrderDetails(String orderId, String customerId, LocalDate date) throws SQLException {
//
//        return false;
//
//    }

  /*  public static boolean saveOrderDetails(String orderId, String itemCode, LocalDate date, double unitPrice, int qty, String customerId, String customerName, String description, double netTotal) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO order_detail VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, orderId);
        pstm.setString(2, itemCode);
        pstm.setInt(3, qty);
        pstm.setDouble(4, unitPrice);
        pstm.setString(5, customerId);
        pstm.setString(6, customerName);
        pstm.setDate(7, Date.valueOf(date));
        pstm.setString(8,description);
        pstm.setDouble(9,netTotal);

        return pstm.executeUpdate() > 0;

    }*/
}
