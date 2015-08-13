package com.karolczaplicki.test.bcsg.midjavatest;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

	private static final String INPUT_FILE_NAME = "/mid-test.csv";
	private static final String OUTPUT_FILE_NAME = "mid-test-output.csv";

	@Test
	public void test() {
		App app = new App();
		URL file = this.getClass().getResource(INPUT_FILE_NAME);
		app.doYourWork(file.getPath(), OUTPUT_FILE_NAME);
		File outputFile = new File(OUTPUT_FILE_NAME);
		assertTrue(outputFile.exists());
	}

	@After
	public void setUp() throws Exception {
		File outputFile = new File(OUTPUT_FILE_NAME);
		if (outputFile.exists()) {
			assertTrue(outputFile.delete());
		}
	}

	@Before
	public void tearDown() throws Exception {
		File outputFile = new File(OUTPUT_FILE_NAME);
		if (outputFile.exists()) {
			assertTrue(outputFile.delete());
		}
	}
}
