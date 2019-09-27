package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.services.EmployeeService;

public class ManagerServlet extends HttpServlet {
	
	private EmployeeService employeeService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] splitURI = req.getRequestURI().split("/");
		System.out.println("EMP SPLIT URI: " + Arrays.toString(splitURI));
		
		String[] tokens = Arrays.copyOfRange(splitURI, 1, splitURI.length);
		
		System.out.println(Arrays.toString(tokens));
		
		switch (tokens[1]) {
		case "manager":
			getEmployees(req, resp, tokens);
		}
		
	}
	
	private void getEmployees(HttpServletRequest req, HttpServletResponse resp, String[] tokens) throws IOException, ServletException {
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();
		
		Employee employee = null;
		
		if(req.getMethod() == "GET") {
			System.out.println("GET from JS");
			if (tokens.length == 1) {
				String jsonEmployees = om.writeValueAsString(employeeService.getEmployeesList());
				pw.write(jsonEmployees);
			}
		}
		
	
		
		
		
	}

}
