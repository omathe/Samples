package fr.omathe.csv;

import java.util.Map;

import javafx.beans.property.SimpleStringProperty;

public interface ObjectBuilder {

	static Object instantiate(Class<?> type) throws ObjectBuilderException {

		Object object = null;

		if (type != null) {
			try {
				Class<?> c = Class.forName(type.getName());
				object = c.getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				throw new ObjectBuilderException("Failed to build object of type '" + type + "'");
			}
		}
		return object;
	}

	static Object build(Class<?> type, Map<String, String> valuedFields) throws ObjectBuilderException {

		Object object = null;

		if (valuedFields != null) {

			object = instantiate(type);

			if (object != null && valuedFields != null) {
				for (final Map.Entry<String, String> entry : valuedFields.entrySet()) {

					FieldValue<?> fieldValue = FieldInspector.findFieldValue(entry.getKey(), object);
					if (fieldValue.isPresent()) {
						fieldValue.getField().setAccessible(true);
						try {
							if (fieldValue.getField().getType().getName()
									.equals("javafx.beans.property.StringProperty")) {
								fieldValue.getField().set(object, new SimpleStringProperty(entry.getValue()));
							}
						} catch (IllegalArgumentException | IllegalAccessException e) {
							throw new ObjectBuilderException("Failed to set field '" + entry.getKey() + "' with value '" + entry.getValue() + "'");
						}
					} else {
						throw new ObjectBuilderException(
								"Unknown field '" + entry.getKey() + "' in class '" + type.getName() + "'");
					}
				}
			}
		}
		return object;
	}
}
