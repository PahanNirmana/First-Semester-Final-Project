package lk.ijse.metleCrusher.dao.custom.impl;

import lk.ijse.metleCrusher.dao.SQLUtil;
import lk.ijse.metleCrusher.dao.custom.CustomerDAO;
import lk.ijse.metleCrusher.db.DbConnection;
import lk.ijse.metleCrusher.dto.CustomerDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");
        ArrayList<CustomerDto> getAllCustomer=new ArrayList<>();
        while (rst.next()){
            CustomerDto customerDTO=new CustomerDto(rst.getString("id"),
                    rst.getString("name"), rst.getString("address"),rst.getString("tel"));
            getAllCustomer.add(customerDTO);
        }
        return getAllCustomer;
    }
    @Override
    public boolean save(CustomerDto dto) throws SQLException, ClassNotFoundException {
       return  SQLUtil.execute("INSERT INTO customer VALUES(?, ?, ?, ?)",
               dto.getId(),dto.getName(),dto.getAddress(),dto.getTel());
    }
    @Override
    public boolean update(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",
                dto.getId(),dto.getName(),dto.getAddress(),dto.getTel());
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE id = ?",id);
    }

}
