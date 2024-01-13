package lk.ijse.metleCrusher.bo.custom;

import lk.ijse.metleCrusher.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO {
    ArrayList<EmployeeDto> getAllEmployee() throws SQLException, ClassNotFoundException;

    boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;

    EmployeeDto searchEmployee(String id) throws SQLException, ClassNotFoundException;
}
