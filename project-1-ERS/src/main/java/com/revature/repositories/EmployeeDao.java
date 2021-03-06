package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursements;

public interface EmployeeDao {
	
	List<Employee> getEmployees();
	Employee getEmployeeInfo(String email);
	Employee getEmployeeLogin(String email, String userPwd);
	Employee isManager();
	Employee getEmployeeID(String email);
	
	//MANAGER MEtHODS
	List<Employee> getAllEmployees();
	List<Employee> getResolvedReimMan();
	
	boolean updateAccount(Employee eu);
	boolean createNewAccount(Employee eu);
	

}
