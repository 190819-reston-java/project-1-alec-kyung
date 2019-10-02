package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.services.EmployeeService;

public class ManagerServlet extends HttpServlet {
	
	private static Logger managerServletLogger = Logger.getLogger(ManagerServlet.class);
	
	private EmployeeService employeeService = new EmployeeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] splitURI = req.getRequestURI().split("/");
		
		managerServletLogger.debug("Employee Split URI: " + Arrays.toString(splitURI));
		
		String[] tokens = Arrays.copyOfRange(splitURI, 2, splitURI.length);
		
		managerServletLogger.debug(Arrays.toString(tokens));
		
		switch (tokens[0]) {
		case "manager":
			getEmployees(req, resp, tokens);
			
		}
		
	}
	
	private void getEmployees(HttpServletRequest req, HttpServletResponse resp, String[] tokens) throws IOException, ServletException {
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();
		
		Employee employee = null;
		
		if(req.getMethod().equals("GET")) {
			
			managerServletLogger.info("GET from JS running");
			if (tokens.length == 1) {
				String jsonEmployees = om.writeValueAsString(employeeService.getEmployeesList());
				
				managerServletLogger.info(jsonEmployees);
				
				pw.write(jsonEmployees);
			} else {
				String jsonEmployee = om.writeValueAsString(employeeService.getEmployeeInfo(tokens[1]));
			}
		}
	}
	
//	//add maybe with reim service???
//	private void getPendingReim(HttpServletRequest req, HttpServletResponse resp, String[] tokens) throws IOException, ServletException {
//		ObjectMapper om = new ObjectMapper();
//		PrintWriter pw = resp.getWriter();
//		
//		Employee employee = null;
//		
//		if(req.getMethod().equals("GET")) {
//			
//			managerServletLogger.info("GET from JS running");
//			if (tokens.length == 1) {
//				String jsonEmployees = om.writeValueAsString(employeeService.get());
//				
//				managerServletLogger.info(jsonEmployees);
//				
//				pw.write(jsonEmployees);
//			} else {
//				String jsonEmployee = om.writeValueAsString(employeeService.getEmployeeInfo(tokens[1]));
//			}
//		}
//	}

}
