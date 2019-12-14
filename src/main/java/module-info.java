module frogger {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens frogger.controller to javafx.fxml;
    exports frogger;
    exports frogger.model;
}
