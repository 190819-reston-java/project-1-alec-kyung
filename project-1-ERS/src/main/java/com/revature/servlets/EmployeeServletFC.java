package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.Reimbursements;
import com.revature.repositories.ReimbDao;
import com.revature.repositories.ReimbDaoImpl;
import com.revature.services.EmployeeService;
import com.revature.services.ReimbService;

public class EmployeeServletFC extends HttpServlet {
	
	private static Logger employeeServletLogger = Logger.getLogger(EmployeeServletFC.class);
	private Reimbursements reimb = new Reimbursements();
	private ReimbDao reimbDB = new ReimbDaoImpl();
	ReimbService reimbService = new ReimbService();
	ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String[] splitURI = req.getRequestURI().split("/");
			employeeServletLogger.debug("Employee Split URI: " + Arrays.toString(splitURI));
			String[] tokens = Arrays.copyOfRange(splitURI, 3, splitURI.length);
			employeeServletLogger.debug(Arrays.toString(tokens));
			
			switch (tokens[0]) {
			case "viewInfo":
				employeeServletLogger.info("Entering employee info");
				getEmployeeInfo(req, resp, tokens);
				break;
			case "updateInfo":
				employeeServletLogger.info("Entering update info");
				break;
			case "submitReimb":
				employeeServletLogger.info("Entering submit reimbursements");
				break;
			case "pendingReimbs":
				employeeServletLogger.info("Entering pending reimbursements");
				getPendingReimbsViaEmp(req, resp, tokens);
				break;
			case "resolvedReimbs":
				break;
			}

			
		}
		

	private void getEmployeeInfo(HttpServletRequest req, HttpServletResponse resp, String[] tokens) throws IOException, ServletException {
//		
		//COKIES WAY
//		Cookie cookies[] = req.getCookies();
//		String empCookie = null;
//		for (Cookie c : cookies) {
//			if(c.getName().equals("employeeUser")) {
//				empCookie = c.getValue();
//			}
//		}
				
//		EmployeeService employeeService = new EmployeeService();
//		employeeService = employeeInfo;
		
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();
		
		//SESSION WAY
		HttpSession session = req.getSession();
		Object employeeInfo = session.getAttribute("employeeSession");
		employeeServletLogger.debug("Employee Session Received: " + employeeInfo);
		
		employeeServletLogger.info("Retrieving Method from JS");
		if(req.getMethod().equals("GET")) {
			employeeServletLogger.debug(tokens.length);
			if (tokens.length == 1) {
//				String jsonEmployee = om.writeValueAsString(employeeService.getEmployeeInfo(empCookie));
				
				String jsonEmployee = om.writeValueAsString(employeeInfo);
				
				employeeServletLogger.info("EMPLOYEE JSON: " + jsonEmployee);
				
				pw.write(jsonEmployee);
			} 
		}
		
	}
	
	//VIEW PENDING REIMBURSEMENTS FROM EMPLOYEE PAGE
	private void getPendingReimbsViaEmp(HttpServletRequest req, HttpServletResponse resp, String[] tokens) throws IOException {
		employeeServletLogger.info("Reached getPendingReimbsViaEmp in Employee Servlet");
		PrintWriter pw = resp.getWriter();
		EmployeeService employeeService = new EmployeeService();

		//COOKIE WAY
//		Cookie cookies[] = req.getCookies();
//		String empCookie = null;
//
//		for (Cookie c : cookies) {
//			if(c.getName().equals("employeeUser")) {
//				empCookie = c.getValue();
//			}
//		}
		//Gets employee ID using cookies
//		int employeeId = employeeService.getEmployeeInfo(empCookie).getEmpId();
//		System.out.println("EMPLOYEE ID FROM COOKIE: " + employeeId);

		
		if(req.getMethod().equals("GET")) {

			
			System.out.println(tokens.length);
			
			if (tokens.length == 1) {
				
				// SESSION WAY
				HttpSession session = req.getSession();
				Object employeeInfo = session.getAttribute("employeeSession");
				employeeServletLogger.debug("Employee Session Received: " + employeeInfo);
			

				//Gets employee ID from session
				Employee employee = (Employee) employeeInfo;
				int employeeId = employee.getEmpId();
				employeeServletLogger.debug("EMPLOYEE ID FROM SESSION: " + employeeId);
					String jsonPendingReimbs = om.writeValueAsString(reimbDB.getReimbsById(employeeId));
					
					employeeServletLogger.info("Pending Reimbursements JSON: " + jsonPendingReimbs);
					
					pw.write(jsonPendingReimbs);

				
			} 
		}
	}
}
