package fr.omathe.metadata.classes;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface ClassTools {

	/**
	 * Finds the class identified by the name
	 * @param name - The class name (package + name, example : java.lang.String)
	 * @return An optional containing the class or an empty optional if the class has not been  found
	 */
	static Optional<Class<?>> findClass(final String name) {

		Optional<Class<?>> optional = Optional.empty();
		try {
			Class<?> theClass = Class.forName(name);
			optional = Optional.of(theClass);
		} catch (Exception e) {
		}
		return optional;
	}

	static Set<Class<?>> findAllClasses(final Set<Class<?>> set, final Type type, final Predicate<Class<?>> filter) {

		if (type != null) {
			if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				Class<?> clazz = (Class<?>) parameterizedType.getRawType();
				if (filter.test(clazz)) {
					set.add(clazz);
				}
				for (Type argumentType : parameterizedType.getActualTypeArguments()) {
					if (ParameterizedType.class.isAssignableFrom(argumentType.getClass())) {
						findAllClasses(set, argumentType, filter);
					} else {
						clazz = (Class<?>) argumentType;
						if (filter.test(clazz)) {
							set.add(clazz);
						}
					}
					findAllClasses(set, argumentType, filter);
				}
			} else {
				Class<?> clazz = (Class<?>) type;
				if (filter.test(clazz)) {
					set.add(clazz);
				}
			}
		}
		return set;
	}

	static String typeAsString(final Type type) {

		String typeAsString = "";

		if (type != null) {
			if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				Class<?> clazz = (Class<?>) parameterizedType.getRawType();

				String parameters = Arrays.stream(parameterizedType.getActualTypeArguments())
						.map(t -> typeAsString(t)).collect(Collectors.joining(","));

				typeAsString = clazz.getSimpleName() + "<" + parameters + ">";
			} else if (WildcardType.class.isAssignableFrom(type.getClass())) {
				typeAsString = "?";
			} else {
				Class<?> clazz = (Class<?>) type;
				typeAsString = clazz.getSimpleName();
			}
		}
		return typeAsString;
	}

}
