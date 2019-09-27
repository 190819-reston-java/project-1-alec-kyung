package com.revature.services;

import java.util.List;

import com.revature.model.Employee;
import com.revature.repositories.EmployeeDao;

public class EmployeeService {
	
	private EmployeeDao employeeDao;
	
	public List<Employee> getEmployeesList(){
		return employeeDao.getEmployees();
	}
	
	public Employee getLoginCredentials(String email, String userPwd) {
		return employeeDao.getEmployeeLogin(email, userPwd);
	}

}
