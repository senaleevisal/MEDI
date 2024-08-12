package edu.nibm.medi.Controller;

import edu.nibm.medi.Application;
import edu.nibm.medi.Data.DataObjects.Doctor;
import edu.nibm.medi.Data.DataStructures.LinkList.DoctorLinkList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DoctorRemoveController {

    @FXML
    private TextField doctorIdTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField contactNumberTextField;

    @FXML
    private TextField yearsOfExperienceTextField;

    @FXML
    private TextField levelTextField;

    private DoctorLinkList doctorLinkList = Application.getDoctorLinkList();

    @FXML
    public void initialize() {

    }

    @FXML
    public void onSearchButtonClick() {
        String doctorId = doctorIdTextField.getText().trim();
        Doctor doctor = doctorLinkList.search(Integer.parseInt(doctorId));
        if (doctor != null) {
            nameTextField.setText(doctor.getName());
            contactNumberTextField.setText(doctor.getContactNumber());
            yearsOfExperienceTextField.setText(String.valueOf(doctor.getYearsOfExperience()));
            levelTextField.setText(doctor.getLevel());
        } else {
            nameTextField.setText("User not found");
            contactNumberTextField.setText("User not found");
            yearsOfExperienceTextField.setText("User not found");
            levelTextField.setText("User not found");
        }
    }

    @FXML
    public void onDoctorRemoveButtonClick() {
        String doctorId = doctorIdTextField.getText().trim();
        Doctor doctor = doctorLinkList.search(Integer.parseInt(doctorId));
        if (doctor != null) {
            doctorLinkList.delete(doctor.getDoctorID());
            nameTextField.clear();
            contactNumberTextField.clear();
            yearsOfExperienceTextField.clear();
            levelTextField.clear();
        } else {
            nameTextField.setText("User not found");
            contactNumberTextField.setText("User not found");
            yearsOfExperienceTextField.setText("User not found");
            levelTextField.setText("User not found");
        }
    }

    @FXML
    public void onLogoClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("home-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = Application.getStage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}