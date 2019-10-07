package com.revature.repositories;

import java.util.ArrayList;

import com.revature.model.Reimbursements;

public interface ReimbDao {
	
	ArrayList<Reimbursements> getAllReimbs();
	ArrayList<Reimbursements> getReimbsById(int empId);
	ArrayList<Reimbursements> getReimbsByStatus(String status);
	ArrayList<Reimbursements> getAllPendingReimMan();
//	ArrayList<Reimbursements> getResolvedReimMan();   MOVED TO EMP DAO
	ArrayList<Reimbursements> getReimReqSingleEmp(int empId);
	boolean addReimb(Reimbursements reimb);
	boolean resolve(String reimbStatus, int resolvedBy, int reimbId);
	
	
	ArrayList<Reimbursements> getResolvedReimEmp(int empId);
	
	
	//not yet used, but i dont think we need these?????
	boolean updateReimb (int id, int resolver, int status);
	boolean deleteReimb(int id);
	public boolean submitReimb(double amt, int empId);
	
	
	//My code after attempts everything comes back null
	ArrayList<Reimbursements> getAllResolvedReimbursementsAsManager();
	ArrayList<Reimbursements> getAllPendingReimbursementsAsManager();
	
	ArrayList<Reimbursements> getResolvedReimbursementsAsEmployee(int empId);
	ArrayList<Reimbursements> getPendingReimbursementsAsEmployee(int empId);
	
	//Approve/Deny
	public boolean requestApproved(int id);
	public boolean requestDenied(int id);
	
	
}
