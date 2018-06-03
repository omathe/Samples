package fr.omathe.metadata.type;

import java.lang.reflect.Type;
import java.util.List;

public interface TypeSamples {

	enum Week {
		MONDAY,TUESDAY
	}

	static void test() {

		Integer integer = 10;

		Type classType = String.class;
		Type interfaceType = List.class;
		Type enumType = Week.class;
		Type object = integer.getClass();

		System.out.println(classType);
		System.out.println(interfaceType);
		System.out.println(enumType);
		System.out.println(object);

	}

	public static void main(String[] args) {

		TypeSamples.test();
	}

}
