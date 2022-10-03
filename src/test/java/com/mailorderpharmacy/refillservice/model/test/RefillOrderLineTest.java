package com.mailorderpharmacy.refillservice.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.mailorderpharmacy.refillservice.model.RefillOrderLine;


public class RefillOrderLineTest {
	RefillOrderLine test1 = new RefillOrderLine();
	RefillOrderLine test2 = new RefillOrderLine(1, "CR7", 3, 25);

	@Test
	public void testRefillID() {
		test1.setId(1);
		assertEquals(1, test1.getId());
		test2.setId(2);
		assertEquals(2, test2.getId());
	}

	@Test
	public void testsubscriptionId() {
		test1.setSubID(1);
		assertEquals(1, test1.getSubID());
		assertEquals(1, test2.getSubID());
	}

	@Test
	public void testRefillQuantity() {
		test1.setQuantity(25);
		assertEquals(25, test1.getQuantity());
		assertEquals(25, test2.getQuantity());
	}

	@Test
	public void testMemberId() {
		test1.setMemberID("CR7");
		assertEquals("CR7", test1.getMemberID());
		assertEquals("CR7", test2.getMemberID());
	}

	@Test
	public void testRefillTime() {
		test1.setRefillTime(3);
		assertEquals(3, test1.getRefillTime());
		assertEquals(3, test2.getRefillTime());
	}
}
