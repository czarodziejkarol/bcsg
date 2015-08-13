package com.karolczaplicki.test.bcsg.midjavatest;

import java.io.IOException;
import java.util.List;

public class App {

	private FileOperator fileOperator;
	private Executor executor;

	public App() {
		fileOperator = new FileOperator();
		executor = new Executor();
	}

	public void doYourWork(String inputFileName, String outputFileName) {
		List<Line> lines;
		try {
			lines = fileOperator.parseFile(inputFileName);
		} catch (IOException e) {
			throw new RuntimeException("Reading file error. Message: " + e.getMessage());
		}
		executor.maskLines(lines);
		executor.sortDesc(lines);
		try {
			fileOperator.saveToFile(lines, outputFileName);
		} catch (IOException e) {
			throw new RuntimeException("Writing file error. Message: " + e.getMessage());
		}
	}

}
