package fr.omathe.init;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class MapSamples {

	static final Map<Integer, String> INTEGERS = new HashMap<Integer, String>() {
		{
			put(0, "ZERO");
			put(1, "ONE");
			put(2, "TWO");
		}
	};

}
