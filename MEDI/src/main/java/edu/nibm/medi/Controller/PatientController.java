package edu.nibm.medi.Controller;

import edu.nibm.medi.Application;
import edu.nibm.medi.Data.DataObjects.Patient;
import edu.nibm.medi.Data.DataStructures.Btree.PatientBtree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientController {

    @FXML
    private ComboBox<String> doctorComboBox;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    public void initialize() {
        // Create a TextFormatter that only allows digits
        ageTextField.setTextFormatter(new TextFormatter<String>((change) -> {
            String newText = change.getControlNewText();

            // Check if the input is a digit
            if (newText.matches("\\d*")) {
                if (newText.isEmpty()) {
                    return change;
                }
                int age = Integer.parseInt(newText);
                if(0 <= age && age <= 120){
                    return change;
                }
                return null;
            }
            return null; // Reject the change if it's not a valid digit
        }));

        // Add a listener to validate the age when the text changes
        ageTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateAge(newValue);
        });


        // Create a TextFormatter that only allows digits and limits the length to 10 characters
        phoneNumberTextField.setTextFormatter(new TextFormatter<String>((change) -> {
            String newText = change.getControlNewText();

            // Check if the input is a digit and the length is within 10
            if (newText.matches("\\d*") && newText.length() <= 10) {
                // Enforce that the first digit must be 0
                if (newText.length() == 1) {
                    if (!newText.equals("0")) {
                        return null; // Reject the change if the first character is not 0
                    }
                }
                return change;
            }
            return null; // Reject the change if it doesn't match the conditions
        }));

//         Sample list of doctor names
            ObservableList<String> doctorNames = FXCollections.observableArrayList(Application.getDoctorLinkList().getAllDoctorNames());

            // Populate the ComboBox with doctor names
            doctorComboBox.setItems(doctorNames);

    }
    private void validateAge(String ageText) {
        if (ageText.isEmpty()) {
            ageTextField.setStyle(""); // Reset style if empty
            return;
        }

        try {
            int age = Integer.parseInt(ageText);

            // Check if the age is within a reasonable range
            if (age >= 0 && age <= 120) {
                ageTextField.setStyle("-fx-border-color: #09b4ff;");
            } else {
                ageTextField.setStyle("-fx-border-color: red;");
            }
        } catch (NumberFormatException e) {
            ageTextField.setStyle("-fx-border-color: red;");
        }
    }

    @FXML
    public void onAddPatientButtonClick(ActionEvent actionEvent) {
        PatientBtree patientBtree = Application.getPatientBtree();
        patientBtree.insert(new Patient(patientBtree.getRoot() != null ? patientBtree.getLast().getPatientID()+1 : 1, nameTextField.getText(), Integer.parseInt(ageTextField.getText()), phoneNumberTextField.getText(), 1, descriptionTextArea.getText()));
        nameTextField.clear();
        ageTextField.clear();
        phoneNumberTextField.clear();
        descriptionTextArea.clear();
        doctorComboBox.getSelectionModel().clearSelection();
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
