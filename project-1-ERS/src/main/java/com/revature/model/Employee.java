package com.revature.model;

public class Employee {
	
	private int employeeId;
	private String email;
	private String userPwd;
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private boolean manager;
	
	public Employee(int employeeId, String email, String userPwd, String firstName, String lastName, long phoneNumber,
			boolean manager) {
		super();
		this.employeeId = employeeId;
		this.email = email;
		this.userPwd = userPwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.manager = manager;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isManager() {
		return manager;
	}
	public void setManager(boolean manager) {
		this.manager = manager;
	}
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", email=" + email + ", userPwd=" + userPwd + ", firstName="
				+ firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", manager=" + manager + "]";
	}
	
	

	
	

	
	
}
