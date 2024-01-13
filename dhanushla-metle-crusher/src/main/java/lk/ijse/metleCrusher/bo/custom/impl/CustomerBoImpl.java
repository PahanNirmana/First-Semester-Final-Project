package lk.ijse.metleCrusher.bo.custom.impl;

import lk.ijse.metleCrusher.bo.custom.CustomerBO;
import lk.ijse.metleCrusher.dao.custom.CustomerDAO;
import lk.ijse.metleCrusher.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.metleCrusher.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBO {
    CustomerDAO customerDAO=new CustomerDAOImpl();
    @Override
    public ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }
    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(dto);
    }
    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(dto);
    }
    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }

}
