# Maven Exercises

A simple collection of Maven exercises for use in a 2-hour introductory training session. You should have
[Maven 3](https://maven.apache.org/download.cgi) or later installed. 

You can find the instructions for each exercise below. The exercises build on each other, so you need to complete them
in sequence. If you get stuck on an exercise the answers are available in branches, so for example: if you fail to get
exercise1 working, just `git checkout answer1` and continue from there.

# Exercises

## Exercise 1: Build the project 

Exercise: `git checkout exercise1`

In this exercise you simply need to build the project. Change into the project directory and then do
`mvn clean install`.

Oh no! It failed with a compilation error:
```
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR :
[INFO] -------------------------------------------------------------
[ERROR] /c:/dev/mattpwest/maven-exercises/src/main/java/za/co/entelect/forums/java/prac/task3/Corruption.java:[22,60] diamond operator is not supported in -source 1.5
  (use -source 7 or higher to enable diamond operator)
[ERROR] /c:/dev/mattpwest/maven-exercises/src/main/java/za/co/entelect/forums/java/example/App.java:[45,45] diamond operator is not supported in -source 1.5
  (use -source 7 or higher to enable diamond operator)
[ERROR] /c:/dev/mattpwest/maven-exercises/src/main/java/za/co/entelect/forums/java/prac/task2/BigBrother.java:[20,56] diamond operator is not supported in -source 1.5
  (use -source 7 or higher to enable diamond operator)
[ERROR] /c:/dev/mattpwest/maven-exercises/src/main/java/za/co/entelect/forums/java/prac/task1/ColorSpotter.java:[29,65] diamond operator is not supported in -source 1.5
  (use -source 7 or higher to enable diamond operator)
[INFO] 4 errors
```

You'll need to configure the [Maven Compiler Plugin to use Java 1.7 or 1.8](http://maven.apache.org/plugins/maven-compiler-plugin/examples/set-compiler-source-and-target.html).

Once you have this properly configured your build should succeed:
```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 10.171 s
[INFO] Finished at: 2015-07-12T11:45:34+02:00
[INFO] Final Memory: 21M/200M
[INFO] ------------------------------------------------------------------------
```

You can run one of the examples with: `java -cp target/maven-exercises-1.0.0-SNAPSHOT.jar za.co.entelect.forums.java.prac.task1.ColorSpotter`.

Answer: `git checkout answer1` 

## Exercise 2: Repositories

Exercise: `git checkout exercise2`

We're not actually going to use it in the project, but let's just add a big dependency to your `pom.xml` to explore the
benefits of the local repository and/or repository server:
```
<dependency>
    <groupId>com.badlogicgames.gdx</groupId>
    <artifactId>gdx</artifactId>
    <version>1.6.1</version>
</dependency>
```

To ensure that you don't already have the artifact in your local repository run: `rm -R ~/.m2/repository/com/badlogicgames`.

Now run `mvn clean install` and look at the output:
```
...
[INFO] ------------------------------------------------------------------------
[INFO] Building maven-exercises 1.0.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
Downloading: https://repo.maven.apache.org/maven2/com/badlogicgames/gdx/gdx/1.6.1/gdx-1.6.1.pom
Downloaded: https://repo.maven.apache.org/maven2/com/badlogicgames/gdx/gdx/1.6.1/gdx-1.6.1.pom (2 KB at 0.7 KB/sec)
Downloading: https://repo.maven.apache.org/maven2/com/badlogicgames/gdx/gdx-parent/1.6.1/gdx-parent-1.6.1.pom
Downloaded: https://repo.maven.apache.org/maven2/com/badlogicgames/gdx/gdx-parent/1.6.1/gdx-parent-1.6.1.pom (5 KB at 5.6 KB/sec)
Downloading: https://repo.maven.apache.org/maven2/com/badlogicgames/gdx/gdx/1.6.1/gdx-1.6.1.jar
Downloaded: https://repo.maven.apache.org/maven2/com/badlogicgames/gdx/gdx/1.6.1/gdx-1.6.1.jar (1722 KB at 323.7 KB/sec)
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ maven-exercises ---
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 12.894 s
...
```

Note how Maven downloaded the `gdx` artifact and its transitive dependency `gdx-parent` for you.

Now run `mvn clean install` again:
```
...
[INFO] ------------------------------------------------------------------------
[INFO] Building maven-exercises 1.0.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ maven-exercises ---
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 4.290 s
...
```

Note that Maven didn't need to download the dependencies this time, so the build was much faster. You can go explore the
contents of your local repository by browsing to `~/.m2/repository/` - you'll see that artefacts are organised in a
hierarchial structure:
```
~/.m2/repository
        \_ com
            \_ badlogicgames
                \_ gdx
                    \_ gdx
                        \_ 1.6.1
                    \_ gdx-parent
                        \_ 1.6.1
```

The rest of this exercise is optional and deals with configuring Maven to use the repository manager at your company to
speed up builds and reduce bandwidth used by Maven.

First copy `example.m2.settings.xml` into `~/.m2` and rename it to `settings.xml`.

Then edit the file and:
 * Delete the `<servers>` section (you're not going to be pushing artifacts for this exercise).
 * Go through the `<profiles>` section and replace the servers with the settings supplied by your instructor.
 * Note the `<activeProfiles>` section which enables this profile by default for all builds on your machine.

Now do `rm -R ~/.m2/repository/com/badlogicgames` followed by `mvn clean install` again and look at the output:
```
...
[INFO] ------------------------------------------------------------------------
[INFO] Building maven-exercises 1.0.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
Downloading: http://esjavatools:8080/artifactory/libs-release/com/badlogicgames/gdx/gdx/1.6.1/gdx-1.6.1.pom
Downloaded: http://esjavatools:8080/artifactory/libs-release/com/badlogicgames/gdx/gdx/1.6.1/gdx-1.6.1.pom (2 KB at 7.4 KB/sec)
Downloading: http://esjavatools:8080/artifactory/libs-release/com/badlogicgames/gdx/gdx-parent/1.6.1/gdx-parent-1.6.1.pom
Downloaded: http://esjavatools:8080/artifactory/libs-release/com/badlogicgames/gdx/gdx-parent/1.6.1/gdx-parent-1.6.1.pom (5 KB at 74.3 KB/sec)
Downloading: http://esjavatools:8080/artifactory/libs-release/com/badlogicgames/gdx/gdx/1.6.1/gdx-1.6.1.jar
Downloaded: http://esjavatools:8080/artifactory/libs-release/com/badlogicgames/gdx/gdx/1.6.1/gdx-1.6.1.jar (1722 KB at 4959.8 KB/sec)
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ maven-exercises ---
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 5.130 s
...
```

Note how Maven now downloads the artefacts from your repository manager, greatly speeding up the build.

Answer: `git checkout answer2`

## Exercise 3: Tests

Exercise: `git checkout exercise3`

Your example project includes some tests (OK - a test). To run it, execute `mvn clean test` and look at the output:
```
...
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running za.co.entelect.forums.java.AppTest
Woohoo - tests!
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.01 sec

Results :

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 3.880 s
...
```

If you examine your project there are some important things to note:
 * You are using `junit:3.8.1` for tests (that's *ancient*).
 * Your test file is named `AppTest.java`.

If you were to rename `AppTest.java` to `App.java` and rerun `mvn clean test` you'll see that tests are no longer
executed - this is because the Maven Surefire plugin by default only looks for tests in files matching the following
patterns:
 * `**/Test*.java`
 * `**/*Test.java`
 * `**/*TestCase.java`

Junit 3 is the default included in the project dependencies when you use an archetype to generate a Maven project, but
it's ancient and version 4's syntax is much more concise - let's upgrade our project to the latest version of JUnit
(`junit:4.12`).

Make the following changes to the test file `AppTest.java`:
 * Remove all import statements.
 * Remove `extends TestCase` from the class definition.
 * Remove the constructor.
 * Remove the static method `suite`.
 * Add the `@Test` annotation above the `testApp()` method.
 * Replace `assertTrue(true);` with `Assert.assertTrue(true);`.

Then update your `pom.xml` to use `junit:4.12` and use your IDE to add the missing imports in test `App.java`.

Now run `mvn clean test` to make sure your changes are working.  

Answer: `git checkout answer3`

## Exercise 4: Multi-module Web Project

Exercise: `git checkout exercise4`

### Step 1: Domain module

As your final exercise, let's convert the project into a multi-module web project. The overall structure of the new
version will be as follows:
```
 maven-exercises
   \_ domain: All the current code in the project will move into the domain.
   \_ web: Web module will contain a single controller and page that will show the output of the corruption exercise.
```

First edit your current project's `pom.xml`:
 * Change `<groupId>` value to `za.co.entelect.forums.java.maven.exercises`.
 * Change `<packaging>` value to `pom`.

Now you can generate your domain module using the simple project archetype:
```
mvn -B archetype:generate \
      -DarchetypeGroupId=org.apache.maven.archetypes \
      -DgroupId=za.co.entelect.forums.java.maven.exercises \
      -DartifactId=domain
```

Now make the following changes to the generated module's `pom.xml`:
 * Remove the redundant `<groupId>` (inherited from parent project).
 * Update the version to `1.0.0-SNAPSHOT` to be consistent with the parent project.
 * Add `<packaging>jar</packaging>`.
 * Remove `<version>` from your JUnit dependency.

Now move the code from `src/main/java/` and `src/test/java` in your parent project into the new module.

Now make the following changes to your parent `pom.xml`:
 * Wrap the existing `<dependencies>` element in a `<dependencyManagement>` element.
 * Delete the src folder from the parent module.

Finally run `mvn clean install` - if all went well your domain module should build and its test should run.

### Step 2: Web module

Begin by generating your web module:
```
mvn archetype:generate \
    -DgroupId=za.co.entelect.forums.java.maven.exercises \
    -DartifactId=web \
    -DarchetypeArtifactId=maven-archetype-webapp \
    -DinteractiveMode=false
```

Now make the following changes to the generated module's `pom.xml`:
 * Remove the redundant `<groupId>` (inherited from parent project).
 * Update the version to `1.0.0-SNAPSHOT` to be consistent with the parent project.
 * Remove `<version>` from your JUnit dependency.

Edit your generated `web.xml` to upgrade to servlet 3.0:
```
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         version="3.0">

    <display-name>Corruption Web Application</display-name>
</web-app>
```

Add the following properties to your `web/pom.xml`:
```
    <properties>
		<spring.version>4.1.1.RELEASE</spring.version>
		<jstl.version>1.2</jstl.version>
		<logback.version>1.0.13</logback.version>
		<jcl-over-slf4j.version>1.7.5</jcl-over-slf4j.version>
	</properties>
```

Add the following dependencies to your `web/pom.xml`:
```
    <dependency>
        <groupId>za.co.entelect.forums.java.maven.exercises</groupId>
        <artifactId>domain</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
    
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>${spring.version}</version>
        <exclusions>
            <exclusion>
                <groupId>commons-logging</groupId>
                <artifactId>commons-logging</artifactId>
            </exclusion>
        </exclusions>
    </dependency>

    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>jcl-over-slf4j</artifactId>
        <version>${jcl-over-slf4j.version}</version>
    </dependency>

    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>${logback.version}</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-web</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <!-- jstl -->
    <dependency>
        <groupId>jstl</groupId>
        <artifactId>jstl</artifactId>
        <version>${jstl.version}</version>
    </dependency>
```

Add the following build plugin to your `web/pom.xml` to use to run the web application:
```
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.2</version>
                <configuration>
                    <path>/corruption</path>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

Add a Spring controller `web/src/main/java/za/co/entelect/forums/java/maven/web/BaseController.java`:
```java
package za.co.entelect.forums.java.maven.web;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import za.co.entelect.forums.java.example.App;
import za.co.entelect.forums.java.example.domain.Gantry;
import za.co.entelect.forums.java.example.domain.Vehicle;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BaseController {

    private static final String VIEW_INDEX = "index";
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        Map<Vehicle, BigDecimal> vehicleBills = getVehicleBills();

        model.addAttribute("vehicles", vehicleBills.keySet());
        model.addAttribute("bills", vehicleBills);
        logger.debug("Showing vehicle bills : " + vehicleBills.toString());

        // Spring uses InternalResourceViewResolver and return back index.jsp
        return VIEW_INDEX;
    }

    private Map<Vehicle, BigDecimal> getVehicleBills() {
        List<Gantry> gantries = App.getGantryList();
        Map<Vehicle, BigDecimal> vehicleBill = new HashMap<>();

        for (Gantry gantry : gantries) {
            for (Vehicle vehicle : gantry.getVehicles()) {
                if (!vehicleBill.containsKey(vehicle)) {
                    vehicleBill.put(vehicle, new BigDecimal(gantry.getToll()));
                } else {
                    BigDecimal value = vehicleBill.get(vehicle);
                    vehicleBill.put(vehicle, value.add(new BigDecimal(gantry.getToll())));
                }
            }
        }

        return vehicleBill;
    }
}
```

Add a Spring configuration file `WEB-INF/mvc-dispatcher-servlet.xml`:
```
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="
        http://www.springframework.org/schema/beans     
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context 
        http://www.springframework.org/schema/context/spring-context.xsd">
 
	<context:component-scan base-package="za.co.entelect.forums.java.maven.web" />
 
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix">
			<value>/WEB-INF/pages/</value>
		</property>
		<property name="suffix">
			<value>.jsp</value>
		</property>
	</bean>
</beans>
```

Add Spring `web.xml` configuration:
```
    <servlet>
		<servlet-name>mvc-dispatcher</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>
 
	<servlet-mapping>
		<servlet-name>mvc-dispatcher</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>
 
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/mvc-dispatcher-servlet.xml</param-value>
	</context-param>
 
	<listener>
		<listener-class>
           org.springframework.web.context.ContextLoaderListener
        </listener-class>
	</listener>
```

Move `index.jsp` to `WEB-INF/pages` and give it some useful content:
```
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1>Corruption Bills</h1>

<c:forEach var="vehicle" items="${vehicles}">
    <h2>Vehicle : ${vehicle.registrationNumber}</h2>
    <p>Bill: R ${bills[vehicle]}</p>
</c:forEach>
</body>
</html>

```

Create `web/src/main/resources/logback.xml`:
```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <layout class="ch.qos.logback.classic.PatternLayout">

            <Pattern>
                %d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
            </Pattern>

        </layout>
    </appender>

    <logger name="za.co.entelect.forums.java.maven.web" level="debug"
            additivity="false">
        <appender-ref ref="STDOUT"/>
    </logger>

    <root level="debug">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>
```

If all went well you should now be able to build and run your web application:
 * `mvn clean install`
 * `mvn tomcat:run`
 * Then visit: http://locahost:8080/web/

Exercise: `git checkout answer4`
