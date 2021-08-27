package io.github.gdbranco;

import java.time.LocalDate;

public class SimplePojo {
	public String firstName;
    public String lastName;
    private LocalDate startDate;

    public SimplePojo(String firstName, String lastName, LocalDate startDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
    }

    public String name() {
        return this.firstName + " " + this.lastName;
    }

    public LocalDate getStart() {
        return this.startDate;
    }
}
