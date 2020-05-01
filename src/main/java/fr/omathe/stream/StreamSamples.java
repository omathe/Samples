package fr.omathe.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface StreamSamples {

	static String pairs(final Stream<String> stream) {

		String pairs = "";
		// pairs = stream.reduce("", (a, b) -> a+b);
		List<String> list = stream.reduce(new ArrayList<String>(), (List<String> l, String e) -> {
			l.add(e);
			return l;
		}, (List<String> l1, List<String> l2) -> {
			l1.addAll(l2);
			return l1;
		});

		return pairs;
	}

	/**
	 * Find Items From One List Based On Values From Another List
	 */
	static List<String> copyListToList(List<String> list1, List<String> list2) {

		return list1.stream().filter(e1 -> list2.stream().anyMatch(e2 -> e2.length() > 3)).collect(Collectors.toList());

		/*
		 * List<Employee> filteredList = emplList.stream() .filter(empl ->
		 * deptList.stream() .anyMatch(dept -> dept.getDepartment().equals("sales") &&
		 * empl.getEmployeeId().equals(dept.getEmployeeId())))
		 * .collect(Collectors.toList());
		 */
	}

	/**
	 * Display index in a stream
	 * @return The list of index
	 */
	static List<Integer> displayIndex() {

		List<String> list = List.of("A", "B", "C");
		List<Integer> indexes = new ArrayList<>();

		IntStream.range(0, list.size()).forEach(index -> {
			System.out.println("index = " + index);
			indexes.add(index);	
		});
		
		return indexes;
	}
}
