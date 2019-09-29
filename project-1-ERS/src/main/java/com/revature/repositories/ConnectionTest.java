package com.revature.repositories;

import com.revature.model.Employee;

public class ConnectionTest {

	public static void main(String[] args) {
		//ERSConnectionUtil.getConnection();
		
		EmployeeDao test = new EmployeeDaoImpl();
		
		for (Employee e : test.getEmployees()) {
			System.out.println(e);
		}
		
		System.out.println(test.getEmployeeLogin("revature@gmail.com", "revaure"));

	}

}
