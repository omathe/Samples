package fr.omathe.reflection;

import java.lang.invoke.LambdaMetafactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class Performance2 {

	private static List<Long> direct = new ArrayList<>();
	private static List<Long> reflections = new ArrayList<>();

	public static long readName(Person person) {

		long t0 = System.nanoTime();

		person.getName();

		return System.nanoTime() - t0;
	}

	public static long readNameByReflection(Person person) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		long t0 = System.nanoTime();
		Field field = Person.class.getDeclaredField("name");
		field.setAccessible(true);
		field.get(person);

		return (System.nanoTime() - t0);
	}

	public static long readNameByReflectionUsingGetter(Person person) throws Exception {

		long t0 = System.nanoTime();
		Method getterMethod = Person.class.getMethod("getName");
		getterMethod.setAccessible(true);
		getterMethod.invoke(person);

		return (System.nanoTime() - t0);
	}

	public static long readNameByLambdaMetafactory(Person person) throws Exception {

		long t0 = System.nanoTime();
		LambdaMetafactory lm;

		Method getterMethod = Person.class.getMethod("getName");
		getterMethod.setAccessible(true);
		getterMethod.invoke(person);

		return (System.nanoTime() - t0);
	}

	public static void main(String[] args) throws Exception {

		Person person = new Person("Joe", "email");

		long t0 = System.nanoTime();
		for (int i = 0; i < 100; i++) {
			Long duration = Performance2.readName(person);
			System.out.println(duration);
			direct.add(duration);
		}
		System.out.println("total duration = " + (System.nanoTime() - t0) + " ns, (" + (System.nanoTime() - t0)/1000000 + " ms)");

		System.out.println("---");
		for (int i = 0; i < 100; i++) {
			long duration = Performance2.readNameByReflection(person);
//			long duration = Performance2.readNameByReflectionUsingGetter(person);
			System.out.println(duration);
			reflections.add(duration);
		}

		direct.remove(0);
		OptionalDouble averageDirectOptional = direct.stream().mapToLong(e -> e.longValue()).average();
		Double averageDirect = averageDirectOptional.isPresent() ? averageDirectOptional.getAsDouble() : null;
		System.out.println("direct average access time = " + averageDirect + " ns");

		OptionalDouble averageReflectionOptional = reflections.stream().mapToLong(e -> e.longValue()).average();
		Double averageReflection = averageReflectionOptional.isPresent() ? averageReflectionOptional.getAsDouble() : null;
		System.out.println("reflection average access time = " + averageReflection + " ns");

		System.out.println("ratio = " + averageReflection / averageDirect);

	}

}
