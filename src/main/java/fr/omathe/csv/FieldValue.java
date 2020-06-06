package fr.omathe.csv;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class FieldValue<T> {

	private Field field;
	private T value;
	private Type type;

	public FieldValue() {
		super();
	}

	public Field getField() {
		return field;
	}

	public void setField(final Field field) {
		this.field = field;
	}

	public T getValue() {
		return value;
	}

	public void setValue(final T object) {
		this.value = object;
	}

	public Type getType() {
		return type;
	}

	public void setType(final Type type) {
		this.type = type;
	}

	public boolean isPresent() {
		return field != null;
	}

	@Override
	public String toString() {
		return "FieldValue [field=" + field + ", value=" + value + "]";
	}

}
