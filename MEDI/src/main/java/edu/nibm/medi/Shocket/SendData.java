package edu.nibm.medi.Shocket;
import edu.nibm.medi.Application;
import edu.nibm.medi.Data.DataObjects.Patient;
import edu.nibm.medi.Data.DataStructures.Btree.PatientBtree;
import edu.nibm.medi.Data.DataStructures.LinkList.DoctorLinkList;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class SendData {
    public  void sendData() {
        try (Socket socket = new Socket("172.20.10.3", 12345)) {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            PatientBtree patientBTree = Application.getPatientBtree();
            DoctorLinkList doctorLinkList = Application.getDoctorLinkList();
            outputStream.writeObject(patientBTree);
            outputStream.writeObject(doctorLinkList);

            System.out.println("Sent PatientBTree and DoctorLinkList");

            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
