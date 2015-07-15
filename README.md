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
