package com.karolczaplicki.test.bcsg.midjavatest;

import java.util.Collections;
import java.util.List;

public class Executor {

	public void maskLines(List<Line> lines) {
		lines.forEach(line -> maskCardNumber(line));
	}

	private void maskCardNumber(Line line) {
		String cardNumber = line.getCardNumber();
		String maskedCardNumber = null;
		switch (line.getBank()) {
		case "HSBC Canada":
			maskedCardNumber = mask(cardNumber, "1100-0000-0000-0000");
			break;
		case "Royal Bank of  Canada":
			maskedCardNumber = mask(cardNumber, "1111-0000-0000-0000");
			break;
		case "American Express":
			maskedCardNumber = mask(cardNumber, "0000-0000-0000-1111");
			break;
		default:
			maskedCardNumber = mask(cardNumber, "0000-0000-0000-1111");
			break;
		}
		line.setMaskedCardNumber(maskedCardNumber);
	}

	private String mask(String cardNumber, String mask) {
		String maskedCardNumber = "";
		for (int i = 0; i < cardNumber.length(); i++) {
			char maskChar = mask.charAt(i);
			switch (maskChar) {
			case '0':
				maskedCardNumber += "x";
				break;
			case '1':
			default:
				maskedCardNumber += cardNumber.charAt(i);
				break;
			}
		}

		return maskedCardNumber;
	}

	public void sortDesc(List<Line> lines) {
		Collections.sort(lines, (a, b) -> {
			if (b.getExpiryDate().isBefore(a.getExpiryDate())) {
				return -1;
			}
			return 0;
		});
	}
}
