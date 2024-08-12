package edu.nibm.medi.Data.Node;




import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class PatientNode<E> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public ArrayList<E> keys;
    public ArrayList<PatientNode<E>> children;

    public PatientNode() {
        keys = new ArrayList<>();
        children = new ArrayList<>();
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }
}