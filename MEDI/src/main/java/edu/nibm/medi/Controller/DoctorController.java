package edu.nibm.medi.Controller;

import edu.nibm.medi.Application;
import edu.nibm.medi.Data.DataObjects.Doctor;
import edu.nibm.medi.Data.DataStructures.LinkList.DoctorLinkList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.function.UnaryOperator;

public class DoctorController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField empNoTextField;

    @FXML
    private TextField yearsOfExperienceTextField;

    @FXML
    private TextField contactNumberTextField;

    @FXML
    private ComboBox<String> levelComboBox;


    private String name;
    private String empNo;
    private int yearsOfExperience;
    private String contactNumber;
    private String level;


    @FXML
    public void initialize() {
        // Initialize ComboBox with levels
        ObservableList<String> levels = FXCollections.observableArrayList(
                "Junior",
                "Mid",
                "Senior",
                "Consultant"
        );
        levelComboBox.setItems(levels);
        levelComboBox.setValue("Select Level");

        // Add a listener to handle level selection
        levelComboBox.setOnAction(event -> {
            String selectedLevel = levelComboBox.getSelectionModel().getSelectedItem();
            System.out.println("Selected Level: " + selectedLevel);
            // Implement additional logic based on the selected level if needed
        });

        // Add TextFormatter to empNoTextField to allow only numeric input
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        empNoTextField.setTextFormatter(textFormatter);
        TextFormatter<String> yearsOfExperienceFormatter = new TextFormatter<>(filter);
        yearsOfExperienceTextField.setTextFormatter(yearsOfExperienceFormatter);

        contactNumberTextField.setTextFormatter(new TextFormatter<String>((change) -> {
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
    }

    @FXML
    public void onAddDoctorButtonClick() {
        boolean isValid = true;

        // Check if name field is empty
        if (nameTextField.getText().trim().isEmpty()) {
            nameTextField.setStyle("-fx-border-color: red;");
            nameTextField.requestFocus();
            isValid = false;
        } else {
            nameTextField.setStyle(""); // Reset style if not empty
            name = nameTextField.getText().trim(); // Store value to variable
        }

        // Check if EMP number field is empty
        if (empNoTextField.getText().trim().isEmpty()) {
            empNoTextField.setStyle("-fx-border-color: red;");
            if (isValid) {
                empNoTextField.requestFocus();
            }
            isValid = false;
        } else {
            empNoTextField.setStyle(""); // Reset style if not empty
            empNo = empNoTextField.getText().trim(); // Store value to variable
        }

        // Check if years of experience field is empty or invalid
        if (yearsOfExperienceTextField.getText().trim().isEmpty()) {
            yearsOfExperienceTextField.setStyle("-fx-border-color: red;");
            if (isValid) {
                yearsOfExperienceTextField.requestFocus();
            }
            isValid = false;
        } else {
            try {
                int years = Integer.parseInt(yearsOfExperienceTextField.getText().trim());
                if (years <= 0) {
                    yearsOfExperienceTextField.setStyle("-fx-border-color: red;");
                    if (isValid) {
                        yearsOfExperienceTextField.requestFocus();
                    }
                    isValid = false;
                } else {
                    yearsOfExperienceTextField.setStyle(""); // Reset style if valid
                    yearsOfExperience = years; // Store value to variable
                }
            } catch (NumberFormatException e) {
                yearsOfExperienceTextField.setStyle("-fx-border-color: red;");
                if (isValid) {
                    yearsOfExperienceTextField.requestFocus();
                }
                isValid = false;
            }
        }

        // Validate contact number field (10 digits, first digit is 0, digits only)
        if (contactNumberTextField.getText().trim().isEmpty()) {
            contactNumberTextField.setStyle("-fx-border-color: red;");
            if (isValid) {
                contactNumberTextField.requestFocus();
            }
            isValid = false;
        } else {
            String contactNumberInput = contactNumberTextField.getText().trim();
            if (!contactNumberInput.matches("0\\d{9}")) {
                contactNumberTextField.setStyle("-fx-border-color: red;");
                if (isValid) {
                    contactNumberTextField.requestFocus();
                }
                isValid = false;
            } else {
                contactNumberTextField.setStyle(""); // Reset style if valid
                contactNumber = contactNumberInput; // Store value to variable
            }
        }

        // Check if level is selected
        if (levelComboBox.getValue() == null || levelComboBox.getValue().equals("Select Level")) {
            levelComboBox.setStyle("-fx-border-color: red;");
            if (isValid) {
                levelComboBox.requestFocus();
            }
            isValid = false;
        } else {
            levelComboBox.setStyle(""); // Reset style if not empty
            level = levelComboBox.getValue(); // Store value to variable
        }


        if (
                nameTextField.getText().trim().isEmpty() ||
                empNoTextField.getText().trim().isEmpty() ||
                yearsOfExperienceTextField.getText().trim().isEmpty() ||
                contactNumberTextField.getText().trim().isEmpty() ||
                levelComboBox.getValue() == null || levelComboBox.getValue().equals("Select Level")
        ) {
            isValid = false;
            System.out.println("Please fill all fields");
        }

        // If all fields are valid, proceed with your logic
        if (isValid) {

            DoctorLinkList doctorLinkList = Application.getDoctorLinkList();
            doctorLinkList.add(new Doctor( Integer.parseInt(empNo), name, contactNumber, yearsOfExperience, level));
            nameTextField.clear();
            empNoTextField.clear();
            yearsOfExperienceTextField.clear();
            contactNumberTextField.clear();
            levelComboBox.setValue("Select Level");
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