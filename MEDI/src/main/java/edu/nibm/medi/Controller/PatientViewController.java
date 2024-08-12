package edu.nibm.medi.Controller;

import edu.nibm.medi.Application;
import edu.nibm.medi.Data.DataObjects.Patient;
import edu.nibm.medi.Data.DataStructures.Btree.PatientBtree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientViewController {

    @FXML
    private TableView<Patient> patientTableView;

    @FXML
    private TableColumn<Patient, String> nameColumn;

    @FXML
    private TableColumn<Patient, Integer> ageColumn;

    @FXML
    private TableColumn<Patient, String> contactNumberColumn;

    @FXML
    private TableColumn<Patient, Integer> doctorColumn;

    @FXML
    private TableColumn<Patient, String> descriptionColumn;

    private PatientBtree patientBTree = Application.getPatientBtree();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        contactNumberColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        doctorColumn.setCellValueFactory(new PropertyValueFactory<>("d_ID"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        loadPatients();
    }

    @FXML
    private void loadPatients() {
        ObservableList<Patient> patients = FXCollections.observableArrayList(patientBTree.getAllPatients());
        patientTableView.setItems(patients);
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