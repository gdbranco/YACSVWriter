package io.github.gdbranco;

public class IgnoredFieldCompositePojo {
	public SimplePojo simple;
	public String field;
	@CsvIgnore
	public String ignoredField;
	
	public IgnoredFieldCompositePojo(SimplePojo simple, String field, String ignoredField) {
		super();
		this.simple = simple;
		this.field = field;
		this.ignoredField = ignoredField;
	}

}
