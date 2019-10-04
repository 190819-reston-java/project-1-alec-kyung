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
	boolean resolve(Reimbursements reimb);
	
	ArrayList<Reimbursements> getResolvedReimEmp(int empId);
	
	
	//not yet used, but i dont think we need these?????
	boolean updateReimb (int id, int resolver, int status);
	boolean deleteReimb(int id);
	
	
	
}
