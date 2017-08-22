package fr.omathe.perf;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SamplePerf {

	private static Set<Integer> integerSet = new HashSet<>();
	private static List<Integer> integerList = new ArrayList<>();

	public static void generateSet() {

		final long t0 = Instant.now().toEpochMilli();
		final Random randomGenerator = new Random();

		while (integerSet.size() < 1_000_000) {
			final int randomInt = randomGenerator.nextInt(1_000_000);
			integerSet.add(randomInt);
		}
		System.out.println("duration to generate set : " + (Instant.now().toEpochMilli() - t0) + " ms");
	}

	public static void generateList() {

		final long t0 = Instant.now().toEpochMilli();
		final Random randomGenerator = new Random();

		while (integerList.size() < 1_000_000) {
			final Integer randomInt = randomGenerator.nextInt(1_000_000);
			if (!integerList.contains(randomInt)) {
				integerList.add(randomInt);
			}
		}
		System.out.println("duration to generate list: " + (Instant.now().toEpochMilli() - t0) + " ms");
	}
	
	public static void copySetToList() {
		
		integerList.clear();
		integerList.addAll(integerSet);
	}

	public static int findMaxFromSet() {

		final long t0 = Instant.now().toEpochMilli();
		int max = 0;
		final Iterator<Integer> iterator = integerSet.iterator();
		while (iterator.hasNext()) {
			final Integer next = iterator.next();
			if (next > max) {
				max = next;
			}
		}
		System.out.println("duration findMaxFromSet : " + (Instant.now().toEpochMilli() - t0) + " ms");
		return max;
	}
	
	public static int findMaxFromList() {
		
		final long t0 = Instant.now().toEpochMilli();
		int max = 0;
		final Iterator<Integer> iterator = integerList.iterator();
		while (iterator.hasNext()) {
			final Integer next = iterator.next();
			if (next > max) {
				max = next;
			}
		}
		System.out.println("duration findMaxFromList : " + (Instant.now().toEpochMilli() - t0) + " ms");
		return max;
	}

	public static void main(final String[] args) {

		generateSet();
		//generateList();
		copySetToList();
		
		final int maxFromSet = findMaxFromSet();
		System.out.println("max from set : " + maxFromSet);
		
		System.out.println("");
		
		final int maxFRomList = findMaxFromList();
		System.out.println("max from list : " + maxFRomList);
	}

}
