package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeeDaoImpl;
import com.revature.repositories.ReimbDao;
import com.revature.repositories.ReimbDaoImpl;
import com.revature.services.EmployeeService;

public class ManagerServletFC extends HttpServlet {
	
	private static Logger managerServletLogger = Logger.getLogger(ManagerServletFC.class);
	
	private ReimbDao dbReimbs = new ReimbDaoImpl();
	private ObjectMapper om = new ObjectMapper();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] splitURI = req.getRequestURI().split("/");
		
		managerServletLogger.debug("Employee Split URI: " + Arrays.toString(splitURI));
		
		String[] tokens = Arrays.copyOfRange(splitURI, 3, splitURI.length);
		
		managerServletLogger.debug(Arrays.toString(tokens));
		
		switch (tokens[0]) {
		case "get-employees":
			getEmployees(req, resp, tokens);
			break;
		case "select-reimb":
			selectReimbByEmployeeId(req, resp, tokens);
			break;
		case "approve-deny":
			approveReimbs(req, resp, tokens);
			break;
		case "view-all-pending-reimbs":
			viewAllPendingReimbs(req, resp, tokens);
			break;
		case "view-resolved-reimbs":
			viewResolvedReimbs(req, resp, tokens);
			break;
		case "logout":
			logout(req, resp);
			break;
		}
		
	}
	


	private void logout(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("Logout reached");

		if (req.getMethod().equals("GET")) {
			HttpSession session = req.getSession();
			session.getAttribute("employeeSession");
			session.invalidate();
		}
	}



	private void getEmployees(HttpServletRequest req, HttpServletResponse resp, String[] tokens) throws IOException, ServletException {
//		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();
		EmployeeService employeeService = new EmployeeService();
		
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
	
	private void selectReimbByEmployeeId(HttpServletRequest req, HttpServletResponse resp, String[] tokens) throws IOException {		
		
		PrintWriter pw = resp.getWriter();

		if(req.getMethod().equals("GET")) {
			int emp = Integer.parseInt(req.getParameter("viewRequestEmpId"));
			managerServletLogger.debug("Input of Employee ID from Manager: " + emp);
//			int managerInputEmployeeID = Integer.parseInt(req.getParameter("viewRequestEmpId"));
//			int managerInputEmployeeID = Integer.parseInt(emp);
//			managerServletLogger.debug(managerInputEmployeeID);
//			Employee employee = new Employee();
			String jsonReimbByEmployeeId = om.writeValueAsString(dbReimbs.getReimbsById(emp));
			
			managerServletLogger.info(jsonReimbByEmployeeId);
			
			
			pw.write(jsonReimbByEmployeeId);
		}
		
	}
	

	private void approveReimbs(HttpServletRequest req, HttpServletResponse resp, String[] tokens) {
		
		if(req.getMethod().equals("POST")) {
			
		}
		
	}
	
//	if(submitType.equals("aprove_deny")) {
//		String reinbursement_id = req.getParameter("id");
//		String radio = req.getParameter("decide");
//		int idInt = Integer.parseInt(reinbursement_id);
//		if("Approved".equals(radio)) {
//			ReimbursementDao reimbursementDao = new ReimbursementDaoJDBC();
//			reimbursementDao.requestAccepted(idInt);
//			req.getRequestDispatcher("approve_deny_reimbursements.html").forward(req, resp);
//		}else {
//			ReimbursementDao reimbursementDao = new ReimbursementDaoJDBC();
//			reimbursementDao.requestDenied(idInt);
//			req.getRequestDispatcher("approve_deny_reimbursements.html").forward(req, resp);
//		}
		
	private void viewAllPendingReimbs(HttpServletRequest req, HttpServletResponse resp, String[] tokens) throws IOException {
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();
//		ReimbDao dbReimbs = new ReimbDaoImpl();
		
		
		if(req.getMethod().equals("GET")) {
			
			
			managerServletLogger.info("GET from JS running");
			if (tokens.length == 1) {
				String jsonEmployees = om.writeValueAsString(dbReimbs.getAllPendingReimbursementsAsManager());
				
				managerServletLogger.info(jsonEmployees);
				
				pw.write(jsonEmployees);
			} 
		}
	}


	private void viewResolvedReimbs(HttpServletRequest req, HttpServletResponse resp, String[] tokens) throws IOException {
		managerServletLogger.info("Reached Resolved Reimbursements for Manager");
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();
//		ReimbDao dbReimbs = new ReimbDaoImpl();
		
		if(req.getMethod().equals("GET")) {
			
			managerServletLogger.info("GET from JS running");
			if (tokens.length == 1) {
				String jsonResolved = om.writeValueAsString(dbReimbs.getAllResolvedReimbursementsAsManager());
				
				managerServletLogger.debug(jsonResolved);
				
				pw.write(jsonResolved);
			} 
		}
	}

}
