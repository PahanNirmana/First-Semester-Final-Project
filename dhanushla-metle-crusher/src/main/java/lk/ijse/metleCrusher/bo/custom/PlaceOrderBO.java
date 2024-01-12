package lk.ijse.metleCrusher.bo.custom;

import lk.ijse.metleCrusher.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBO {

    List<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;

    String generateNextOrderId();
}
