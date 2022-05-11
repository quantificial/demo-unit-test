## tutorial site

https://ithelp.ithome.com.tw/articles/10196078

jsonPath reference：https://github.com/jayway/JsonPath

hamcrest reference：http://hamcrest.org/JavaHamcrest/javadoc/1.3/


https://stackoverflow.com/questions/6178583/maven-does-not-find-junit-tests-to-run

https://stackoverflow.com/questions/59193282/difference-between-junit-vintage-engine-and-junit-jupiter-engine

need to add the vintage engine for running test cases for junit4
```pom
<dependency>
        <groupId>org.junit.vintage</groupId>
        <artifactId>junit-vintage-engine</artifactId>
        <scope>test</scope>
</dependency>

```


generate surfire report

mvn surefire-report:report


