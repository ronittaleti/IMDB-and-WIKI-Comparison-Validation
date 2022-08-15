# IMDB Wikipedia Comparison Test
> Compares the release date and country info for two different movie pages on IMDB and Wikipedia

This is a Selenium/TestNG program that allows you to specify two different movie pages, one on IMDB and the other on Wikipedia, and runs an assertion test on the release dates and country info found on both of those pages. The pages can be specified through the command line or through the testng.xml file at the root of the project structure.

## Usage

Make sure you have Maven installed, and run this line on the command prompt in the project folder:

```shell
mvn package
```

This will create a folder called target, and within it is the compiled class files as well as a compiled executable jar file with a name like "XXXX-X.X-SNAPSHOT-jar-with-dependencies.jar".

Then you can run the aforementioned jar like you would with any other executable jar:

```shell
java -jar target/XXXX-X.X-SNAPSHOT-jar-with-dependencies.jar
```

## Options

You may change the movie pages used in the test from the testng.xml file, or you may specify it through the command line.

As an example, we will use the corresponding IMDB and Wikipedia pages for Pushpa: The Rise.

It can be specifed using the following arguments in the command line:

```shell
java -jar target/XXXX-X.X-SNAPSHOT-jar-with-dependencies.jar -i https://www.imdb.com/title/tt9389998/ -w https://en.wikipedia.org/wiki/Pushpa:_The_Rise
```
**OR**
```shell
java -jar target/XXXX-X.X-SNAPSHOT-jar-with-dependencies.jar --imdb https://www.imdb.com/title/tt9389998/ --wiki https://en.wikipedia.org/wiki/Pushpa:_The_Rise
```