package lk.ijse.metleCrusher.bo.custom;

import lk.ijse.metleCrusher.bo.SuperBO;
import lk.ijse.metleCrusher.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String dto) throws SQLException, ClassNotFoundException;
}
