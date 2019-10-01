package com.revature.repositories;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursements;

public interface EmployeeDao {
	
	List<Employee> getEmployees();
	Employee getEmployeeInfo(String email);
	Employee getEmployeeLogin(String email, String userPwd);
	
	Employee isManager();
	
	//Employee getResolvedReimb();
	
	Reimbursements getResolvedReimb();
	
	//MANAGER MEtHODS
	List<Employee> getAllEmployees();
	
	boolean updateAccount(Employee eu);
	boolean createNewAccount(Employee eu);

}
