package com.revature.model;

public class Employee {
	
	private int empId;
	private String email;

	private String empPwd;
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private boolean manager;
	
	public Employee(int empId, String email, String empPwd, String firstName, String lastName, long phoneNumber,
			boolean manager) {
		super();
		this.empId = empId;
		this.email = email;
		this.empPwd = empPwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.manager = manager;

	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
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

	public long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(long phoneNumber) {
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
		return "Employee [empId=" + empId + ", email=" + email + ", empPwd=" + empPwd + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", manager=" + manager + "]";
	}
	


	
	

	
	
}
