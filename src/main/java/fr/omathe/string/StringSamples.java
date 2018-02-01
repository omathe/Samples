package fr.omathe.string;

import java.util.List;
import java.util.stream.Collectors;

public interface StringSamples {

	static String removeFirstCharacter(final String name) {

		return name.substring(1);
	}

	/**
	 * Return a string build from a list of strings, separated by a ';', starting by '[' and ending by ']'
	 * @param list - A list of strings
	 * Example : a,b,c becomes [a;b;c]
	 * @return The built string
	 */
	static String joinWithDelimiter(List<String> list) {

		return list.stream().collect(Collectors.joining(",", "[", "]"));
	}

	/**
	 * Build a array of string length of 2
	 * @param string - The original string
	 * Example : aabbccddee becomes [aa,bb,cc,dd]
	 * @return An array
	 */
	static String[] splitStringIntoStringsLengthOfTwo(String string) {

		return string.split("(?<=\\G..)");
	}

}
