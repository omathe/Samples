package fr.omathe.metadata;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Optional;

import fr.omathe.data.Person;

public interface MetadataSamples {

	/**
	 * Retrieves the value of a field of an object
	 * The optional is empty if the field has not been found
	 * @param fieldName - The field name
	 * @param object - The object we want to retrieve the value of the field
	 * @return The value or an empty response
	 */
	@SuppressWarnings("unchecked")
	static <T> Optional<T> getFieldValue(String fieldName, Object object) {

		long t0 = Instant.now().toEpochMilli();
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
		System.out.println("duration = " + (Instant.now().toEpochMilli() - t0) + " ms");
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

	static void main(String[] args) {


		Person person = new Person("toto", 10);

		Optional<String> name = MetadataSamples.getFieldValue("name", person);
		System.out.println("name = " + (name.isPresent() ? name.get() : null));

		Optional<Integer> age = MetadataSamples.getFieldValue("age", person);
		System.out.println("age = " + (age.isPresent() ? age.get() : null));

	}
}
