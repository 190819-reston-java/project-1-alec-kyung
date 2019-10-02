package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursements;
import com.revature.util.ERSConnectionUtil;
import com.revature.util.ERSStreamCloser;

public class ReimbDaoImpl implements ReimbDao {

	private static Reimbursements createReimbFromRS(ResultSet results) throws SQLException {
		return new Reimbursements(results.getInt("reimb_id"), results.getDouble("amount"), results.getString("status"),
				results.getInt("submitted_by_id"), results.getInt("resolved_by_id"), results.getLong("submit_time"));
	}
	
	//GET ALL REIMBURSEMENTS
	@Override
	public ArrayList<Reimbursements> getAllReimbs() {

		PreparedStatement statement = null;
		ResultSet results = null;

		String query = "SELECT * FROM ers.reimbursements;";

		ArrayList<Reimbursements> reimbursementsList = new ArrayList<Reimbursements>();

		try (Connection conn = ERSConnectionUtil.getConnection()) {
			statement = conn.prepareStatement(query);
			results = statement.executeQuery();

			while (results.next()) {
				Reimbursements reimbInfo = new Reimbursements();
				
				reimbInfo.setReimbId(results.getInt("reimb_id"));
				reimbInfo.setReimbAmt(results.getDouble("amount"));
				reimbInfo.setReimbStatus(results.getString("status"));
				reimbInfo.setSubmittedBy(results.getInt("submitted_by_id"));
				reimbInfo.setResolvedBy(results.getInt("resolved_by_id"));
				reimbInfo.setSubmitTime(results.getLong("submit_time"));

				reimbursementsList.add(reimbInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ERSStreamCloser.close(results);
			ERSStreamCloser.close(statement);
			System.out.println("Close stream");

		}

		return reimbursementsList;
	}

	//A Manager can view all pending requests from all employees
	@Override
	public ArrayList<Reimbursements> getPendingReim() {
		PreparedStatement statement = null;
		ResultSet results = null;

		String query = "SELECT * \r\n" + 
				"FROM ers.employees\r\n" + 
				"FULL JOIN ers.reimbursements\r\n" + 
				"ON ers.employees.emp_id = ers.reimbursements.submitted_by_id\r\n" + 
				"WHERE ers.reimbursements.resolved_by_id IS NULL AND ers.reimbursements.submitted_by_id IS NOT NULL;";

		ArrayList<Reimbursements> reimbursementsList = new ArrayList<Reimbursements>();

		try (Connection conn = ERSConnectionUtil.getConnection()) {
			statement = conn.prepareStatement(query);
			results = statement.executeQuery();

			while (results.next()) {
				Reimbursements reimbInfo = new Reimbursements();
				
				reimbInfo.setReimbId(results.getInt("reimb_id"));
				reimbInfo.setReimbAmt(results.getDouble("amount"));
				reimbInfo.setReimbStatus(results.getString("status"));
				reimbInfo.setSubmittedBy(results.getInt("submitted_by_id"));
				reimbInfo.setResolvedBy(results.getInt("resolved_by_id"));
				reimbInfo.setSubmitTime(results.getLong("submit_time"));

				
				reimbursementsList.add(reimbInfo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ERSStreamCloser.close(results);
			ERSStreamCloser.close(statement);
			System.out.println("Close stream");

		}

		return reimbursementsList;
	}
	
	//--A Manager can view all resolved requests from all employees and see which manager resolved it
	@Override
	public ArrayList<Reimbursements> getResolvedReimMan() {
		PreparedStatement statement = null;
		ResultSet results = null;

		String query = "SELECT emp_id, first_name, last_name\r\n" + 
				"FROM ers.employees\r\n" + 
				"FULL JOIN ers.reimbursements\r\n" + 
				"ON ers.employees.emp_id = ers.reimbursements.resolved_by_id\r\n" + 
				"WHERE ers.reimbursements.resolved_by_id IS NOT NULL;";

		ArrayList<Reimbursements> reimbursementsList = new ArrayList<Reimbursements>();

		try (Connection conn = ERSConnectionUtil.getConnection()) {
			statement = conn.prepareStatement(query);
			results = statement.executeQuery();

			while (results.next()) {
				Reimbursements reimbInfo = new Reimbursements();
				
				reimbInfo.setReimbId(results.getInt("reimb_id"));
				reimbInfo.setReimbAmt(results.getDouble("amount"));
				reimbInfo.setReimbStatus(results.getString("status"));
				reimbInfo.setSubmittedBy(results.getInt("submitted_by_id"));
				reimbInfo.setResolvedBy(results.getInt("resolved_by_id"));
				reimbInfo.setSubmitTime(results.getLong("submit_time"));

				
				reimbursementsList.add(reimbInfo);

				//employeesList.add(createEmployeeFromRS(results));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ERSStreamCloser.close(results);
			ERSStreamCloser.close(statement);
			System.out.println("Close stream");

		}

		return reimbursementsList;
	}
	
	@Override
	public ArrayList<Reimbursements> getReimbsById(int empId) {

		PreparedStatement statement = null;
		ResultSet results = null;

		String query = "SELECT * \r\n" + 
				"FROM ers.employees\r\n" + 
				"FULL JOIN ers.reimbursements\r\n" + 
				"ON ers.employees.emp_id = ers.reimbursements.submitted_by_id\r\n" + 
				"WHERE ers.reimbursements.resolved_by_id IS NULL AND ers.reimbursements.submitted_by_id IS NOT NULL AND ers.reimbursements.submitted_by_id = ?;";

		ArrayList<Reimbursements> reimbRequests = new ArrayList<Reimbursements>();

		try (Connection conn = ERSConnectionUtil.getConnection()) {
			statement = conn.prepareStatement(query);
			statement.setInt(1, empId);
			results = statement.executeQuery();

			while (results.next()) {
				Reimbursements reimbInfo = new Reimbursements();
				
				reimbInfo.setReimbId(results.getInt("reimb_id"));
				reimbInfo.setReimbAmt(results.getDouble("amount"));
				reimbInfo.setReimbStatus(results.getString("status"));
				reimbInfo.setSubmittedBy(results.getInt("submitted_by_id"));
				reimbInfo.setResolvedBy(results.getInt("resolved_by_id"));
				reimbInfo.setSubmitTime(results.getLong("submit_time"));

				
				reimbRequests.add(reimbInfo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ERSStreamCloser.close(results);
			ERSStreamCloser.close(statement);
			System.out.println("Close stream");

		}

		return reimbRequests;
	}

	@Override
	public ArrayList<Reimbursements> getReimbsByStatus(String status) {
		PreparedStatement statement = null;
		ResultSet results = null;

		String query = "SELECT * ers.reimbursements WHERE status = ?;";

		ArrayList<Reimbursements> reimbStatus = new ArrayList<Reimbursements>();

		try (Connection conn = ERSConnectionUtil.getConnection()) {
			statement = conn.prepareStatement(query);
			statement.setString(1, status);
			results = statement.executeQuery();

			while (results.next()) {
				Reimbursements reimbInfo = new Reimbursements();
				
				reimbInfo.setReimbId(results.getInt("reimb_id"));
				reimbInfo.setReimbAmt(results.getDouble("amount"));
				reimbInfo.setReimbStatus(results.getString("status"));
				reimbInfo.setSubmittedBy(results.getInt("submitted_by_id"));
				reimbInfo.setResolvedBy(results.getInt("resolved_by_id"));
				reimbInfo.setSubmitTime(results.getLong("submit_time"));

				
				reimbStatus.add(reimbInfo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ERSStreamCloser.close(results);
			ERSStreamCloser.close(statement);
			System.out.println("Close stream");

		}

		return reimbStatus;
	}

	@Override
	public boolean updateReimb(int id, int resolver, int status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteReimb(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addReimb(Reimbursements reimb) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
