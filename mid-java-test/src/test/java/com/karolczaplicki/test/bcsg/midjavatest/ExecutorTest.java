package com.karolczaplicki.test.bcsg.midjavatest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class ExecutorTest {

	private static List<Line> lines;
	private static Line lastExpiryDate;
	private static Line firstExpiryDate;

	@BeforeClass
	public static void beforeClassSetUp() {
		lines = new ArrayList<Line>();

		Line newLine = new Line("HSBC Canada", "5601-2345-3446-5678",
				LocalDate.of(2017, 10, 1));
		firstExpiryDate = newLine;
		lines.add(newLine);

		lines.add(new Line("Royal Bank of  Canada", "4519-4532-4524-2456",
				LocalDate.of(2017, 11, 1)));

		newLine = new Line("American Express", "3786-7334-8965-345",
				LocalDate.of(2018, 12, 1));

		lastExpiryDate = newLine;
		lines.add(newLine);
	}

	@Test
	public void testMaskCardNumber() {
		Executor executor = new Executor();

		executor.maskLines(lines);

		for (Line line : lines) {
			switch (line.getCardNumber()) {
			case "5601-2345-3446-5678":
				assertEquals("56xx-xxxx-xxxx-xxxx", line.getMaskedCardNumber());
				break;

			case "4519-4532-4524-2456":
				assertEquals("4519-xxxx-xxxx-xxxx", line.getMaskedCardNumber());
				break;
			case "3786-7334-8965-345":
				assertEquals("xxxx-xxxx-xxxx-345", line.getMaskedCardNumber());
				break;
			default:
				break;
			}
		}

	}

	@Test
	public void testSortDesc() {
		Executor executor = new Executor();

		assertEquals(firstExpiryDate, lines.get(0));
		assertEquals(lastExpiryDate, lines.get(2));

		executor.sortDesc(lines);

		assertEquals(firstExpiryDate, lines.get(2));
		assertEquals(lastExpiryDate, lines.get(0));
	}

}
