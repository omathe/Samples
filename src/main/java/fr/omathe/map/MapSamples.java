package fr.omathe.map;

import java.util.HashMap;
import java.util.Map;

public class MapSamples {

	public static void browse() {

		final Map<Long, String> map = new HashMap<>();
		map.put(1L, "ONE");
		map.put(2L, "TWO");
		map.put(3L, "THREE");
		

		for (final Map.Entry<Long, String> entry : map.entrySet()) {
			
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}

}
