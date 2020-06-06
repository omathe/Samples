package fr.omathe.csv;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {
	
	private Long id;

	private StringProperty firstName;

	private StringProperty lastName;
	
	private StringProperty email;

	public Player() {
		super();
		this.firstName = new SimpleStringProperty("");
		this.lastName = new SimpleStringProperty("");
		this.email = new SimpleStringProperty("");
	}

	public Player(final String firstName, final String lastName, final String email) {
		super();
		this.firstName = new SimpleStringProperty(firstName == null ? "" : firstName);
		this.lastName = new SimpleStringProperty(lastName == null ? "" : lastName);
		this.email = new SimpleStringProperty(email == null ? "" : email);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(final String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(final String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(final String email) {
		this.email.set(email);
	}

	public StringProperty nicknameProperty() {
		return email;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", firstName=" + firstName.get() + ", lastName=" + lastName.get() + ", email=" + email.get() + "]";
	}

}
