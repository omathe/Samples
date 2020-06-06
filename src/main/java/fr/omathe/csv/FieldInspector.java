package fr.omathe.csv;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface FieldInspector {

	/**
	 * Retrieves all the interfaces of a class or an interface
	 * @param theClass - The class or interface
	 * @return The stream of interfaces
	 */
	static Stream<Class<?>> getInterfaces(final Class<?> theClass) {

		return Stream.concat(Stream.of(theClass.getInterfaces()), Arrays.stream(theClass.getInterfaces()).flatMap(c -> FieldInspector.getInterfaces(c)));
	}

	/**
	 * Find a field from a class and its super classes and its interfaces
	 * @param fieldName - The field name
	 * @param theClass - The class where to search for the field
	 * @return A FieldValue
	 */
	@SuppressWarnings("unchecked")
	static <T> FieldValue<T> findFieldValue(final String fieldName, final Class<?> theClass) {

		FieldValue<T> fieldValue = new FieldValue<T>();
		Field field = null;

		Class<?> target = theClass;
		try {
			field = target.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			// searching in super classes
			for (target = theClass; target.getSuperclass() != null && target != Object.class; target = target.getSuperclass()) {
				try {
					field = target.getDeclaredField(fieldName);
					if (field != null) {
						break;
					}
				} catch (Exception e1) {
				}
			}
			if (field == null) {
				// searching in super interfaces
				for (Class<?> anInterface : getInterfaces(theClass).collect(Collectors.toList())) {
					try {
						field = anInterface.getDeclaredField(fieldName);
						if (field != null) {
							break;
						}
					} catch (Exception e1) {
					}
				}
			}
		}
		if (field != null) {
			fieldValue.setField(field);
			try {
				field.setAccessible(true);
				Object value = field.get(theClass);
				fieldValue.setValue((T) field.getType().cast(value));
			} catch (Exception e) {
			}
			fieldValue.setType(field.getGenericType());
		}
		return fieldValue;
	}

	/**
	 * Find a field from an object and its super classes and its interfaces
	 * @param fieldName - The field name
	 * @param object - The object where to search for the field
	 * @return A FieldValue
	 */
	@SuppressWarnings("unchecked")
	static <T> FieldValue<T> findFieldValue(final String fieldName, final Object object) {

		FieldValue<T> fieldValue = new FieldValue<T>();
		Field field = null;

		Class<?> target = object.getClass();
		try {
			field = target.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			// searching in super classes
			for (target = object.getClass(); target.getSuperclass() != null && target != Object.class; target = target.getSuperclass()) {
				try {
					field = target.getDeclaredField(fieldName);
					if (field != null) {
						break;
					}
				} catch (Exception e1) {
				}
			}
			if (field == null) {
				// searching in super interfaces
				for (Class<?> inter : object.getClass().getInterfaces()) {
					try {
						field = inter.getDeclaredField(fieldName);
						if (field != null) {
							break;
						}
					} catch (Exception e2) {
					}
				}
			}
		}
		if (field != null) {
			fieldValue.setField(field);
			try {
				field.setAccessible(true);
				Object value = field.get(object);
				fieldValue.setValue((T) field.getType().cast(value));
			} catch (Exception e) {
			}
		}
		return fieldValue;
	}
}
