package com.mailorderpharmacy.refillservice.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mailorderpharmacy.refillservice.exception.DrugQuantityNotAvailable;
import com.mailorderpharmacy.refillservice.exception.InvalidTokenException;
import com.mailorderpharmacy.refillservice.exception.SubscriptionIDNotFoundException;
import com.mailorderpharmacy.refillservice.model.RefillOrder;
import com.mailorderpharmacy.refillservice.model.RefillOrderLine;
import com.mailorderpharmacy.refillservice.service.IRefillOrder;
import com.mailorderpharmacy.refillservice.service.IRefillOrderSubscription;

import feign.FeignException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
//@RequestMapping("/refill")
@CrossOrigin(origins = "http://localhost:3000/")
public class RefillController {

	@Autowired
	private IRefillOrder oservice;
	@Autowired
	private IRefillOrderSubscription subservice;

	private static final Logger LOGGER = LoggerFactory.getLogger(RefillController.class);
	
	/*
	 * http://127.0.0.1:8080/refillappdb/viewRefillStatus/101
	 */
	
	@ApiOperation(value = "View status of the subscriptions per id", response = ResponseEntity.class)
	@GetMapping(value = "/viewRefillStatus/{subID}")
	public ResponseEntity<List<RefillOrder>> viewRefillStatus(@RequestHeader("Authorization") String token,@PathVariable long subID)
			throws SubscriptionIDNotFoundException, InvalidTokenException {
		LOGGER.info("Inside viewRefillStatus");
		List<RefillOrder> list = oservice.getStatus(subID,token);
		return new ResponseEntity<List<RefillOrder>>(list, HttpStatus.OK);
	}

	/*
	 * http://127.0.0.1:8080/refillappdb/getRefillDuesAsOfDate/1/20220721
	 */
	@ApiOperation(value = "View refill dues as of date", response = ResponseEntity.class)
	@GetMapping(value = "/getRefillDuesAsOfDate/{memberID}/{date}")
	public ResponseEntity<List<RefillOrderLine>> getRefillDuesAsOfDate(@RequestHeader("Authorization") String token,@PathVariable("date") int date,
			@PathVariable("memberID") String memberID) throws InvalidTokenException {
		LOGGER.info("Inside Refill Controller getRefillDuesAsOfDate ");
		return new ResponseEntity<List<RefillOrderLine>>(oservice.getRefillDuesAsOfDate(memberID, date,token), HttpStatus.OK);
	}

	/*
	 * http://127.0.0.1:8080/refillappdb/requestAdhocRefill 
	  {
	   "policyID":1011,
	   "subID":101, 
	   "memberID" : "1", 
	   "location" : "Chennai",
	   "quantity": 15
	  }
	  
	 */
	@ApiOperation(value = "Request refill", response = ResponseEntity.class)
	@PostMapping(value = "/requestAdhocRefill")
	public ResponseEntity<RefillOrder> requestAdhocRefill(@RequestHeader("Authorization") String token,@RequestBody ObjectNode objectNode)
			throws ParseException, DrugQuantityNotAvailable, InvalidTokenException,FeignException {
		LOGGER.info("Inside Refill Controller requestAdhocRefill");
		long policyId = objectNode.get("policyID").asLong();
		long subID = objectNode.get("subID").asLong();

		//long policyId = Long.parseLong(objectNode.get("policyID").asText());
		//long subID = Long.parseLong(objectNode.get("subID").asText());
		//boolean payStatus = objectNode.get("payStatus").asBoolean();
		String memberID = objectNode.get("memberID").asText();
		String location = objectNode.get("location").asText();
		int quantity=objectNode.get("quantity").asInt();
		return new ResponseEntity<RefillOrder>(oservice.requestAdhocRefill(policyId, subID, memberID, location,quantity,token),
				HttpStatus.ACCEPTED);
	}
	
	
	// for subscription service
	
	/*
	 * http://127.0.0.1:8080/refillappdb/getRefillPaymentDues/101
	 */
	@ApiOperation(value = "View payment dues as of date", response = ResponseEntity.class)
	@GetMapping(value = "/getRefillPaymentDues/{subID}")
	public ResponseEntity<Boolean> getRefillPaymentDues(@RequestHeader("Authorization") String token,@PathVariable("subID") long subID) throws InvalidTokenException {
		LOGGER.info("Inside Refill Controller getRefillDuesAsOfDate");
		return new ResponseEntity<Boolean>(oservice.getRefillPaymentDues(subID,token), HttpStatus.OK);
	}
	
	/*
	 * http://127.0.0.1:8080/refillappdb/requestRefillSubscription
	 {
	 	"subID":101,
	 	"memberID":1,
	 	"quantity":15,
	 	"refillCycle":3
	 }
	  
	 */
	@ApiOperation(value = "Request normal refill", response = ResponseEntity.class)
	@PostMapping(path = "/requestRefillSubscription/{subId}/{memberId}/{quantity}/{time}")
	public ResponseEntity<RefillOrderLine> requestRefillSubscription(
			@RequestHeader("Authorization") String token, @PathVariable("subId") long subId,
			@PathVariable("memberId") String memberId, @PathVariable("quantity") int quantity,
			@PathVariable("time") int time) throws ParseException, InvalidTokenException {
		LOGGER.info("Inside Refill Controller requestRefillSubscription method");
		return ResponseEntity.accepted().body(
				subservice.updateRefillOrderSubscription(subId, memberId, quantity, time ,token));
	}
	
	/*
	 *  http://127.0.0.1:8080/refillappdb/viewRefillOrderSubscriptionStatus
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@ApiOperation(value = "View list of refills", response = ResponseEntity.class)
	@GetMapping(value = "/viewRefillOrderSubscriptionStatus")
	public ResponseEntity<List<RefillOrderLine>> viewRefillOrderSubscriptionStatus(@RequestHeader("Authorization") String token) throws InvalidTokenException{
		LOGGER.info("Inside Refill Controller viewRefillOrderSubscriptionStatus");
		return new ResponseEntity<List<RefillOrderLine>>(subservice.getAllSubscription(token),HttpStatus.OK);
	}
	
	
	
	/*
	 * http://127.0.0.1:8080/refillappdb/deleteBySubscriptionId/103
	 */
	
	@ApiOperation(value = "Delete subscription", response = ResponseEntity.class)
	@DeleteMapping(value = "/deleteBySubscriptionId/{subID}")
	public void deleteBySubscriptionId(@RequestHeader("Authorization") String token,@PathVariable("subID") long subID) throws InvalidTokenException {
		LOGGER.info("Inside Refill Controller deleteBySubscriptionId");
		subservice.deleteBySubscriptionId(subID,token);
	}
	
}
