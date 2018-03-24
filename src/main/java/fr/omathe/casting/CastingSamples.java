package fr.omathe.casting;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface CastingSamples {

	static void staticCasting() {

		Object obj = 10;
		if (obj instanceof Integer) {
			Integer integer = (Integer) obj;
			System.out.println("integer = " + integer);
		}
	}

	static <T> List<T> filter(Class<T> clazz, List<?> items) {

		return items.stream().filter(clazz::isInstance).map(clazz::cast).collect(Collectors.toList());
	}

	/**
	 * Cast object to target
	 */
	static <T> T dynamicCasting(Object object, Class<T> target) {

		T t = null;
		if (target.isInstance(object)) {
			t = target.cast(object);
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	static void main(String[] args) {

		Object object = new Integer(200);
		Integer i = CastingSamples.dynamicCasting(object, Integer.class);
		System.out.println("i = " + i);

		object = Stream.of("A", "B", "C").collect(Collectors.toList());
		List<String> strings2 = CastingSamples.dynamicCasting(object, List.class);
		strings2.forEach(System.out::println);
	}
}
