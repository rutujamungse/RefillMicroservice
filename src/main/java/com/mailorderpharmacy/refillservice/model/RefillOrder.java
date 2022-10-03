package com.mailorderpharmacy.refillservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "refill_order")
public class RefillOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long refillID;
	@Column
	private long subID;
	@Column
	private String memberID;
	@Column
	private boolean payStatus;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss")
	private Date refillDate;
	@Column
	private int quantity;
	
	public RefillOrder() {
	}
	
	public RefillOrder(long refillID, long subID, String memberID, boolean payStatus, Date refillDate, int quantity) {
		this.refillID = refillID;
		this.subID = subID;
		this.memberID = memberID;
		this.payStatus = payStatus;
		this.refillDate = refillDate;
		this.quantity = quantity;
	}


	public long getRefillID() {
		return refillID;
	}
	public void setRefillID(long refillID) {
		this.refillID = refillID;
	}

	public long getSubID() {
		return subID;
	}
	public void setSubID(long subID) {
		this.subID = subID;
	}

	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	
	public Date getRefillDate() {
		return refillDate;
	}
	public void setRefillDate(Date refillDate) {
		this.refillDate = refillDate;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPayStatus(boolean payStatus) {
		this.payStatus = payStatus;
	}
	public boolean getPayStatus() {
		return payStatus;
	}
	
}
