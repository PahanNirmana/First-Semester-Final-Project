package lk.ijse.metleCrusher.dao.custom.impl;

import lk.ijse.metleCrusher.dao.SQLUtil;
import lk.ijse.metleCrusher.dao.custom.ItemDAO;
import lk.ijse.metleCrusher.db.DbConnection;
import lk.ijse.metleCrusher.dto.ItemDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList<ItemDto> getAll() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");
        ArrayList<ItemDto> getAllItem=new ArrayList<>();
        while (rst.next()) {
            ItemDto itemDTO=new ItemDto(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getInt(4));
            getAllItem.add(itemDTO);
        }
        return getAllItem;
    }


    @Override
    public boolean save(ItemDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO item VALUES(?, ?, ?, ?)",
                dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand());
    }

    @Override
    public boolean update(ItemDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Item SET description=?, unit_price=?, qty_on_hand=? WHERE code=?",
                dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getCode());

    }

    @Override
    public boolean delete(String dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM item WHERE code = ?");
    }
}
