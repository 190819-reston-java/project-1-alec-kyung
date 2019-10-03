package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursements;
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

		ArrayList<Employee> employeesList = new ArrayList<Employee>();

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
	public Employee getEmployeeInfo(String email) {
		Employee user = null;

		PreparedStatement statement = null;
		ResultSet results = null;

		String query = "SELECT * FROM ers.employees WHERE email=?";
		
		try (Connection conn = ERSConnectionUtil.getConnection()){
			statement = conn.prepareStatement(query);
			statement.setString(1, email);

			if (statement.execute()) {
				results = statement.getResultSet();
				if (results.next()) {
					user = createEmployeeFromRS(results);
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ERSStreamCloser.close(results);
			ERSStreamCloser.close(statement);
		}
		return user;
	}

	@Override
	public Employee getEmployeeLogin(String email, String userPassword) {
		Employee user = null;

		PreparedStatement statement = null;
		ResultSet results = null;

		String query = "SELECT * FROM ers.employees WHERE email=? AND pwd = ?";
		
		try (Connection conn = ERSConnectionUtil.getConnection()){
			statement = conn.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, userPassword);
			if (statement.execute()) {
				results = statement.getResultSet();
				if (results.next()) {
					user = createEmployeeFromRS(results);
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ERSStreamCloser.close(results);
			ERSStreamCloser.close(statement);
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
		PreparedStatement statement = null;

		String query = "INSERT INTO ers.employees VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);";

		try (Connection conn = ERSConnectionUtil.getConnection()) {

			statement = conn.prepareStatement(query);
			statement.setString(1, eu.getEmail());
			statement.setString(2, eu.getEmpPwd());
			statement.setString(3, eu.getFirstName());
			statement.setString(4, eu.getLastName());
			statement.setLong(5, eu.getPhoneNumber());
			statement.setBoolean(6, eu.isManager());

			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			ERSStreamCloser.close(statement);
		}
		return true;
	}

	@Override
	public List<Employee> getAllEmployees() {

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
	public Employee isManager(int empId) {
		Employee user = null;

		PreparedStatement statement = null;
		ResultSet results = null;

		String query = "SELECT manager\r\n" + 
				"FROM ers.employees\r\n" + 
				"WHERE emp_id =?;";
		
		try (Connection conn = ERSConnectionUtil.getConnection()){
			statement = conn.prepareStatement(query);
			statement.setInt(1, empId);
			if (statement.execute()) {
				results = statement.getResultSet();
				if (results.next()) {
					user = createEmployeeFromRS(results);
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ERSStreamCloser.close(results);
			ERSStreamCloser.close(statement);
		}
		return user;
	}

	@Override
	public Employee getEmployeeID(String email) {
		Employee user = null;

		PreparedStatement statement = null;
		ResultSet results = null;

		String query = "SELECT emp_id FROM ers.employees WHERE email=?";
		
		try (Connection conn = ERSConnectionUtil.getConnection()){
			statement = conn.prepareStatement(query);
			statement.setString(1, email);

			if (statement.execute()) {
				results = statement.getResultSet();
				if (results.next()) {
					user = createEmployeeFromRS(results);
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ERSStreamCloser.close(results);
			ERSStreamCloser.close(statement);
		}
		return user;
	}
	
		//--A Manager can view all resolved requests from all employees and see which manager resolved it
		@Override
		public List<Employee> getResolvedReimMan() {
			PreparedStatement statement = null;
			ResultSet results = null;

//			String query = "SELECT emp_id, first_name, last_name\r\n" + 
//					"FROM ers.employees\r\n" + 
//					"FULL JOIN ers.reimbursements\r\n" + 
//					"ON ers.employees.emp_id = ers.reimbursements.resolved_by_id\r\n" + 
//					"WHERE ers.reimbursements.resolved_by_id IS NOT NULL;";
			
//			String query = "SELECT * FROM ers.reimbursements WHERE resolved_by_id IS NOT NULL;";
//			
			String query = "SELECT *\r\n" + 
			"FROM ers.employees\r\n" + 
			"FULL JOIN ers.reimbursements\r\n" + 
			"ON ers.employees.emp_id = ers.reimbursements.resolved_by_id\r\n" + 
			"WHERE ers.reimbursements.resolved_by_id IS NOT NULL;";

			List<Employee> employeesList = new ArrayList<Employee>();

			try (Connection conn = ERSConnectionUtil.getConnection()) {
				statement = conn.prepareStatement(query);
				results = statement.executeQuery();

				while (results.next()) {
					Employee empInfo = new Employee();
					
					empInfo.setEmpId(results.getInt("emp_id"));
//					empInfo.setEmail(results.getString("email"));
//					empInfo.setEmpPwd(results.getString("pwd"));
					empInfo.setFirstName(results.getString("first_name"));
					empInfo.setLastName(results.getString("last_name"));
//					empInfo.setPhoneNumber(results.getLong("phone_number"));
//					empInfo.setManager(results.getBoolean("manager"));
					
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
		public Employee isManager() {
			// TODO Auto-generated method stub
			return null;
		}

}
