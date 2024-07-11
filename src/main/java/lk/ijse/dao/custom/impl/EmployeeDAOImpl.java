package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployees = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employee");
        while (resultSet.next()){
            Employee employee = new Employee(resultSet.getString("empId"), resultSet.getString("name"), resultSet.getString("contact"), resultSet.getString("address"));
            allEmployees.add(employee);
        }
        return allEmployees;
    }

    @Override
    public boolean add(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employee (empId, name, contact, address) VALUES (?,?,?,?)", entity.getEmpId(), entity.getName(), entity.getContact(), entity.getAddress());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Employee SET name=?, contact=?, address=? WHERE empId=?", entity.getName(), entity.getContact(), entity.getAddress(), entity.getEmpId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT empId FROM Employee WHERE empId=?", id);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT empId FROM Employee ORDER BY empId DESC LIMIT 1;");
        if (resultSet.next()) {
            String id = resultSet.getString("empId");
            int neeEmpId = Integer.parseInt(id.replace("E00-", "")) + 1;
            return String.format("E00-%03d", neeEmpId);
        } else {
            return "E00-001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Employee WHERE empId = ?", id);
    }

//    @Override;
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employee WHERE empId=?", id + "");
        resultSet.next();
        return new Employee(id + "", resultSet.getString("name"), resultSet.getString("contact"), resultSet.getString("address"));
    }

}


























