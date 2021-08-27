package io.github.gdbranco;

public class CustomPrimitiveClass {
	private String firstName;
	private String lastName;
	
	public CustomPrimitiveClass(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("%s %s", firstName, lastName);
	}
}
