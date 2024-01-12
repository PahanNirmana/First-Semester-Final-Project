package lk.ijse.metleCrusher.dao.custom.impl;

import lk.ijse.metleCrusher.dao.SQLUtil;
import lk.ijse.metleCrusher.dao.custom.OrderDetailDAO;
import lk.ijse.metleCrusher.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public ArrayList<OrderDetailDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        System.out.println("Lasi : "+dto);
        return SQLUtil.execute("INSERT INTO order_detail VALUES (?,?,?,?)",
                dto.getOid(),dto.getItemCode(),dto.getQty(),dto.getUnitPrice());
    }

    @Override
    public boolean update(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String dto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
