package com.karolczaplicki.test.bcsg.midjavatest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FileOperator {

	public List<Line> parseFile(String filePath) throws IOException {
		List<Line> lines = new ArrayList<Line>();
		FileReader fileReader = new FileReader(filePath);
		BufferedReader bufferedFileReader = new BufferedReader(fileReader);
		String fileLine = null;

		while ((fileLine = bufferedFileReader.readLine()) != null) {
			if (fileLine.equals("")) {
				continue;
			}
			Line line = parseLine(fileLine);
			lines.add(line);
		}

		bufferedFileReader.close();

		return lines;
	}

	private Line parseLine(String fileLine) {

		String[] splittedFileLine = fileLine.split(",");
		String bank = splittedFileLine[0];
		String cardNumber = splittedFileLine[1];

		LocalDate expiryDate = LocalDate.parse(
				"01-" + splittedFileLine[2],
				DateTimeFormatter.ofPattern("dd-MMM-uuuu").withLocale(
						Locale.ENGLISH));
		Line line = new Line(bank, cardNumber, expiryDate);
		return line;
	}

	public void saveToFile(List<Line> lines, String outputFileName)
			throws IOException {
		File newFile = new File(outputFileName);
		BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));

		for (Line line : lines) {
			writer.write(line.getBank() + "," + line.getCardNumber() + ","
					+ line.getMaskedCardNumber());
		}
		
		writer.flush();
		writer.close();
	}
}
