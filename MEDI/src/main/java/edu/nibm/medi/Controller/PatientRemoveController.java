package edu.nibm.medi.Controller;

import edu.nibm.medi.Application;
import edu.nibm.medi.Data.DataObjects.Patient;
import edu.nibm.medi.Data.DataStructures.Btree.PatientBtree;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientRemoveController {

    @FXML
    private TextField patientIdTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField doctorTextField;

    @FXML
    private TextArea descriptionTextArea;

    private PatientBtree patientBTree = Application.getPatientBtree();

    @FXML
    public void initialize() {

    }

    @FXML
    public void onSearchButtonClick() {
        String patientId = patientIdTextField.getText().trim();
        Patient patient = patientBTree.search(Integer.parseInt(patientId));
        if (patient != null) {
            nameTextField.setText(patient.getName());
            ageTextField.setText(String.valueOf(patient.getAge()));
            phoneNumberTextField.setText(patient.getContact());
            doctorTextField.setText(Application.getDoctorLinkList().search(patient.getDoctorId()).getName());
            descriptionTextArea.setText(patient.getDescription());
        } else {
            nameTextField.setText("User not found");
            ageTextField.setText("User not found");
            phoneNumberTextField.setText("User not found");
            doctorTextField.setText("User not found");
            descriptionTextArea.setText("User not found");
        }
    }

    @FXML
    public void onPatientRemoveButtonClick() {
        String patientId = patientIdTextField.getText().trim();
        Patient patient = patientBTree.search(Integer.parseInt(patientId));
        if (patient != null) {
            patientBTree.delete(patient.getPatientID());
            nameTextField.clear();
            ageTextField.clear();
            phoneNumberTextField.clear();
            doctorTextField.clear();
            descriptionTextArea.clear();
        } else {
            nameTextField.setText("User not found");
            ageTextField.setText("User not found");
            phoneNumberTextField.setText("User not found");
            doctorTextField.setText("User not found");
            descriptionTextArea.setText("User not found");
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