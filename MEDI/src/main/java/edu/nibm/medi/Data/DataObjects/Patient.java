package edu.nibm.medi.Data.DataObjects;

import java.io.Serial;
import java.io.Serializable;

public class Patient implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int patientID;
    private String name;
    private int age;
    private String contact;
    private int d_ID;
    private String description;
    private int doctorId;


    public Patient(int patientID, String name, int age, String contact, int d_ID, String description) {
        this.patientID = patientID;
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.d_ID = d_ID;
        this.description = description;
    }

    public Patient() {
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setD_ID(int d_ID) {
        this.d_ID = d_ID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPatientID() {
        return patientID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getContact() {
        return contact;
    }

    public int getD_ID() {
        return d_ID;
    }

    public String getDescription() {
        return description;
    }
}