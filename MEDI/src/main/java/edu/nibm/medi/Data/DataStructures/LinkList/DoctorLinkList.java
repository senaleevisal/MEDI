package edu.nibm.medi.Data.DataStructures.LinkList;

import edu.nibm.medi.Data.DataObjects.Doctor;
import edu.nibm.medi.Data.Node.DoctorNode;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DoctorLinkList implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private DoctorNode head;
    private DoctorNode tail;

    public DoctorLinkList() {
        head = null;
        tail = null;
    }

    public void add(Doctor doctor) {
        DoctorNode newNode = new DoctorNode(doctor);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
         }
    }

    public Doctor search(int doctorID) {
        DoctorNode current = head;
        while (current != null) {
            if (current.doctor.getDoctorID() == doctorID) {
                return current.doctor;
            }
            current = current.next;
        }
        return null;
    }

    public Doctor searchByName(String name) {
        DoctorNode current = head;
        while (current != null) {
            if (current.doctor.getName().equalsIgnoreCase(name)) {
                return current.doctor;
            }
            current = current.next;
        }
        return null;
    }

    public String[] getAllDoctorNames() {
        List<String> names = new ArrayList<>();
        DoctorNode current = head;
        while (current != null) {
            names.add(current.doctor.getName());
            current = current.next;
        }
        return names.toArray(new String[0]);
    }

    public void delete(int doctorID) {
        DoctorNode current = head;
        while (current != null) {
            if (current.doctor.getDoctorID() == doctorID) {
                if (current == head) {
                    head = current.next;
                    head.prev = null;
                } else if (current == tail) {
                    tail = current.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                return;
            }
            current = current.next;
        }
    }


}
