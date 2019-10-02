package com.revature.repositories;

import com.revature.model.Employee;
import com.revature.model.Reimbursements;

public class ConnectionTest {

	public static void main(String[] args) {
		//ERSConnectionUtil.getConnection();
		
		ReimbDao reimbs = new ReimbDaoImpl();

		
		EmployeeDao emps = new EmployeeDaoImpl();
		
		for (Employee e : emps.getEmployees()) {
			System.out.println(e);
		}
		
//		emps.createNewAccount(new Employee(0, "test@test.com", "testpwd", "User", "Test", 9879989998L, true));
//		
//	
//		System.out.println(reimbs.getAllReimbs());
//		
//		for (Reimbursements r : reimbs.getReimbsByStatus("resolved")) {
//			System.out.println(r);
//		}
//		
		//test.createNewAccount(new Customer(0, "Alison", "Wonderland4", "peace05", 7878, 20000));


	}

}
