[contributors-url]
[forks-url]
[stars-url]
[issues-url]
[license-url]
[linkedin-url]

<br />
<p align="center">

  <h3 align="center">YACSVWriter</h3>

  <p align="center">
    Yet Another CSV Writer is an easy to use java library that maps POJOs to CSV string
    <br />
  </p>
</p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>

## Getting Started

Download the released version .jar or add dependency to maven ([![Maven Central](https://img.shields.io/maven-central/v/io.github.gdbranco/yacsvwriter.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.gdbranco%22%20AND%20a:%22yacsvwriter%22))

```
<dependency>
  <groupId>io.github.gdbranco</groupId>
  <artifactId>yacsvwriter</artifactId>
  <version>0.0.2</version>
</dependency>
```

## Usage

**Please refer to the tests included for now, I'll be adding examples in the readme soon**

* Currently works for simple POJOs, composite POJOs, complex POJOs.
* Accepts custom field name as well as field ignoring via java annotation.
```java
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
 CsvWriter<IgnoredFieldCompositePojo> csvWriter = new CsvWriter<IgnoredFieldCompositePojo>();
    	LocalDate now = LocalDate.now();
    	LocalDate plusDays = now.plusDays(2);
		String csvString = csvWriter.writeCsv(Arrays.asList(new IgnoredFieldCompositePojo[] {
    			new IgnoredFieldCompositePojo(new SimplePojo("Alice", "Bennett", now), "firstRowField", "firstRowIgnoredField"),
    			new IgnoredFieldCompositePojo(new SimplePojo("Bob", "Russell", plusDays), "secondRowField", "secondRowIgnoredField")}));
```
* Able to utilize overloading of toString in classes to shorten the amount of fields within a specific class, the list of classes can be passed via varargs within the constructor.
```java
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
CsvWriter<RenameFieldCompositePojo> csvWriter = new CsvWriter<RenameFieldCompositePojo>();
    	LocalDate now = LocalDate.now();
    	LocalDate plusDays = now.plusDays(2);
		String csvString = csvWriter.writeCsv(Arrays.asList(new RenameFieldCompositePojo[] {
    			new RenameFieldCompositePojo(new SimplePojo("Alice", "Bennett", now), "firstRowField", "firstRowRenamedField"),
    			new RenameFieldCompositePojo(new SimplePojo("Bob", "Russell", plusDays), "secondRowField", "secondRowRenamedField")}));
```

## Roadmap

No know issues at the moment

## Contributing

Contributions make the open source community the best place to learn and create. Your efforts are **greatly appreciated**.

To contribute please follow the steps bellow:

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/YourFeatureName`)
3. Commit your Changes (`git commit -m 'Add some new feature'`)
4. Push to the Branch (`git push origin feature/YourFeatureName`)
5. Open a Pull Request

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Contact

Guilherme David Branco - gdbranco@gmail.com

Project Link: [https://github.com/gdbranco/YACSVWriter](https://github.com/gdbranco/YACSVWriter)

[contributors-url]: https://github.com/gdbranco/YACSVWriter/graphs/contributors
[forks-url]: https://github.com/gdbranco/YACSVWriter/network/members
[stars-url]: https://github.com/gdbranco/YACSVWriter/stargazers
[issues-url]: https://github.com/gdbranco/YACSVWriter/issues
[license-url]: https://github.com/gdbranco/YACSVWriter/blob/main/LICENSE
[linkedin-url]: https://www.linkedin.com/in/guilherme-david-branco-1b4a4b137/
