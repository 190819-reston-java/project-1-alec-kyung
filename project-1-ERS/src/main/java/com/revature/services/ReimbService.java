package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursements;
import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeeDaoImpl;
import com.revature.repositories.ReimbDao;
import com.revature.repositories.ReimbDaoImpl;

public class ReimbService {
	
	private ReimbDao reimDao = new ReimbDaoImpl();
	
	public List<Reimbursements> getAllReimList() {
		return reimDao.getAllReimbs();
	}
	
	public List<Reimbursements> getReimByIdList(int empId) {
		return reimDao.getReimbsById(empId);
	}
	
	public List<Reimbursements> getReimByStatusList(String status) {
		return reimDao.getReimbsByStatus(status);
	}
	
	public List<Reimbursements> getPendingReimList() {
		return reimDao.getAllPendingReimMan();
	}

	
	
	
	

}