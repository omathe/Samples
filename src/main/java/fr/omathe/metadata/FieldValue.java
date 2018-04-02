package fr.omathe.metadata;

public class FieldValue<T> {

	private boolean present;
	private T value;

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

	public FieldValue(boolean present) {
		super();
		this.present = present;
	}

	public void setValue(T object) {
		this.value = object;
	}

	public T getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "ValueField [present=" + present + ", value=" + value + "]";
	}

}
