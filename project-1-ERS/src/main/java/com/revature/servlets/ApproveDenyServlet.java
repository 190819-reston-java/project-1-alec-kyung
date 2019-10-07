package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.repositories.ReimbDao;
import com.revature.repositories.ReimbDaoImpl;
import com.revature.services.EmployeeService;

public class ApproveDenyServlet extends HttpServlet {
	
	Logger approveLogger = Logger.getLogger(ApproveDenyServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		approveLogger.info("ApproveDenyServlet reached");
		
		PrintWriter pw = resp.getWriter();
		EmployeeService dbUser = new EmployeeService();
		ReimbDao dbReimb = new ReimbDaoImpl();
		
		HttpSession session = req.getSession();
		Employee managerSession = (Employee) session.getAttribute("managerSession");
		
//		Employee manager = (Employee) managerSession;
		int managerId = managerSession.getEmpId();
		approveLogger.debug("EMPLOYEE ID FROM SESSION: " + managerId);
		

		
		String reimbursementId = req.getParameter("reimbId");
		approveLogger.debug("Manager Reimb ID Input: " + reimbursementId);
		
		int reimbIdInt = Integer.parseInt(reimbursementId);
		approveLogger.debug("Converting to INT: " + reimbIdInt);

		
		String radio = req.getParameter("decide");
		approveLogger.debug("Manager approve/deny: " + radio);
		
	
		if (radio.equals("approved")) {
			approveLogger.info("Approved");
			dbReimb.resolve(radio, managerId, reimbIdInt);
//			req.r;;
			} else if (radio.equals("denied")){
				approveLogger.info("Denied");
				dbReimb.resolve(radio, managerId, reimbIdInt);

				req.getRequestDispatcher("user-portals/manager_page.html").forward(req, resp);;
			} else {
				approveLogger.info("No decision");
				req.getRequestDispatcher("user-portals/manager_page.html").forward(req, resp);;

			}
		
	}
}
