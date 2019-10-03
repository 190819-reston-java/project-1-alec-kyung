package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.services.EmployeeService;

public class AuthorizeServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger authorizeLogger = Logger.getLogger(Welcome.class);
	EmployeeService user = new EmployeeService();

	@SuppressWarnings("deprecation")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
//		Employee user = new Employee();

//		
//		Cookie cookies[] = req.getCookies();
//		
//		String verifiedUser = null;
//		
//		for (Cookie c : cookies) {
//			if(c.getName().equals("employeeUser")) {
//				verifiedUser = c.getValue();
//				authorizeLogger.info("Cookie set: " + verifiedUser);
//			}
//		}
		//SESSION
		HttpSession session = req.getSession();
		Employee userSession = new Employee();
		userSession = (Employee) session.getAttribute("employeeSession");
	
		PrintWriter out = resp.getWriter();
		out.print(userSession);
		
//		out.print("Welcome " + verifiedUser);
//		out.print(user.getEmployeeInfo(verifiedUser));
		
		resp.sendRedirect("user-portals/employee_page.html");
		
	}
	
}
			




