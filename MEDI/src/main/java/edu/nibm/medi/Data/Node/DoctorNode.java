package edu.nibm.medi.Data.Node;

import edu.nibm.medi.Data.DataObjects.Doctor;

import java.io.Serial;
import java.io.Serializable;

public class DoctorNode implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public Doctor doctor;
    public DoctorNode next;
    public DoctorNode prev;

    public DoctorNode(Doctor doctor) {
        this.doctor = doctor;
        this.next = null;
        this.prev = null;
    }
}