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
		return new Employee(results.getInt("emp_id"), results.getString("email"), results.getString("pwd"),
				results.getString("first_name"), results.getString("last_name"), results.getLong("phone_number"),
				results.getBoolean("manager"));
	}

	@Override
	public List<Employee> getEmployees() {

		PreparedStatement statement = null;
		ResultSet results = null;

		String query = "SELECT * FROM ers.employees;";

		List<Employee> employeesList = new ArrayList<Employee>();

		try (Connection conn = ERSConnectionUtil.getConnection()) {
			statement = conn.prepareStatement(query);
			results = statement.executeQuery();

			while (results.next()) {
				Employee empInfo = new Employee();
				
				empInfo.setEmpId(results.getInt("emp_id"));
				empInfo.setEmail(results.getString("email"));
				empInfo.setEmpPwd(results.getString("pwd"));
				empInfo.setFirstName(results.getString("first_name"));
				empInfo.setLastName(results.getString("last_name"));
				empInfo.setPhoneNumber(results.getLong("phone_number"));
				empInfo.setManager(results.getBoolean("manager"));
				
				employeesList.add(empInfo);

				//employeesList.add(createEmployeeFromRS(results));
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

		String query = "SELECT * FROM ers.employees WHERE email=? AND pwd = ?";

			while (results.next()) {

				//user.add(createEmployeeFromRS(results));
			} catch (SQLException e) {
			e.printStackTrace();
			} finally {
			ERSStreamCloser.close(results);
			ERSStreamCloser.close(statement);
			System.out.println("Close stream");
		
		}
		
		return user;
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
