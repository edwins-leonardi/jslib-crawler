package com.scalable.main;

import java.util.List;
import java.util.Scanner;

import com.scalable.crawler.SearchPopularJSLibraries;

public class JSLibCrawlerMain {

	private static final String HELLO_MESSAGE = "Hi there :), please enter a search term for our machine: ";
	private static final String WAIT_MESSAGE = "Ok, you entered \"%s\". Please hang on with us while the machine do its job!";

	public static void main(String[] args) throws Exception {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println(HELLO_MESSAGE);
			String searchTerm = scanner.nextLine();
			System.out.println(String.format(WAIT_MESSAGE, searchTerm));
			SearchPopularJSLibraries search = new SearchPopularJSLibraries(searchTerm);
			List<String> libraryNames = search.getPopularLibraries();
			for (int i = 0; i < libraryNames.size(); i++)
				System.out.println(i + 1 + " - " + libraryNames.get(i));
		}
	}
}
