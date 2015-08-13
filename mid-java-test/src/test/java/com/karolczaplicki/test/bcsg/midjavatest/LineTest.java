package com.karolczaplicki.test.bcsg.midjavatest;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class LineTest {

	@Test
	public void testLine() {
		String bank = "Test Bank";
		String cardNumber = "Test card number";
		LocalDate expiryDate = LocalDate.of(2015, 8, 13);
		Line line = new Line(bank, cardNumber, expiryDate);

		assertEquals(bank, line.getBank());
		assertEquals(cardNumber, line.getCardNumber());
		assertEquals(expiryDate, line.getExpiryDate());

	}
	
	@Test
	public void testToString(){

		String bank = "Test Bank";
		String cardNumber = "Test card number";
		LocalDate expiryDate = LocalDate.of(2015, 8, 13);
		Line line = new Line(bank, cardNumber, expiryDate);
		
		assertEquals(cardNumber, line.toString());
	}

}
