package com.revature.repositories;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.revature.model.Employee;
import com.revature.model.Reimbursements;
import com.revature.services.EmployeeService;
import com.revature.services.ReimbService;

public class ConnectionTest {

	public static void main(String[] args) {
		//ERSConnectionUtil.getConnection();
		
		
		EmployeeDao emps = new EmployeeDaoImpl();
		ReimbDao reimbs = new ReimbDaoImpl();
		ReimbService reimbsService = new ReimbService();
		EmployeeService employee = new EmployeeService();

		
//		Cookie cookies[] = req.getCookies();
		String user = null;
		
		
		for (Employee e : emps.getEmployees()) {
			System.out.println(e);
		}
		
//		System.out.println(reimbsService.getPendingReimList());
		
		
//		System.out.println(emps.getEmployeeInfo("test@test.com").getEmpId());
		

		
//		emps.createNewAccount(new Employee(0, "test@test.com", "testpwd", "User", "Test", 9879989998L, true));
//		
//	
//		System.out.println(reimbs.getAllReimbs());
//		

		System.out.println("============REIMBURSEMENT TESTS=================");
		for (Reimbursements r : reimbs.getReimbsById(2)) {
			System.out.println(r);
		}

//		for (Reimbursements r : reimbs.getReimbsById(2)) {
//			System.out.println();
//		}
		
		System.out.println("VIEW ALL RESOLVE: " + employee.getResolvedReimMan());
		

//		
		
		
		System.out.println("REIMBS BY ID: " + reimbs.getReimbsById(2));
//		
//		System.out.println("RESOLVED REIMBS: " + reimbs.getResolvedReimMan());

		//test.createNewAccount(new Customer(0, "Alison", "Wonderland4", "peace05", 7878, 20000));


	}

}
