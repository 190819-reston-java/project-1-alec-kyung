package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.services.EmployeeService;

public class Login extends HttpServlet {
	
	private static Logger loginServletLogger = Logger.getLogger(LoginServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = resp.getWriter();
		EmployeeService user = new EmployeeService();
		String email = req.getParameter("userEmail");
		String password = req.getParameter("userPassword");
		
		loginServletLogger.info("Inputs from form: " + email + password);
		
		
		if(user.getLoginCredentials(email, password) != null) {
//			if(user.getManager()) {
//				RequestDispatcher rs = req.getRequestDispatcher("user-portals/manager_page.html");
//				rs.forward(req, resp);
////				resp.sendRedirect("user-portals/manager_page.html");
//			} 
			HttpSession session = req.getSession();
			loginServletLogger.info("USER: " + user);
			loginServletLogger.info(session);
			session.setAttribute("employeeUser", user);
			loginServletLogger.info(session.getAttribute("employeeUser"));
			resp.sendRedirect("user-portals/employee_page.html");
//			RequestDispatcher rs = req.getRequestDispatcher("user-portals/employee_page.html");
//			rs.forward(req, resp);

		} else {
			pw.println("Username or Password Incorrect");
			resp.sendRedirect("index.html");
			
//			RequestDispatcher rs = req.getRequestDispatcher("index.html");
//			rs.include(req, resp);
		}
			

	}

}
