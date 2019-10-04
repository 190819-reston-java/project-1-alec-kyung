package com.revature.services;

import java.util.List;

import com.revature.model.Employee;
import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeeDaoImpl;

public class EmployeeService {
	
	private EmployeeDao employeeDao = new EmployeeDaoImpl();
	
	public List<Employee> getEmployeesList(){
		return employeeDao.getEmployees();
	}
	
	public Employee getLoginCredentials(String email, String userPwd) {
		return employeeDao.getEmployeeLogin(email, userPwd);
	}
	
	public Employee getEmployeeInfo(String email) {
		return employeeDao.getEmployeeInfo(email);
	}
	
//	public boolean getManager() {
//		return employeeDao.getManager();
//	}
	
	public List<Employee> getResolvedReimMan() {
	return  employeeDao.getResolvedReimMan();
	}

}
