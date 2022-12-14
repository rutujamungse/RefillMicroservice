package com.mailorderpharmacy.refillservice.exception.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.naming.ServiceUnavailableException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.mailorderpharmacy.refillservice.exception.DrugQuantityNotAvailable;
import com.mailorderpharmacy.refillservice.exception.GlobalExceptionHandler;
import com.mailorderpharmacy.refillservice.exception.InvalidTokenException;
import com.mailorderpharmacy.refillservice.exception.SubscriptionIDNotFoundException;



@SpringBootTest
@AutoConfigureMockMvc
class GlobalExceptionHandlerTest {
		
	@InjectMocks
	GlobalExceptionHandler globalExceptionHandler;
	
	@Test
	void invalidTokenException()
	{
		assertEquals(HttpStatus.NOT_FOUND, globalExceptionHandler.invalidTokenException
				(new InvalidTokenException("invalidTokenException")).getStatusCode());
	}
	
	@Test
	void subscriptionIdNotFoundException()
	{
		assertEquals(HttpStatus.NOT_FOUND, globalExceptionHandler.subscriptionIdNotFoundException
				(new SubscriptionIDNotFoundException("subscriptionIdNotFoundException")).getStatusCode());
	}
	
	@Test
	void serviceUnavailableException()
	{
		assertEquals(HttpStatus.SERVICE_UNAVAILABLE, globalExceptionHandler.serviceUnavailableException
				(new ServiceUnavailableException("serviceUnavailableException")).getStatusCode());
	}
	
	@Test
	void drugQuantityNotAvailable()
	{
		assertEquals(HttpStatus.NOT_FOUND, globalExceptionHandler.drugQuantityNotAvailable
				(new DrugQuantityNotAvailable("DrugQuantityNotAvailable")).getStatusCode());
	}
	
}
