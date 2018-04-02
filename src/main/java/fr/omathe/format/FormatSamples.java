package fr.omathe.format;

import java.util.Arrays;
import java.util.stream.Collectors;

public interface FormatSamples {

	static <T> String commaSeparated(T[] array) {

		String s = "";
		if (array != null) {
			s = Arrays.asList(array).stream().map(t -> t.toString()).collect(Collectors.joining(","));
		}
		return s;
	}
}
