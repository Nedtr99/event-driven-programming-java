@echo off
set R=C:\Users\TED WANJOHI\.m2\repository\org\openjfx
set MP=%R%\javafx-base\17.0.12\javafx-base-17.0.12-win.jar;%R%\javafx-controls\17.0.12\javafx-controls-17.0.12-win.jar;%R%\javafx-fxml\17.0.12\javafx-fxml-17.0.12-win.jar;%R%\javafx-graphics\17.0.12\javafx-graphics-17.0.12-win.jar
set MYSQL=C:\Users\TED WANJOHI\.m2\repository\com\mysql\mysql-connector-j\8.3.0\mysql-connector-j-8.3.0.jar
set OUT=C:\devprojects\my projects\New folder\event-driven-programming-java\week10-university-system\out
"C:\Program Files\Java\jdk-24\bin\java.exe" --module-path "%MP%" -cp "%OUT%;%MYSQL%" --add-modules javafx.controls,javafx.fxml com.university.App
