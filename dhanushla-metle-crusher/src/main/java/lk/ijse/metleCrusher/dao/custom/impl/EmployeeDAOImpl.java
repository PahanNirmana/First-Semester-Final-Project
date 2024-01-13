package lk.ijse.metleCrusher.dao.custom.impl;

import lk.ijse.metleCrusher.dao.SQLUtil;
import lk.ijse.metleCrusher.dao.custom.EmployeeDAO;
import lk.ijse.metleCrusher.db.DbConnection;
import lk.ijse.metleCrusher.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<EmployeeDto> getAll() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM employeee");
        ArrayList<EmployeeDto> getAllEmployee  =new ArrayList<>();
        while (rst.next()){
            EmployeeDto employeeDto=new EmployeeDto(rst.getString("id"),
                    rst.getString("nic"), rst.getString("name"),rst.getString("address"),rst.getString("gander"),rst.getString("tel"),rst.getDouble("salary"));
            getAllEmployee.add(employeeDto);
        }
        return getAllEmployee;
    }

    @Override
    public boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employeee VALUES(?, ?, ?, ?, ?, ?, ?)",
        dto.getId(),dto.getNic(),dto.getName(),dto.getAddress(),dto.getGander(),dto.getTel(),dto.getSalary());
    }

    @Override
    public boolean update(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employeee SET name = ?, address = ?, gander = ?, nic = ?, salary = ?, tel = ? WHERE id = ?",
                dto.getId(),dto.getNic(),dto.getName(),dto.getAddress(),dto.getGander(),dto.getTel(),dto.getSalary());

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employeee WHERE id = ?",id);
    }

    @Override
    public EmployeeDto search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee WHERE id = ?",id);
        resultSet.next();
        return new EmployeeDto(id + "", resultSet.getString("nic"), resultSet.getString("name"),resultSet.getString("address"),resultSet.getString("gander"),resultSet.getString("tel"),resultSet.getDouble("salary"));
    }
}
