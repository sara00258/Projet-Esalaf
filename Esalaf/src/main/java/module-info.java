module com.example.esalaf {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires java.sql;
    requires commons.dbcp2;

    opens com.example.esalaf to javafx.fxml;

    opens  com.exemple.model;

    exports com.example.esalaf;
}