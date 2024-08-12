package edu.nibm.medi.Controller;

import edu.nibm.medi.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    public void onAddPatientButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("patient_add.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage =  Application.getStage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onAddDoctorButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("doctor_add.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage =  Application.getStage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void removePatientButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("patient_remove.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage =  Application.getStage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void removeDoctorButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("doctor_remove.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage =  Application.getStage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void viewPatientButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("patient_view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage =  Application.getStage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void viewDoctorButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("doctor_view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage =  Application.getStage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onDoctorsAttendanceButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("doctor_attendance.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage =  Application.getStage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}