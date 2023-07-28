package org.example;

import java.util.*;
import java.util.function.Consumer;

public class MyArrayList<E>{
    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;

    public MyArrayList() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        elements = (E[]) new Object[initialCapacity];
        size = 0;
    }

    public void add(int index, E element) {
        ensureCapacity();
        for (int i = size; i >index ; i--) {
            elements[i] = elements[i-1];
        }
        elements[index] = element;
        size++;
    }
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Collection c is null");
        }
        ensureCapacity();

        for (E element : c) {
            elements[size++] = element;
        }

        return c.size() > 0;
    }
    public void clear(){
        E[] newArray = elements;
        for (int i = 0; i < size; i++) {
            newArray[i] = null;
        }
        size=0;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elements[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E oldValue = (E) elements[index];
        for (int i = index; i < size;i++) {
            elements[i] = elements[i+1];
        }
        size--;
        return oldValue;
    }
    public void remove(Object o){
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(o)) {
                    remove(i);
                    break;
                }
            }
    }
    public int size(){
        return size;
    }
    public void printArray(){
        for (E element : elements){
            if (element != null) {
                System.out.println(element);
            }
        }
    }
    public void sort(Comparator<? super E> c) {
        if (size > 1) {
            E[] tempArray = Arrays.copyOf(elements, size);
            quickSort(tempArray, 0, size - 1, c);
            System.arraycopy(tempArray, 0, elements, 0, size);
        }
    }

    private void quickSort(E[] array, int left, int right, Comparator<? super E> c) {
        if (left < right) {
            int pivotIndex = partition(array, left, right, c);
            quickSort(array, left, pivotIndex - 1, c);
            quickSort(array, pivotIndex + 1, right, c);
        }
    }

    private int partition(E[] array, int left, int right, Comparator<? super E> c) {
        E pivot = array[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (c.compare(array[j], pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, right);
        return i + 1;
    }

    private void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

}