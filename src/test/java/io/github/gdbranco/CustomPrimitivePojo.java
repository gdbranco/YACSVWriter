package io.github.gdbranco;

import java.time.LocalDate;

public class CustomPrimitivePojo {
	private CustomPrimitiveClass fullName; 
    private LocalDate startDate;
	public CustomPrimitivePojo(CustomPrimitiveClass fullName, LocalDate startDate) {
		super();
		this.fullName = fullName;
		this.startDate = startDate;
	}
	public CustomPrimitiveClass getFullName() {
		return fullName;
	}
	public void setFullName(CustomPrimitiveClass fullName) {
		this.fullName = fullName;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
}
