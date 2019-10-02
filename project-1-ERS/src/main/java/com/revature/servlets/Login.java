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

public class Login extends HttpServlet {

	private static Logger loginServletLogger = Logger.getLogger(LoginServlet.class);
	EmployeeService dbUser = new EmployeeService();
	Employee employeeUser = new Employee();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = resp.getWriter();
		String email = req.getParameter("userEmail");
		String password = req.getParameter("userPassword");

		loginServletLogger.info("Inputs from form: " + email + password);
		employeeUser = dbUser.getLoginCredentials(email, password);
//		if (dbUser.getLoginCredentials(email, password) != null) {
		
		if (employeeUser != null) {
			
//			HttpSession session = req.getSession();
			
			String empFirstName = employeeUser.getFirstName();
			Cookie cookie = new Cookie("employeeUser", empFirstName);
			resp.addCookie(cookie);
			
			resp.sendRedirect("authorize");
////			
//			loginServletLogger.info("USER: " + user);
//			loginServletLogger.info(session);
//	
//			session.setAttribute("employeeUser", user);
//			loginServletLogger.info("SESSION CONNECTED TO USER: " + session.getAttribute("employeeUser"));
//			
//			RequestDispatcher rs = req.getRequestDispatcher("index.html");
//			rs.include(req, resp);
			//resp.sendRedirect("user-portals/test-page.html");
			//resp.sendRedirect("user-portals/employee_page.html");
//			resp.sendRedirect("authorize");
			
		} else {
			pw.println("Username or Password Incorrect");
			resp.sendError(401, "Invalid Credentials");
			resp.sendRedirect("index.html");
		}
	}
}


//			// COOKIES
//			Cookie cookie = new Cookie("userEmail", Long.toString(1));
//			resp.addCookie(cookie);
//			Cookie cookies[] = req.getCookies();
//			String str = null;
//			EmployeeDao employeeDao = new EmployeeDaoImpl();
//			for (Cookie c : cookies) {
//				if (c.getName().equals("userEmail")) {
//					str = c.getValue();
//				}
//			}
//			resp.sendRedirect("user-portals/manager_page.html");

//
////			if(user.getManager()) {
////				System.out.println(user.getManager());
//////				RequestDispatcher rs = req.getRequestDispatcher("user-portals/manager_page.html");
//////				rs.forward(req, resp);
////				resp.sendRedirect("user-portals/manager_page.html");
////			} else {


////			RequestDispatcher rs = req.getRequestDispatcher("user-portals/employee_page.html");
////			rs.forward(req, resp);

	


	
