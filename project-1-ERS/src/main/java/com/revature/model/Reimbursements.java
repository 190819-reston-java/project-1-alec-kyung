package com.revature.model;

public class Reimbursements {

	private int reimbId;
	private double reimbAmt;
	private String reimbStatus;
	private int submittedBy;
	private int resolvedBy;
	private String imageUrl;
	private long submitTime;
	
	public Reimbursements() {
		super();
	}

	public Reimbursements(int reimbId, double reimbAmt, String reimbStatus, int submittedBy, int resolvedBy,
			String imageUrl, long submitTime) {
		super();
		this.reimbId = reimbId;
		this.reimbAmt = reimbAmt;
		this.reimbStatus = reimbStatus;
		this.submittedBy = submittedBy;
		this.resolvedBy = resolvedBy;
		this.imageUrl = imageUrl;
		this.submitTime = submitTime;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getReimbAmt() {
		return reimbAmt;
	}

	public void setReimbAmt(double reimbAmt) {
		this.reimbAmt = reimbAmt;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public int getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(int submittedBy) {
		this.submittedBy = submittedBy;
	}

	public int getResolvedBy() {
		return resolvedBy;
	}

	public void setResolvedBy(int resolvedBy) {
		this.resolvedBy = resolvedBy;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public long getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(long submitTime) {
		this.submitTime = submitTime;
	}

	@Override
	public String toString() {
		return "Reimbursements [reimbId=" + reimbId + ", reimbAmt=" + reimbAmt + ", reimbStatus=" + reimbStatus
				+ ", submittedBy=" + submittedBy + ", resolvedBy=" + resolvedBy + ", imageUrl=" + imageUrl
				+ ", submitTime=" + submitTime + "]";
	}
	
}
