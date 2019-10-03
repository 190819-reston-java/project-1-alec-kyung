package com.revature.repositories;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.revature.model.Employee;
import com.revature.model.Reimbursements;
import com.revature.services.EmployeeService;

public class ConnectionTest {

	public static void main(String[] args) {
		//ERSConnectionUtil.getConnection();
		
		HttpServletRequest req = null;
		ReimbDao reimbs = new ReimbDaoImpl();
		
//		Cookie cookies[] = req.getCookies();
		String user = null;
		EmployeeService employee = new EmployeeService();
		
		EmployeeDao emps = new EmployeeDaoImpl();
		
		for (Employee e : emps.getEmployees()) {
			System.out.println(e);
		}
		
//		System.out.println(emps.getEmployeeInfo("test@test.com").getEmpId());
		

		
//		emps.createNewAccount(new Employee(0, "test@test.com", "testpwd", "User", "Test", 9879989998L, true));
//		
//	
//		System.out.println(reimbs.getAllReimbs());
//		
		for (Reimbursements r : reimbs.getReimbsById(2)) {
			System.out.println(r);
		}
//		
		//test.createNewAccount(new Customer(0, "Alison", "Wonderland4", "peace05", 7878, 20000));


	}

}
