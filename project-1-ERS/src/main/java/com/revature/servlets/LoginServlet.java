package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.services.EmployeeService;

public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmployeeService employeeService = new EmployeeService();
		ObjectMapper om = new ObjectMapper();
		
		String[] employeeCredentials = om.readValue(req.getInputStream(), String[].class);
		System.out.println("Employee credentials from input stream: "+ employeeCredentials);
		String email = employeeCredentials[0];
		String userPwd = employeeCredentials[1];
		
		Employee authorizedEmployee = employeeService.getLoginCredentials(email, userPwd);
		System.out.println("Authorized Employee: " + authorizedEmployee);
		
		if (authorizedEmployee != null) {
			HttpSession employeeSession = req.getSession();
			employeeSession.setAttribute("authEmployee", authorizedEmployee);
		}
		
	
	}

}
