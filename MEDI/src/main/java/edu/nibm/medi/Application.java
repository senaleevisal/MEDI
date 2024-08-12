package edu.nibm.medi;

import edu.nibm.medi.Data.DataStructures.Btree.PatientBtree;
import edu.nibm.medi.Data.DataStructures.LinkList.DoctorLinkList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class Application extends javafx.application.Application {

    private static PatientBtree patientBtree = new PatientBtree();
    private static DoctorLinkList doctorLinkList = new DoctorLinkList();
    private static Stage stage;
    public static Stage getStage() {
        return stage;
    }

    public static DoctorLinkList getDoctorLinkList() {
        return doctorLinkList;
    }
    public static PatientBtree getPatientBtree() {
        return patientBtree;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("home-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("MEDI");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}