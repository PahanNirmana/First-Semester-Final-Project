package lk.ijse.metleCrusher.entity;

import lk.ijse.metleCrusher.db.DbConnection;
import lk.ijse.metleCrusher.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    public EmployeeDto searchEmployee(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM employee WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        EmployeeDto dto = null;

        if(resultSet.next()) {
            String emp_id = resultSet.getString(1);
            String emp_nic = resultSet.getString(2);
            String emp_name = resultSet.getString(3);
            String emp_address = resultSet.getString(4);
            String emp_gander = resultSet.getString(5);
            String emp_tel = resultSet.getString(6);
            double emp_salary = resultSet.getDouble(7);

            dto = new EmployeeDto(emp_id, emp_nic, emp_name, emp_address,emp_gander,emp_tel,emp_salary);
        }
        return dto;
    }

    public boolean saveEmployee(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Employeee VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getNic());
        pstm.setString(3, dto.getName());
        pstm.setString(4, dto.getAddress());
        pstm.setString(5, dto.getGander());
        pstm.setString(6, dto.getTel());
        pstm.setDouble(7, dto.getSalary());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public List<EmployeeDto> getAllEmployee() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employeee";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<EmployeeDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            boolean add = dtoList.add(
                    new EmployeeDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getDouble(7)
                    )
            );
        }
        return dtoList;
    }

    public boolean deleteEmployee(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM employeee WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public boolean updateEmployee(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE employeee SET name = ?, address = ?, gander = ?, nic = ?, salary = ?, tel = ? WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getNic());
        pstm.setString(4, dto.getGander());
        pstm.setString(5, dto.getTel());
        pstm.setString(6, dto.getTel());
        pstm.setDouble(7, dto.getSalary());

        return pstm.executeUpdate() > 0;
    }
}
