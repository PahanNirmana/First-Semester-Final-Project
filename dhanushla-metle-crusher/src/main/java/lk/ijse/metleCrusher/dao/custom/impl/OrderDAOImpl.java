package lk.ijse.metleCrusher.dao.custom.impl;

import lk.ijse.metleCrusher.dao.SQLUtil;
import lk.ijse.metleCrusher.dao.custom.OrderDAO;
import lk.ijse.metleCrusher.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Orders VALUES(?,?,?)",
                dto.getOrderId(),dto.getCustomerId(),dto.getOrderDate());
    }

    @Override
    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
