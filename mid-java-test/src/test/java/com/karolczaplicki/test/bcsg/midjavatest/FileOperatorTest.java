package com.karolczaplicki.test.bcsg.midjavatest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileOperatorTest {

	private static final String OUTPUT_FILE_NAME = "mid-test-output.csv";

	@AfterClass
	public static void setUp() throws Exception {
		File outputFile = new File(OUTPUT_FILE_NAME);
		if (outputFile.exists()) {
			assertTrue(outputFile.delete());
		}
	}

	@BeforeClass
	public static void tearDown() throws Exception {
		File outputFile = new File(OUTPUT_FILE_NAME);
		if (outputFile.exists()) {
			assertTrue(outputFile.delete());
		}
	}

	@Test
	public void testParseFile() throws IOException {

		FileOperator fileOperator = new FileOperator();
		URL file = this.getClass().getResource("/mid-test.csv");
		List<Line> readedLines = fileOperator.parseFile(file.getPath());

		assertEquals(3, readedLines.size());

		Line line = readedLines.get(0);
		LocalDate compareDate = LocalDate.of(2017, 11, 01);

		assertEquals("HSBC Canada", line.getBank());
		assertEquals("5601-2345-3446-5678", line.getCardNumber());
		assertEquals(compareDate.getYear(), line.getExpiryDate().getYear());
		assertEquals(compareDate.getMonth(), line.getExpiryDate().getMonth());

		line = readedLines.get(1);
		compareDate = LocalDate.of(2017, 10, 01);

		assertEquals("Royal Bank of  Canada", line.getBank());
		assertEquals("4519-4532-4524-2456", line.getCardNumber());
		assertEquals(compareDate.getYear(), line.getExpiryDate().getYear());
		assertEquals(compareDate.getMonth(), line.getExpiryDate().getMonth());

		line = readedLines.get(2);
		compareDate = LocalDate.of(2018, 12, 01);

		assertEquals("American Express", line.getBank());
		assertEquals("3786-7334-8965-345", line.getCardNumber());
		assertEquals(compareDate.getYear(), line.getExpiryDate().getYear());
		assertEquals(compareDate.getMonth(), line.getExpiryDate().getMonth());
	}

	@Test
	public void testSaveToFile() throws IOException {
		FileOperator fot = new FileOperator();
		List<Line> lines = new ArrayList<Line>();
		Line testLine = new Line("American Express", "3786-7334-8965-345",
				LocalDate.of(2017, 11, 01));
		testLine.setMaskedCardNumber("xxxx-xxxx-xxxx-345");
		lines.add(testLine);

		fot.saveToFile(lines, OUTPUT_FILE_NAME);

		File outputFile = new File(OUTPUT_FILE_NAME);

		assertTrue(outputFile.exists());

		BufferedReader reader = new BufferedReader(new FileReader(
				OUTPUT_FILE_NAME));
		String line = reader.readLine();
		assertEquals("American Express,3786-7334-8965-345,xxxx-xxxx-xxxx-345",
				line);
		reader.close();

	}

}
