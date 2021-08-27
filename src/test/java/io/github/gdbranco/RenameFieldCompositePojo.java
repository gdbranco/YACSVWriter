package io.github.gdbranco;

public class RenameFieldCompositePojo {
	public SimplePojo simple;
	public String field;
	@CsvField(name = "Renamed Field")
	public String ignoredField;
	
	public RenameFieldCompositePojo(SimplePojo simple, String field, String ignoredField) {
		super();
		this.simple = simple;
		this.field = field;
		this.ignoredField = ignoredField;
	}

}
