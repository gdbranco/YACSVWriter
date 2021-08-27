package io.github.gdbranco;

import java.time.LocalDate;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;



public class CsvWriterTest  {
    
	@Test
    public void testSimplePojo() {
    	CsvWriter<SimplePojo> csvWriter = new CsvWriter<SimplePojo>();
    	LocalDate now = LocalDate.now();
		LocalDate plusDays = now.plusDays(2);
		String actual = csvWriter.writeCsv(Arrays.asList(new SimplePojo[] {
    			new SimplePojo("Alice", "Bennett", now),
    			new SimplePojo("Bob", "Russell", plusDays)}));
    	String expected = String.format("firstName;lastName;startDate\nAlice;Bennett;%s\nBob;Russell;%s\n", now, plusDays);
    	assertThat(actual).isEqualTo(expected);
    }
    
	@Test
    public void testCompositePojo() {
    	CsvWriter<CompositePojo> csvWriter = new CsvWriter<CompositePojo>();
    	LocalDate now = LocalDate.now();
    	LocalDate plusDays = now.plusDays(2);
		String actual = csvWriter.writeCsv(Arrays.asList(new CompositePojo[] {
    			new CompositePojo(new SimplePojo("Alice", "Bennett", now), "firstRowField"),
    			new CompositePojo(new SimplePojo("Bob", "Russell", plusDays), "secondRowField")}));
    	String expected = String.format("firstName;lastName;startDate;field\nAlice;Bennett;%s;firstRowField\nBob;Russell;%s;secondRowField\n", now, plusDays);
    	assertThat(actual).isEqualTo(expected);
    }
    
	@Test
    public void testIgnoreField() {
    	CsvWriter<IgnoredFieldCompositePojo> csvWriter = new CsvWriter<IgnoredFieldCompositePojo>();
    	LocalDate now = LocalDate.now();
    	LocalDate plusDays = now.plusDays(2);
		String actual = csvWriter.writeCsv(Arrays.asList(new IgnoredFieldCompositePojo[] {
    			new IgnoredFieldCompositePojo(new SimplePojo("Alice", "Bennett", now), "firstRowField", "firstRowIgnoredField"),
    			new IgnoredFieldCompositePojo(new SimplePojo("Bob", "Russell", plusDays), "secondRowField", "secondRowIgnoredField")}));
    	String expected = String.format("firstName;lastName;startDate;field\nAlice;Bennett;%s;firstRowField\nBob;Russell;%s;secondRowField\n", now, plusDays);
    	assertThat(actual).isEqualTo(expected);
    }
	
	@Test
    public void testRenameField() {
    	CsvWriter<RenameFieldCompositePojo> csvWriter = new CsvWriter<RenameFieldCompositePojo>();
    	LocalDate now = LocalDate.now();
    	LocalDate plusDays = now.plusDays(2);
		String actual = csvWriter.writeCsv(Arrays.asList(new RenameFieldCompositePojo[] {
    			new RenameFieldCompositePojo(new SimplePojo("Alice", "Bennett", now), "firstRowField", "firstRowRenamedField"),
    			new RenameFieldCompositePojo(new SimplePojo("Bob", "Russell", plusDays), "secondRowField", "secondRowRenamedField")}));
    	String expected = String.format("firstName;lastName;startDate;field;Renamed Field\nAlice;Bennett;%s;firstRowField;firstRowRenamedField\nBob;Russell;%s;secondRowField;secondRowRenamedField\n", now, plusDays);
    	assertThat(actual).isEqualTo(expected);
    }
	
	@Test
    public void testCustomPrimitiveField() {
    	CsvWriter<CustomPrimitivePojo> csvWriter = new CsvWriter<CustomPrimitivePojo>(CustomPrimitiveClass.class);
    	LocalDate now = LocalDate.now();
    	LocalDate plusDays = now.plusDays(2);
		String actual = csvWriter.writeCsv(Arrays.asList(new CustomPrimitivePojo[] {
    			new CustomPrimitivePojo(new CustomPrimitiveClass("Alice", "Bennett"), now),
    			new CustomPrimitivePojo(new CustomPrimitiveClass("Bob", "Russell"), plusDays)}));
    	String expected = String.format("fullName;startDate\nAlice Bennett;%s\nBob Russell;%s\n", now, plusDays);
    	assertThat(actual).isEqualTo(expected);
    }
}
