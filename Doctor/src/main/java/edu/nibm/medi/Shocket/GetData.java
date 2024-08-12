package edu.nibm.medi.Shocket;



import edu.nibm.medi.Data.DataStructures.Btree.PatientBtree;
import edu.nibm.medi.Data.DataStructures.LinkList.DoctorLinkList;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GetData {
    public  void GetData() {
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(12345)) {
                System.out.println("Server is listening on port 12345");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                PatientBtree patientBTree = (PatientBtree) inputStream.readObject();
                DoctorLinkList doctorLinkList = (DoctorLinkList) inputStream.readObject();

                System.out.println("Received PatientBTree and DoctorLinkList");

                inputStream.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}