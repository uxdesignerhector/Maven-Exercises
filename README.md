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

Answer: `git checkout answer1` 
