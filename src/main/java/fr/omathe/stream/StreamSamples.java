package fr.omathe.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public interface StreamSamples {

	static String pairs(final Stream<String> stream) {

		String pairs = "";
//		pairs = stream.reduce("", (a, b) -> a+b);
		List<String> list = stream.reduce( new ArrayList<String>(),
			    (List<String> l, String e) -> {
			        l.add(e);
			        return l;
			       },
			        (List<String> l1, List<String> l2) -> {
			       l1.addAll(l2); return l1; });

		return pairs;
	}
}
