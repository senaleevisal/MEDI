package edu.nibm.medi.Data.DataStructures.List;

import java.util.Arrays;

public class ArrayList<T> {
    private T[] array;
    private int size;

    // Default constructor
    public ArrayList() {
        array = (T[]) new Object[10]; // Initial capacity of 10
        size = 0;
    }

    // Add element to the list
    public void add(T element) {
        if (size == array.length) {
            resizeArray();
        }
        array[size] = element;
        size++;
    }

    // Get element at a specific index
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return array[index];
    }

    // Remove element at a specific index
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T removedElement = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        return removedElement;
    }

    // Get the current size of the list
    public int size() {
        return size;
    }

    // Resize the internal array when it gets full
    private void resizeArray() {
        array = Arrays.copyOf(array, array.length * 2);
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Clear the list
    public void clear() {
        array = (T[]) new Object[10]; // Reset array with initial capacity
        size = 0;
    }

    // Print the list elements
    public void printList() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    // Add all elements from another list
    public void addAll(ArrayList<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    // Get a sublist from the list
    public ArrayList<T> subList(int i, int mid) {
        ArrayList<T> subList = new ArrayList<>();
        for (int j = i; j < mid; j++) {
            subList.add(array[j]);
        }
        return subList;
    }

    // Set element at a specific index
    public void set(int i, T element) {
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }
        array[i] = element;
    }
}