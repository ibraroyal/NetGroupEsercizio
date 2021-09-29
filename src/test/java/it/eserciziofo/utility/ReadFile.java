package it.eserciziofo.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadFile{
	final static String PATH_LINK = ".\\src\\main\\resources\\input\\link.txt";

	public static List<String> letturaFile() throws Exception {
		List<String> links = null;
		try {
			links = Files.readAllLines(Path.of(PATH_LINK));
		} catch (IOException e) {
			throw e;
		}
		return links;
	}

}