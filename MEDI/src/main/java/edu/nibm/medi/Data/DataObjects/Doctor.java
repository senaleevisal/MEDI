package edu.nibm.medi.Data.DataObjects;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Doctor implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int doctorID;
    private String name;
    private String contactNumber;
    private int yearsOfExperience;
    private String level;

    public Doctor(int doctorID, String name, String contactNumber, int yearsOfExperience, String level) {
        this.doctorID = doctorID;
        this.name = name;
        this.contactNumber = contactNumber;
        this.yearsOfExperience = yearsOfExperience;
        this.level = level;
    }

    public Doctor() {
    }



    public int getDoctorID() {
        return doctorID;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public String getLevel() {
        return level;
    }
}
