javac -Xlint util\*.java

java util.CPCompiler
java util.AfmCompiler

javac -Xlint com\pdfjet\*.java
jar cf PDFjet.jar com\pdfjet\*.class

javac -cp PDFjet.jar -Xlint Example_01.java
javac -cp PDFjet.jar -Xlint Example_02.java
javac -cp PDFjet.jar -Xlint Example_03.java
javac -cp PDFjet.jar -Xlint Example_04.java
javac -cp PDFjet.jar -Xlint Example_05.java
javac -cp PDFjet.jar -Xlint Example_08.java
javac -cp PDFjet.jar -Xlint Example_13.java
javac -cp PDFjet.jar -Xlint Example_19.java

java Example_01
java Example_02
java Example_03
java Example_04
java Example_05
java Example_08
java Example_13
REM java Example_19
