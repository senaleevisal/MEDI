module edu.nibm.medi.medi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens edu.nibm.medi to javafx.fxml;
    opens edu.nibm.medi.Data.DataObjects to javafx.base;
    opens edu.nibm.medi.Controller to javafx.base , javafx.fxml;
    exports edu.nibm.medi;
    exports edu.nibm.medi.Controller;
}
