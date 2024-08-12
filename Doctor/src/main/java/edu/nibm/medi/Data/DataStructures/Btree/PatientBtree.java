package edu.nibm.medi.Data.DataStructures.Btree;

import edu.nibm.medi.Data.DataObjects.Patient;
import edu.nibm.medi.Data.Node.PatientNode;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PatientBtree implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final int T = 3; // minimum degree of the tree
    private PatientNode<Patient> root;

    public PatientBtree() {
        root = new PatientNode<>();
    }

    public void insert(Patient patient) {
        this.root = insert(root, patient);
    }

    private PatientNode<Patient> insert(PatientNode<Patient> node, Patient patient) {
        if (node == null) {
            node = new PatientNode<>();
        }
        if (node.isLeaf()) {
            node.keys.add(patient);
            node.keys.sort((p1, p2) -> Integer.compare(p1.getPatientID(), p2.getPatientID()));
            if (node.keys.size() == 5) {
                split(node);
            }
        } else {
            int i = 0;
            while (i < node.keys.size() && patient.getPatientID() > node.keys.get(i).getPatientID()) {
                i++;
            }
            node.children.set(i, insert(node.children.get(i), patient));
        }

         return node;
    }

    private void split(PatientNode<Patient> node) {
        int mid = node.keys.size() / 2;
        Patient midKey = node.keys.get(mid);
        PatientNode<Patient> leftChild = new PatientNode<>();
        PatientNode<Patient> rightChild = new PatientNode<>();

        leftChild.keys.addAll(node.keys.subList(0, mid));
        rightChild.keys.addAll(node.keys.subList(mid+1, node.keys.size()));

        if (!node.isLeaf()) {
            leftChild.children.addAll(node.children.subList(0, mid + 1));
            rightChild.children.addAll(node.children.subList(mid + 1, node.children.size()));
        }

        node.keys.clear();
        node.keys.add(midKey);
        node.children.clear();
        node.children.add(leftChild);
        node.children.add(rightChild);
    }

    public Patient search(int patientID) {
        return search(root, patientID);
    }

    private Patient search(PatientNode<Patient> node, int patientID) {
        if (node == null || node.keys.isEmpty()) {
            return null;
        }

        int i = 0;
        while (i < node.keys.size() && patientID > node.keys.get(i).getPatientID()) {
            i++;
        }

        if (i < node.keys.size() && patientID == node.keys.get(i).getPatientID()) {
            return node.keys.get(i);
        }

        return search(node.children.get(i), patientID);
    }

    public void delete(int patientID) {
        root = delete(root, patientID);
    }

    private PatientNode<Patient> delete(PatientNode<Patient> node, int patientID) {
        if (null == node) {
            return null;
        }

        int i = 0;
        while (i < node.keys.size() && patientID > node.keys.get(i).getPatientID()) {
            i++;
        }

        if (i < node.keys.size() && patientID == node.keys.get(i).getPatientID()) {
            if (node.isLeaf()) {
                node.keys.remove(i);
                if (node.keys.size() < T - 1) {
                    fill(node);
                }
            } else {
                PatientNode<Patient> leftChild = node.children.get(i);
                PatientNode<Patient> rightChild = node.children.get(i + 1);

                if (leftChild.keys.size() >= T) {
                    Patient predecessor = getPredecessor(leftChild);
                    node.keys.set(i, predecessor);
                    delete(leftChild, predecessor.getPatientID());
                } else if (rightChild.keys.size() >= T) {
                    Patient successor = getSuccessor(rightChild);
                    node.keys.set(i, successor);
                    delete(rightChild, successor.getPatientID());
                } else {
                    merge(node, i);
                    delete(leftChild, patientID);
                }
            }
        } else {
            PatientNode<Patient> child = node.children.get(i);
            child = delete(child, patientID);
            if (child.keys.size() < T - 1) {
                fill(node);
            }
        }

        return node;
    }

    private Patient getPredecessor(PatientNode<Patient> node) {
        while (!node.isLeaf()) {
            node = node.children.get(node.children.size() - 1);
        }
        return node.keys.get(node.keys.size() - 1);
    }

    private Patient getSuccessor(PatientNode<Patient> node) {
        while (!node.isLeaf()) {
            node = node.children.get(0);
        }
        return node.keys.get(0);
    }

    private void fill(PatientNode<Patient> node) {
        if (node == root) {
            if (node.keys.isEmpty()) {
                root = node.children.get(0);
            }
            return;
        }

        int index = 0;
        PatientNode<Patient> parent = getParent(root, node);
        while (index < parent.children.size() && parent.children.get(index) != node) {
            index++;
        }

        if (index > 0 && parent.children.get(index - 1).keys.size() >= T) {
            borrowFromLeftSibling(parent, index);
        } else if (index < parent.children.size() - 1 && parent.children.get(index + 1).keys.size() >= T) {
            borrowFromRightSibling(parent, index);
        } else {
            if (index > 0) {
                merge(parent, index - 1);
            } else {
                merge(parent, index);
            }
        }
    }

    private PatientNode<Patient> getParent(PatientNode<Patient> node, PatientNode<Patient> child) {
        if (node.isLeaf()) {
            return null;
        }

        for (PatientNode<Patient> n : node.children) {
            if (n == child) {
                return node;
            }
            PatientNode<Patient> parent = getParent(n, child);
            if (parent != null) {
                return parent;
            }
        }

        return null;
    }

    private void borrowFromLeftSibling(PatientNode<Patient> parent, int index) {
        PatientNode<Patient> leftSibling = parent.children.get(index - 1);
        PatientNode<Patient> node = parent.children.get(index);

        node.keys.add(0, parent.keys.get(index - 1));
        parent.keys.set(index - 1, leftSibling.keys.remove(leftSibling.keys.size() - 1));

        if (!leftSibling.isLeaf()) {
            node.children.add(0, leftSibling.children.remove(leftSibling.children.size() - 1));
        }
    }

    private void borrowFromRightSibling(PatientNode<Patient> parent, int index) {
        PatientNode<Patient> rightSibling = parent.children.get(index + 1);
        PatientNode<Patient> node = parent.children.get(index);

        node.keys.add(parent.keys.get(index));
        parent.keys.set(index, rightSibling.keys.remove(0));

        if (!rightSibling.isLeaf()) {
            node.children.add(rightSibling.children.remove(0));
        }
    }

    private void merge(PatientNode<Patient> parent, int index) {
        PatientNode<Patient> leftChild = parent.children.get(index);
        PatientNode<Patient> rightChild = parent.children.get(index + 1);

        leftChild.keys.add(parent.keys.remove(index));
        leftChild.keys.addAll(rightChild.keys);
        leftChild.children.addAll(rightChild.children);

        parent.children.remove(rightChild);
    }

    public Patient getLast() {
        return getLast(root);
    }

    private Patient getLast(PatientNode<Patient> node) {
        if (node.isLeaf()) {
            return node.keys.get(node.keys.size() - 1);
        }
        return getLast(node.children.get(node.children.size() - 1));
    }

    public Patient getRoot() {
        if (root.keys.isEmpty()) {
            return null;
        }else{
            return root.keys.get(0);
        }
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        traverseInOrder(root, patients);
        return patients;
    }

    private void traverseInOrder(PatientNode<Patient> node, List<Patient> patients) {
        if (node != null && !node.keys.isEmpty()) {
            for (int i = 0; i < node.keys.size(); i++) {
                if (!node.isLeaf()) {
                    traverseInOrder(node.children.get(i), patients);
                }
                patients.add(node.keys.get(i));
            }
            if (!node.isLeaf()) {
                traverseInOrder(node.children.get(node.keys.size()), patients);
            }
        }
    }
}