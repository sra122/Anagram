package com.anagram.checker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class CheckerApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//List of past inputs
		Set<String> pastInputs = new HashSet<>();

		boolean exit = false;

		while(!exit) {
			System.out.println("\n**Anagram Checker & Finder**");
			System.out.println("1. Check for Anagrams");
			System.out.println("2. List of anagrams for a string");
			System.out.println("3. Exit");

			int choice = scanner.nextInt();

			switch (choice) {
				case 1 -> {
					System.out.println("Enter first word:");
					String word1 = scanner.next();
					System.out.println("Enter second word:");
					String word2 = scanner.next();

					//Check if provided words are Anagram or not
					if (isAnagram(word1, word2)) {
						pastInputs.add(word1);
						pastInputs.add(word2);
						System.out.println(word1 + ", " + word2 + " are anagram type words");
					} else {
						System.out.println(word1 + ", " + word2 + " are not anagram type words");
					}
				}
				case 2 -> {
					System.out.println("Enter word for searching related anagrams:");
					// Provide the word to get the list of related Anagram values
					String searchWord = scanner.next();

					//Search for Anagrams list
					Set<String> anagrams = findAnagrams(pastInputs, searchWord);
					if (anagrams.isEmpty()) {
						System.out.println("No anagrams found for " + searchWord + ".");
					} else {
						System.out.println("Anagrams for " + searchWord + ":");
						anagrams.stream()
								.filter(word -> !Objects.equals(word, searchWord)) // Filter out word3
								.forEach(System.out::println);
					}
				}
				case 3 -> {
					exit = true;
					System.out.println("Exiting program.");
				}
				default -> System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	/**
	 *  Function to identify provided words are anagram or not
	 *
	 * @param word1 String
	 * @param word2 String
	 * @return boolean
	 */
	public static boolean isAnagram(String word1, String word2) {
		// In anagram, length of words should be same
		if (word1.length() != word2.length()) {
			return false;
		}

		HashMap<Character, Integer> charCount1 = new HashMap<>();
		//Creating hashmap with keys as each character of the word and assigning value to it.
		for (char c : word1.toCharArray()) {
			charCount1.put(c, charCount1.getOrDefault(c, 0) + 1);
		}

		HashMap<Character, Integer> charCount2 = new HashMap<>();
		//Creating hashmap with keys as each character of the word and assigning value to it.
		for (char c : word2.toCharArray()) {
			charCount2.put(c, charCount2.getOrDefault(c, 0) + 1);
		}

		//Comparing Hashmaps
		return charCount1.equals(charCount2);
	}

	/**
	 * Find the anagrams list against the word that is provided
	 *
	 * @param pastInputs Set<String>
	 * @param searchWord String
	 * @return Set<String>
	 */
	public static Set<String> findAnagrams(Set<String> pastInputs, String searchWord) {
		Set<String> anagrams = new HashSet<>();
		for (String pastInput : pastInputs) {
			if (isAnagram(pastInput, searchWord)) {
				anagrams.add(pastInput);
			}
		}
		return anagrams;
	}

}
