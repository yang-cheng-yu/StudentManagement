module org.example.studentmanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.example.studentmanagement to javafx.fxml;
    exports org.example.studentmanagement;
}