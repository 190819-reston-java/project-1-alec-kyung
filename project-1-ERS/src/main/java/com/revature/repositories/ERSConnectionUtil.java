package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ERSConnectionUtil {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		try {
			Properties props = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			props.load(loader.getResourceAsStream("ERSdb.connection.properties"));
			
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			

			conn = DriverManager.getConnection(url, username, password);
			System.out.println("==========CONNECTED=============");

			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

}
