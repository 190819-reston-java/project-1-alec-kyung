package com.revature.repositories;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDao {
	
	List<Employee> getEmployees();
	Employee getEmail(String email);
	Employee getEmployeeLogin(String email, String userPwd);
	boolean updateAccount(Employee eu);
	boolean createNewAccount(Employee eu);

}
