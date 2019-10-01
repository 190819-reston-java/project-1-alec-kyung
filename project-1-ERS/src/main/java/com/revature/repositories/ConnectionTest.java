package com.revature.repositories;

import com.revature.model.Employee;

public class ConnectionTest {

	public static void main(String[] args) {
		//ERSConnectionUtil.getConnection();
		
		Employee testService = new Employee();
		
		EmployeeDao test = new EmployeeDaoImpl();
		
		for (Employee e : test.getEmployees()) {
			System.out.println(e);
		}
		
//		System.out.println(test.getEmployeeLogin("revature@gmail.com", "revature"));
//		
//		System.out.println(test.getResolvedReimb());
		
		System.out.println(test.isManager());
		

	}

}
