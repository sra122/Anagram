package com.anagram.checker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class CheckerApplicationTests {

	@Test
	public void testIsAnagram_True() {
		String word1 = "listen";
		String word2 = "silent";
		boolean areAnagrams = CheckerApplication.isAnagram(word1, word2);

		assertTrue(areAnagrams, word1 + ", " + word2 + " are anagram type words");
	}

	@Test
	public void testIsAnagram_False() {
		String word1 = "apple";
		String word2 = "banana";
		boolean areAnagrams = CheckerApplication.isAnagram(word1, word2);

		assertFalse(areAnagrams, word1 + ", " + word2 + " are not anagram type words");
	}

	@Test
	public void testIsAnagram_EmptyStrings() {
		String word1 = "";
		String word2 = "";
		boolean areAnagrams = CheckerApplication.isAnagram(word1, word2);

		assertTrue(areAnagrams, "isAnagram should return true for empty strings");
	}

	@Test
	public void testFindAnagrams_EmptyList() {
		String newWord = "test";
		Set<String> pastInputs = new HashSet<>();
		Set<String> anagrams = CheckerApplication.findAnagrams(pastInputs, newWord);

		assertEquals(Collections.emptySet(), anagrams, "findAnagrams should return empty set for empty past inputs");
	}

	@Test
	public void testFindAnagrams_SingleMatch() {
		String newWord = "test";
		Set<String> pastInputs = new HashSet<>(Arrays.asList("silent", "test", "listen"));
		Set<String> expectedAnagrams = new HashSet<>(Collections.singletonList(newWord));
		Set<String> anagrams = CheckerApplication.findAnagrams(pastInputs, newWord);

		assertEquals(expectedAnagrams, anagrams, "findAnagrams should find a single matching anagram");
	}

	@Test
	public void testFindAnagrams_MultipleMatches() {
		String newWord = "eat";
		Set<String> pastInputs = new HashSet<>(Arrays.asList("silent", "ate", "listen", "table"));
		Set<String> expectedAnagrams = new HashSet<>(List.of("ate"));
		Set<String> anagrams = CheckerApplication.findAnagrams(pastInputs, newWord);

		assertEquals(expectedAnagrams, anagrams, "findAnagrams should find multiple matching anagrams");
	}

	@Test
	public void testFindAnagrams_NoMatches() {
		String newWord = "hello";
		Set<String> pastInputs = new HashSet<>(Arrays.asList("silent", "test", "listen", "table"));
		Set<String> expectedAnagrams = Collections.emptySet();
		Set<String> anagrams = CheckerApplication.findAnagrams(pastInputs, newWord);

		assertEquals(expectedAnagrams, anagrams, "findAnagrams should return empty set for no matches");
	}



}
