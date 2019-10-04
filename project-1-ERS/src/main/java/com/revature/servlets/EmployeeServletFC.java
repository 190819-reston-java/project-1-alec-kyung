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
import com.revature.services.UpdateInfo;

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
			updateEmployee(req, resp, tokens);
			break;
		case "submitReimb":
			employeeServletLogger.info("Entering submit reimbursements");
			submitReimb(req, resp, tokens);
			break;
		case "pendingReimbs":
			employeeServletLogger.info("Entering pending reimbursements");
			getPendingReimbsViaEmp(req, resp, tokens);
			break;
		case "resolvedReimbs":
			getResolvedReimbsViaEmp(req, resp, tokens);
			break;
		case "logout":
			logout(req, resp);
			break;
		}

	}

	private void submitReimb(HttpServletRequest req, HttpServletResponse resp, String[] tokens) throws IOException {
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();

		HttpSession session = req.getSession();
		Object employeeInfo = session.getAttribute("employeeSession");
		employeeServletLogger.debug("Employee Session Received: " + employeeInfo);

		// Gets employee ID from session
		Employee employee = (Employee) employeeInfo;
		int employeeId = employee.getEmpId();
		employeeServletLogger.debug("EMPLOYEE ID FROM SESSION: " + employeeId);

		String requestAmt = req.getParameter("request-amount");
		employeeServletLogger.debug("EMPLOYEE REQUEST AMOUNT: " + requestAmt);

		reimbDB.submitReimb(Integer.parseInt(requestAmt), employeeId);

		resp.setContentType("text/html;charset=UTF-8");

		// NEED TO WORK ON REDIRECTING BACK TO MAIN PAGE
//		resp.sendRedirect("viewInfo");

	}

	private void updateEmployee(HttpServletRequest req, HttpServletResponse resp, String[] tokens) throws IOException {
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();
		Employee employee = null;
		EmployeeService employeeService = new EmployeeService();

		if (req.getMethod().equals("PUT")) {
			employee = om.readValue(req.getReader(), Employee.class);
			if (tokens.length > 1) {
				try {
					employee.setEmpId(Integer.parseInt(tokens[1]));
				} catch (NumberFormatException e) {
					resp.sendError(400);
				}
			}

			if (!employeeService.updateEmployee(employee)) {
				resp.sendError(400, "Failed to update player");
			}
			pw.write("Successful update");

		}
		;

		// SESSION WAY
//		HttpSession session = req.getSession();
//		UpdateInfo update = om.readValue(req.getReader(), UpdateInfo.class);
//		Employee oldEmployee = (Employee) session.getAttribute("employeeSession");
//		employeeServletLogger.debug("Employee Session Received: " + oldEmployee);
//	
//		Employee newEmployee = new Employee(
//				oldEmployee.getEmpId(),
//				oldEmployee.getEmail(),
//				oldEmployee.getEmpPwd(),
//				update.getFirstName(),
//				update.getLastName(),
//				update.getEmail());
//		
//		if(employeeService.updateEmployee(newEmployee)) {
//			session.setAttribute("employee", newEmployee);
//		} else {
//			resp.sendError(400, "Failed to create player");
//		}
	}

	private void logout(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("Logout reached");
		if (req.getMethod().equals("GET")) {
			HttpSession session = req.getSession();
			session.invalidate();
		}
	}

	private void getEmployeeInfo(HttpServletRequest req, HttpServletResponse resp, String[] tokens)
			throws IOException, ServletException {
//		
		// COKIES WAY
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
		Employee employee = null;

		// SESSION WAY
		HttpSession session = req.getSession();
		Object employeeInfo = session.getAttribute("employeeSession");
		employeeServletLogger.debug("Employee Session Received: " + employeeInfo);

		employeeServletLogger.info("Retrieving Method from JS");
//		if (req.getMethod().equals("GET")) {

		switch (req.getMethod()) {
		case "GET":
			employeeServletLogger.debug(tokens.length);
			if (tokens.length == 1) {

				String jsonEmployee = om.writeValueAsString(employeeInfo);

				employeeServletLogger.info("EMPLOYEE JSON: " + jsonEmployee);

				pw.write(jsonEmployee);
			} else {
//				String jsonEmployee = om.writeValueAsString()
			}
		case "PUT":
			employee = om.readValue(req.getReader(), Employee.class);
		}

	}

	// VIEW PENDING REIMBURSEMENTS FROM EMPLOYEE PAGE
	private void getPendingReimbsViaEmp(HttpServletRequest req, HttpServletResponse resp, String[] tokens)
			throws IOException {
		employeeServletLogger.info("Reached getPendingReimbsViaEmp in Employee Servlet");
		PrintWriter pw = resp.getWriter();
		EmployeeService employeeService = new EmployeeService();

		// COOKIE WAY
//		Cookie cookies[] = req.getCookies();
//		String empCookie = null;
//
//		for (Cookie c : cookies) {
//			if(c.getName().equals("employeeUser")) {
//				empCookie = c.getValue();
//			}
//		}
		// Gets employee ID using cookies
//		int employeeId = employeeService.getEmployeeInfo(empCookie).getEmpId();
//		System.out.println("EMPLOYEE ID FROM COOKIE: " + employeeId);

		if (req.getMethod().equals("GET")) {

			System.out.println(tokens.length);

			if (tokens.length == 1) {

				// SESSION WAY
				HttpSession session = req.getSession();
				Object employeeInfo = session.getAttribute("employeeSession");
				employeeServletLogger.debug("Employee Session Received: " + employeeInfo);

				// Gets employee ID from session
				Employee employee = (Employee) employeeInfo;
				int employeeId = employee.getEmpId();
				employeeServletLogger.debug("EMPLOYEE ID FROM SESSION: " + employeeId);

				String jsonPendingReimbs = om
						.writeValueAsString(reimbDB.getPendingReimbursementsAsEmployee(employeeId));

				employeeServletLogger.info("Pending Reimbursements JSON: " + jsonPendingReimbs);

				pw.write(jsonPendingReimbs);

			}
		}
	}

	private void getResolvedReimbsViaEmp(HttpServletRequest req, HttpServletResponse resp, String[] tokens)
			throws IOException {
		employeeServletLogger.info("Reached getPendingReimbsViaEmp in Employee Servlet");
		PrintWriter pw = resp.getWriter();

		if (req.getMethod().equals("GET")) {

			System.out.println(tokens.length);

			if (tokens.length == 1) {

				// SESSION WAY
				HttpSession session = req.getSession();
				Object employeeInfo = session.getAttribute("employeeSession");
				employeeServletLogger.debug("Employee Session Received: " + employeeInfo);

				// Gets employee ID from session
				Employee employee = (Employee) employeeInfo;
				int employeeId = employee.getEmpId();
				employeeServletLogger.debug("EMPLOYEE ID FROM SESSION: " + employeeId);

				String jsonResolvedReimbs = om
						.writeValueAsString(reimbDB.getResolvedReimbursementsAsEmployee(employeeId));

				employeeServletLogger.info("Resolved Reimbursements JSON: " + jsonResolvedReimbs);

				pw.write(jsonResolvedReimbs);

			}
		}
	}
}
