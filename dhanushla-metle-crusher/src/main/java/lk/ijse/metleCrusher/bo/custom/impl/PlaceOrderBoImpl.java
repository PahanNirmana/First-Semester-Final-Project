package lk.ijse.metleCrusher.bo.custom.impl;

import lk.ijse.metleCrusher.bo.custom.PlaceOrderBO;
import lk.ijse.metleCrusher.dao.custom.CustomerDAO;
import lk.ijse.metleCrusher.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.metleCrusher.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public class PlaceOrderBoImpl implements PlaceOrderBO {

    private CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public List<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public String generateNextOrderId() {
        return null;
    }
    @Override
    public CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }


}
