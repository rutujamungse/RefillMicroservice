package com.mailorderpharmacy.refillservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "refillOrder_line")
@Table(name = "refillOrder_line")
public class RefillOrderLine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private long subID;
	@Column
	private String memberID;
	@Column
	private int refillTime;
	@Column
	private int quantity;

	public RefillOrderLine() {
	}
	public RefillOrderLine(long subID, String memberID, int refillTime, int quantity) {
		this.subID = subID;
		this.memberID = memberID;
		this.refillTime = refillTime;
		this.quantity = quantity;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	
	public int getRefillTime() {
		return refillTime;
	}
	public void setRefillTime(int refillTime) {
		this.refillTime = refillTime;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
