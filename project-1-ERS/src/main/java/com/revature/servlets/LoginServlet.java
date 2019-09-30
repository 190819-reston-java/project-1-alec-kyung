package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeeDaoImpl;
import com.revature.services.EmployeeService;

public class LoginServlet extends HttpServlet {
	private static Logger loginServletLogger = Logger.getLogger(LoginServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		loginServletLogger.info("LOGIN SERVLET");
		
		PrintWriter pw = resp.getWriter();
		ObjectMapper om = new ObjectMapper();
		
		String email = req.getParameter("userEmail");
		String password = req.getParameter("userPAssword");
		
		EmployeeService employeeUser = new EmployeeService();
		
		Employee verifiedEmp = employeeUser.getLoginCredentials(email, password);
		System.out.println(verifiedEmp);
		System.out.println(email);
		System.out.println(password);
		if (verifiedEmp != null) {
			HttpSession session = req.getSession();
			session.setAttribute("user", verifiedEmp);
			System.out.println("Login successful!");
			resp.sendRedirect("user-portals/employee_page");
		
		} else {
			System.out.println("Invalid");
			String invalidMessage = "Invalid email/password";
			req.setAttribute("message", invalidMessage);
		}

	}
	
}
	
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		String[] splitURI = req.getRequestURI().split("/");
//		
//		loginServletLogger.debug("Employee Split URI: " + Arrays.toString(splitURI));
//		
//		String[] tokens = Arrays.copyOfRange(splitURI, 2, splitURI.length);
//		
//		loginServletLogger.debug(Arrays.toString(tokens));
//		
//		switch (tokens[0]) {
//		case "login":
//			getLoginCredentials(req, resp, tokens);
//		}
//	}
//
//	private void getLoginCredentials(HttpServletRequest req, HttpServletResponse resp, String[] tokens) throws IOException {
//		ObjectMapper om = new ObjectMapper();
//		PrintWriter pw = resp.getWriter();
//		String jsRequest = req.getMethod();
//		
//		if (jsRequest.equals("POST")) {
//			loginServletLogger.info("POST from JS");
//			String email = req.getParameter("userEmail");
//			String password = req.getParameter("userPassword");
//			
//			String jsonLogin = om.writeValueAsString(employeeUser.getLoginCredentials(email, password));
//			
//			if(jsonLogin != null) {
//				System.out.println("Login successful");
//			}
//		}
//		
//		
//	}
//		
//}
		
//		
//		String[] employeeCredentials = om.readValue(req.getInputStream(), String[].class);
//		loginServletLogger.info("Employee credentials from input stream: "+ employeeCredentials);
//		String email = employeeCredentials[0];
//		String userPwd = employeeCredentials[1];
//		
//		Employee authorizedEmployee = employeeUser.getLoginCredentials(email, userPwd);
//		System.out.println("Authorized Employee: " + authorizedEmployee);
//		
//		if (authorizedEmployee != null) {
//			HttpSession employeeSession = req.getSession();
//			employeeSession.setAttribute("authEmployee", authorizedEmployee);
//		}
		

