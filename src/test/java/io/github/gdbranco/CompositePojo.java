package io.github.gdbranco;

public class CompositePojo {
	public SimplePojo simple;
	public String field;
	
	public CompositePojo(SimplePojo simple, String field) {
		super();
		this.simple = simple;
		this.field = field;
	}

}
