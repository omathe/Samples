package fr.omathe.metadata.classes;

import java.util.Arrays;
import java.util.stream.Collectors;

import fr.omathe.format.FormatSamples;

public interface ClassesSamples {

	static void list(Object object) {

		System.out.println("getCanonicalName : " + object.getClass().getCanonicalName());
		System.out.println("getName : " + object.getClass().getName());
		System.out.println("getSimpleName : " + object.getClass().getSimpleName());
		System.out.println("getPackage : " + object.getClass().getPackage());
		System.out.println("getClasses : " + Arrays.asList(object.getClass().getClasses()).stream().map(c -> c.getSimpleName()).collect(Collectors.joining(", ")));
		System.out.println("getComponentType : " + object.getClass().getComponentType());
		System.out.println("getDeclaredClasses : " + FormatSamples.commaSeparated(object.getClass().getDeclaredClasses()));
		System.out.println("getDeclaredFields : " + Arrays.asList(object.getClass().getDeclaredFields()).stream().map(f -> f.getName()).collect(Collectors.joining(", ")));
		System.out.println("getDeclaringClass : " + object.getClass().getDeclaringClass());
		System.out.println("getEnclosingClass : " + object.getClass().getEnclosingClass());
		System.out.println("getFields : " + Arrays.asList(object.getClass().getFields()).stream().map(f -> f.getName()).collect(Collectors.joining(", ")));
		System.out.println("getGenericInterfaces : " + Arrays.asList(object.getClass().getGenericInterfaces()).stream().map(t -> t.getTypeName()).collect(Collectors.joining(", ")));
		System.out.println("getGenericSuperclass : " + object.getClass().getGenericSuperclass());
		System.out.println("getInterfaces : " + Arrays.asList(object.getClass().getInterfaces()).stream().map(c -> c.getSimpleName()).collect(Collectors.joining(", ")));
		System.out.println("getSuperclass : " + object.getClass().getSuperclass());
		System.out.println("getTypeName : " + object.getClass().getTypeName());

	}

	public static void main(String[] args) {

		ClassesSamples.list(new C3());

	}

}
