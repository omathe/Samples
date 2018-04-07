package fr.omathe.metadata;

import java.lang.reflect.Field;

public class FieldValue<T> {

	private Field field;
	private T value;

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

	public boolean isPresent() {
		return field != null;
	}

	@Override
	public String toString() {
		return "FieldValue [field=" + field + ", value=" + value + "]";
	}

}