module vidafacil {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;
    requires jbcrypt;

    exports vidafacil.application;
    exports vidafacil.controller;
    exports vidafacil.utils;

    opens vidafacil.controller to javafx.fxml;
    opens vidafacil.application to javafx.graphics, javafx.fxml;
}