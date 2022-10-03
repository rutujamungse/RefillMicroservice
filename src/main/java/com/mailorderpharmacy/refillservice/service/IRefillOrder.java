/**
 * 
 */
package com.mailorderpharmacy.refillservice.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mailorderpharmacy.refillservice.exception.DrugQuantityNotAvailable;
import com.mailorderpharmacy.refillservice.exception.InvalidTokenException;
import com.mailorderpharmacy.refillservice.exception.SubscriptionIDNotFoundException;
import com.mailorderpharmacy.refillservice.model.RefillOrder;
import com.mailorderpharmacy.refillservice.model.RefillOrderLine;

import feign.FeignException;


@Service
public interface IRefillOrder {
	
	/**
	 * @param subID
	 * @return
	 * @throws SubscriptionIDNotFoundException 
	 * @throws InvalidTokenException 
	 */
	public List<RefillOrder> getStatus(long subID,String token) throws SubscriptionIDNotFoundException, InvalidTokenException;
	
	/**
	 * @param memberId
	 * @param date
	 * @return
	 * @throws InvalidTokenException 
	 */
	public List<RefillOrderLine> getRefillDuesAsOfDate(String memberId, int date,String token) throws InvalidTokenException;
	
	/**
	 * @param subId
	 * @param payStatus
	 * @param quantity
	 * @param location
	 * @return
	 * @throws ParseException 
	 * @throws DrugQuantityNotAvailable 
	 * @throws InvalidTokenException 
	 */
	public RefillOrder requestAdhocRefill(long policyId, long subID, String memberID, String location,int quantity,String token) throws ParseException, DrugQuantityNotAvailable, InvalidTokenException,FeignException;
	
	
	/**
	 * @param order
	 * @return
	 * @throws ParseException 
	 * @throws InvalidTokenException 
	 */
	//public RefillOrder requestRefill(RefillOrder order,String token) throws ParseException, InvalidTokenException;
	
	/**
	 * @param subId
	 * @param quantity
	 * @param memberId
	 * @param token
	 * @return RefillOrder
	 * @throws ParseException
	 * @throws InvalidTokenException
	 */
	public RefillOrder requestRefill(long subId, int quantity, String memberId, String token)
			throws ParseException, InvalidTokenException;
	
	
	
	/**
	 * @return
	 * @throws InvalidTokenException 
	 */
	public String updateRefill(String token) throws InvalidTokenException;
	

	
	/**
	 * @param subID
	 * @return
	 * @throws InvalidTokenException 
	 */
	public boolean getRefillPaymentDues(long subID,String token) throws InvalidTokenException;
	
}
