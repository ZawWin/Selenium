@echo off
setlocal

set CLASSPATH=C:/Users/zaw.win/.m2/repository/junit/junit/4.9/junit-4.9.jar
set CLASSPATH=%CLASSPATH%;"C:/Program Files/Java/jdk1.7.0_04/jre/lib"
set CLASSPATH=%CLASSPATH%;"C:/Users/zaw.win/Desktop/Eclipse Project/Selenium/target/classes"
set CLASSPATH=%CLASSPATH%;"C:/Users/zaw.win/Desktop/Eclipse Project/Selenium/target/test-classes"
set CLASSPATH=%CLASSPATH%;"C:/Users/zaw.win/.m2/repository/org/seleniumhq/selenium/selenium-api/2.23.1/selenium-api-2.23.1.jar"
set CLASSPATH=%CLASSPATH%;"C:/Users/zaw.win/.m2/repository/org/seleniumhq/selenium/selenium-java/2.23.1/selenium-java-2.23.1.jar"
set CLASSPATH=%CLASSPATH%;"C:/Users/zaw.win/.m2/repository/org/seleniumhq/selenium/selenium-ie-driver/2.23.1/selenium-ie-driver-2.23.1.jar"
set CLASSPATH=%CLASSPATH%;"C:/Users/zaw.win/.m2/repository/org/seleniumhq/selenium/selenium-remote-driver/2.23.1/selenium-remote-driver-2.23.1.jar"
set CLASSPATH=%CLASSPATH%;"C:/Users/zaw.win/.m2/repository/com/google/guava/guava/12.0/guava-12.0.jar"
set CLASSPATH=%CLASSPATH%;"C:/Users/zaw.win/.m2/repository/org/json/json/20080701/json-20080701.jar"
set CLASSPATH=%CLASSPATH%;"C:/Users/zaw.win/.m2/repository/org/apache/commons/commons-exec/1.1/commons-exec-1.1.jar"
set CLASSPATH=%CLASSPATH%;"C:/Users/zaw.win/.m2/repository/org/apache/httpcomponents/httpclient/4.1.2/httpclient-4.1.2.jar"
set CLASSPATH=%CLASSPATH%;"C:/Users/zaw.win/.m2/repository/org/apache/httpcomponents/httpcore/4.1.2/httpcore-4.1.2.jar"
set CLASSPATH=%CLASSPATH%;"C:/Users/zaw.win/.m2/repository/net/java/dev/jna/jna/3.4.0/jna-3.4.0.jar"
set CLASSPATH=%CLASSPATH%;"C:/Users/zaw.win/.m2/repository/commons-logging/commons-logging/1.1.1/commons-logging-1.1.1.jar"

echo java -classpath %CLASSPATH% org.junit.runner.JUnitCore com.Trapeze.NOVUS.Selenium.AllTests
java -classpath %CLASSPATH% org.junit.runner.JUnitCore com.Trapeze.NOVUS.Selenium.AllTests


endlocal
pause
