module frogger {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.junit.jupiter.api;

    opens frogger.controller to javafx.fxml;
    exports frogger;
    exports frogger.model;
    exports frogger.controller;
}