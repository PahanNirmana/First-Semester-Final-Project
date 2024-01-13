package lk.ijse.metleCrusher.bo.custom.impl;

import lk.ijse.metleCrusher.bo.custom.EmployeeBO;
import lk.ijse.metleCrusher.dao.custom.EmployeeDAO;
import lk.ijse.metleCrusher.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.metleCrusher.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBoImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    public ArrayList<EmployeeDto> getAllEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }
    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(dto);
    }
    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }
    @Override
    public boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(dto);
    }
    @Override
    public EmployeeDto searchEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(id);
    }


}
