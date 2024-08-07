module edu.nibm.medi.medi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens edu.nibm.medi to javafx.fxml;
    exports edu.nibm.medi;
}