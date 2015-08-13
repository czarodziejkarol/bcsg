package com.karolczaplicki.test.bcsg.midjavatest;

import java.time.LocalDate;

public class Line {

	private String bank;
	private String cardNumber;
	private LocalDate expiryDate;

	private String maskedCardNumber;

	public Line(String bank, String cardNumber, LocalDate expiryDate) {
		this.bank = bank;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
	}

	public String getBank() {
		return bank;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public String getMaskedCardNumber() {
		return maskedCardNumber;
	}

	public void setMaskedCardNumber(String maskedCardNumber) {
		this.maskedCardNumber = maskedCardNumber;
	}

	@Override
	public String toString() {
		return cardNumber;
	}

}
