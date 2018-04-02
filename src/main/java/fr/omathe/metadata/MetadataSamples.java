package fr.omathe.metadata;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface MetadataSamples {

	/**
	 * Retrieves all the interfaces of a class or an interface
	 * @param theClass - The class or interface
	 * @return The stream of interfaces
	 */
	static Stream<Class<?>> getInterfaces(Class<?> theClass) {

		return Stream.concat(Stream.of(theClass.getInterfaces()), Arrays.stream(theClass.getInterfaces()).flatMap(c -> MetadataSamples.getInterfaces(c)));
	}

	/**
	 * Find a field from a class and its super classes and its interfaces
	 * @param fieldName - The field name
	 * @param theClass - The class where to search for the field
	 * @return A FieldValue
	 */
	@SuppressWarnings("unchecked")
	static <T> FieldValue<T> findFieldValue(final String fieldName, final Class<?> theClass) {

		FieldValue<T> fieldValue = new FieldValue<T>(false);
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
			fieldValue.setPresent(true);
			try {
				field.setAccessible(true);
				Object value = field.get(theClass);
				fieldValue.setValue((T) field.getType().cast(value));
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
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

		FieldValue<T> fieldValue = new FieldValue<T>(false);
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
			fieldValue.setPresent(true);
			try {
				field.setAccessible(true);
				Object value = field.get(object);
				fieldValue.setValue((T) field.getType().cast(value));
			} catch (Exception e) {
			}
		}
		return fieldValue;
	}

	/**
	 * Retrieves the value of a field of an object
	 * The optional is empty if the field has not been found
	 * @param fieldName - The field name
	 * @param object - The object we want to retrieve the value of the field
	 * @return The value or an empty response
	 */
	@SuppressWarnings("unchecked")
	static <T> Optional<T> getFieldValue(String fieldName, Object object) {

		Optional<T> optionalValue = Optional.empty();
		for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				Field field = clazz.getDeclaredField(fieldName);
				if (field != null) {
					field.setAccessible(true);
					Object value = field.get(object);
					optionalValue = (Optional<T>) Optional.of(field.getType().cast(value));
					break;
				}
			} catch (Exception e) {
			}
		}
		return optionalValue;
	}

	/**
	 * Finds the field fieldName in the Object target
	 */
	static Optional<Field> findField(final String fieldName, final Object target) {

		Optional<Field> optional = Optional.empty();
		for (Class<?> clazz = target.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				Field field = clazz.getDeclaredField(fieldName);
				if (field != null) {
					optional = Optional.of(field);
					break;
				}
			} catch (NoSuchFieldException | SecurityException e) {
			}
		}
		return optional;
	}

	/**
	 * Finds the fields in the target and in its interfaces
	 */
	static Optional<Field> findFieldInInterfaces(final String fieldName, final Class<?> target) {

		Optional<Field> optional = Optional.empty();

		if (target != null) {
			try {
				Field field = target.getDeclaredField(fieldName);
				if (field != null) {
					optional = Optional.of(field);
				}
			} catch (NoSuchFieldException | SecurityException e) {
			}
			if (!optional.isPresent()) {
				// if not found, search in the extended interface
				for (int i = 0; i < target.getInterfaces().length; i++) {
					try {
						Field field = target.getInterfaces()[i].getDeclaredField(fieldName);
						if (field != null) {
							optional = Optional.of(field);
							break;
						}
					} catch (NoSuchFieldException | SecurityException e) {
					}
				}
			}
		}
		return optional;
	}

	/**
	 * Finds the static field fieldName in the Class target
	 */
	static Optional<Field> findField(final String fieldName, final Class<?> target) {

		Optional<Field> optional = Optional.empty();
		for (Class<?> clazz = target; clazz != null && clazz != Object.class; clazz = clazz.getSuperclass()) {

			System.err.println("class = " + clazz.getSuperclass());
			try {
				if (clazz != null) {
					Field field = clazz.getDeclaredField(fieldName);
					if (field != null) {
						optional = Optional.of(field);
						break;
					}
				}
			} catch (NoSuchFieldException | SecurityException e) {
			}
		}
		return optional;
	}

	/**
	 * Finds the static field fieldName in the Class target
	 */
	static Optional<Field> findFieldInHierachy(final String fieldName, final Class<?> target) {

		Optional<Field> optional = Optional.empty();

		try {
			if (target != null) {
				Field field = target.getField(fieldName);
				if (field != null) {
					optional = Optional.of(field);
				}
			}
		} catch (NoSuchFieldException | SecurityException e) {
		}
		return optional;
	}

}
