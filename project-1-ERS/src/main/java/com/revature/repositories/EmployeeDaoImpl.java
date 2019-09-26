package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.util.ERSConnectionUtil;
import com.revature.util.ERSStreamCloser;

public class EmployeeDaoImpl implements EmployeeDao {

	private static Employee createEmployeeFromRS(ResultSet results) throws SQLException {
		return new Employee(results.getInt("employee_id"), results.getString("employee_first_name"),
				results.getString("employee_last_name"), results.getLong("phone_number"), results.getString("email"), results.getString("user_password")
				);
	}

	@Override
	public List<Employee> getEmployees() {
		
		PreparedStatement statement = null;
		ResultSet results = null;

		String query = "SELECT * FROM project1.employee_info;";

		List<Employee> employeesList = new ArrayList<Employee>();

		try (Connection conn = ERSConnectionUtil.getConnection()) {
			statement = conn.prepareStatement(query);
			results = statement.executeQuery();

			while (results.next()) {
				employeesList.add(createEmployeeFromRS(results));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ERSStreamCloser.close(results);
			ERSStreamCloser.close(statement);
			System.out.println("Close stream");
		}

		return employeesList;
	}

	@Override
	public Employee getEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployeeLogin(String email, String userPassword) {
		Employee user = null;

		PreparedStatement statement = null;
		ResultSet results = null;
		String query = "SELECT * FROM project1.employee_info WHERE email=? AND user_password = ?";

		return null;
	}

	@Override
	public boolean updateAccount(Employee eu) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createNewAccount(Employee eu) {
		// TODO Auto-generated method stub
		return false;
	}

}
