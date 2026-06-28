module vidafacil {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires jbcrypt;

    exports vidafacil.application;
    exports vidafacil.controller;
    opens vidafacil.controller to javafx.fxml;
}