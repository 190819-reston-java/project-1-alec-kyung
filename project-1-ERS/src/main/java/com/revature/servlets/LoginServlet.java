package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeeDaoImpl;
import com.revature.services.EmployeeService;

public class LoginServlet extends HttpServlet {

	private static Logger loginServletLogger = Logger.getLogger(LoginServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		EmployeeService dbUser = new EmployeeService();

		
		String email = req.getParameter("userEmail");
		String password = req.getParameter("userPassword");

		loginServletLogger.info("Inputs from form: " + email + password);
		Employee employeeUser = dbUser.getLoginCredentials(email, password);
		
		if (employeeUser != null) {
			if (dbUser.getEmployeeInfo(email).getManager()) {
				//SESSION WAY
				HttpSession session = req.getSession();
				session.setAttribute("employeeSession", employeeUser);
				loginServletLogger.debug("Session set in LoginServlet" + employeeUser);	
				loginServletLogger.info("Redirect to Manager page");

				resp.sendRedirect("user-portals/manager_page.html");
			} else {
				//SESSION WAY
				HttpSession session = req.getSession();
				session.setAttribute("employeeSession", employeeUser);
				loginServletLogger.debug("Session set in LoginServlet" + employeeUser);	
				loginServletLogger.info("Redirect to Employee page");

				resp.sendRedirect("user-portals/employee_page.html");
				
			}
				
//			resp.sendRedirect("user-portals/employee_page.html");
		} else {
			pw.println("Username or Password Incorrect");
			resp.setContentType("text/html;charset=UTF-8");

		
			resp.sendRedirect("index.html");
		}
	}
}


	


	
