package fr.omathe.reflection;

public class Performance {

	public static long readName(Person person) {

		long t0 = System.nanoTime();
		person.getName();
		long duration = System.nanoTime() - t0;
		System.out.println(duration);

		return duration;
	}

	public static void main(String[] args) throws Exception {

		Person person = new Person("Joe", "email");

		long t0 = System.nanoTime();
		for (int i = 0; i < 100; i++) {
			Performance.readName(person);
		}
		System.out.println("total duration = " + (System.nanoTime() - t0) + " ns");

	}

}
