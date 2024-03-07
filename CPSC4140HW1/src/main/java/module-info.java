module org.app.cpsc4140hw1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.app.cpsc4140hw1 to javafx.fxml;
    exports org.app.cpsc4140hw1;
}