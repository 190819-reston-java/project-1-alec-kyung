package com.revature.repositories;

import java.util.ArrayList;

import com.revature.model.Reimbursements;

public interface ReimbDao {
	
	ArrayList<Reimbursements> getAllReimbs();
	ArrayList<Reimbursements> getReimbsById();
	ArrayList<Reimbursements> getReimbsByStatus();
	boolean updateReimb (int id, int resolver, int status);
	boolean deleteReimb(int id);
	boolean addReimb(Reimbursements reimb);
	
}
