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

import com.revature.services.EmployeeService;

public class Welcome extends HttpServlet {
	
	private static Logger welcomeLogger = Logger.getLogger(Welcome.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		Cookie cookies[] = req.getCookies();
		
		String verifiedUser = null;
		
		for (Cookie c : cookies) {
			if(c.getName().equals("employeeUser")) {
				verifiedUser = c.getValue();
			}
		}
		
		PrintWriter out = resp.getWriter();
		out.print("Welcome" + verifiedUser);

	}

}