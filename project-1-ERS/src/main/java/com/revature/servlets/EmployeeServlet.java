package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.Reimbursements;
import com.revature.services.EmployeeService;

public class EmployeeServlet extends HttpServlet {
	
	private static Logger employeeServletLogger = Logger.getLogger(EmployeeServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			String[] splitURI = req.getRequestURI().split("/");
			
			employeeServletLogger.debug("Employee Split URI: " + Arrays.toString(splitURI));
			
			String[] tokens = Arrays.copyOfRange(splitURI, 3, splitURI.length);
			
			employeeServletLogger.debug(Arrays.toString(tokens));
			
			switch (tokens[0]) {
			case "viewInfo":
				getEmployeeInfo(req, resp, tokens);
				break;
			case "updateInfo":
				break;
			case "submitReimb":
				break;
			case "pendingReimbs":
				getPendingReimbsViaEmp(req, resp, tokens);
				break;
			case "resolvedReimbs":
				
				
			}

			
		}
		

	private void getEmployeeInfo(HttpServletRequest req, HttpServletResponse resp, String[] tokens) throws IOException, ServletException {
		
		Cookie cookies[] = req.getCookies();
		
		String empCookie = null;
		
		for (Cookie c : cookies) {
			if(c.getName().equals("employeeUser")) {
				empCookie = c.getValue();
			}
		}
		
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();
		
		EmployeeService employeeService = new EmployeeService();

		//GETS EMPLOYEE ID FROM THE COOKIE
		int employeeId = employeeService.getEmployeeInfo(empCookie).getEmpId();
		System.out.println("EMPLOYEE ID FROM COOKIE: " + employeeId);
		
		if(req.getMethod().equals("GET")) {
			
			if (tokens.length == 1) {
				String jsonEmployee = om.writeValueAsString(employeeService.getEmployeeInfo(empCookie));
				
				employeeServletLogger.info("EMPLOYEE JSON: " + jsonEmployee);
				
				pw.write(jsonEmployee);
			} 
		}
		
	}
	
	//VIEW PENDING REIMBURSEMENTS FROM EMPLOYEE PAGE
	private void getPendingReimbsViaEmp(HttpServletRequest req, HttpServletResponse resp, String[] tokens) throws IOException {
		
		Cookie cookies[] = req.getCookies();
		
		String empCookie = null;
		Reimbursements reimbService = new Reimbursements();
		
		for (Cookie c : cookies) {
			if(c.getName().equals("employeeUser")) {
				empCookie = c.getValue();
			}
		}
		
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();
		
		EmployeeService employeeService = new EmployeeService();

		//GETS EMPLOYEE ID FROM THE COOKIE
		int employeeId = employeeService.getEmployeeInfo(empCookie).getEmpId();
		System.out.println("EMPLOYEE ID FROM COOKIE: " + employeeId);
		
		if(req.getMethod().equals("GET")) {
			
			if (tokens.length == 1) {
				String jsonEmployee = om.writeValueAsString(reimbService.getSubmittedBy());
				
				employeeServletLogger.info("EMPLOYEE JSON: " + jsonEmployee);
				
				pw.write(jsonEmployee);
			} 
		}
	}
}
