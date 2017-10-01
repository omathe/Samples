package fr.omathe.collection;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ListSamples {

	static void reverseList() {

		final List<String> list = Stream.of("C", "B", "A").collect(Collectors.toList());
		System.out.println(list);

		Collections.reverse(list);

		System.out.println(list);
	}
	
}
