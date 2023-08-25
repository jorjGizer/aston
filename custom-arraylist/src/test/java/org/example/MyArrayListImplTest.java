package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;


class MyArrayListImplTest {
    private MyArrayList<Integer> myArrayList = new MyArrayList<>();
    private ArrayList<Integer> arrayList = new ArrayList<>();

    void addToMyList(){
        myArrayList.add(0, 1);
        myArrayList.add(1, 2);
        myArrayList.add(2, 3);
        myArrayList.add(3, 4);
        myArrayList.add(2, 6);
    }
    void addToArrayList(){
        arrayList.add(0, 1);
        arrayList.add(1, 2);
        arrayList.add(2, 3);
        arrayList.add(3, 4);
        arrayList.add(2, 6);
    }
    @Test
    void add() {
        addToMyList();
        Assertions.assertNotEquals(3, myArrayList.get(2));
        Assertions.assertEquals(2, myArrayList.get(1));
        Assertions.assertEquals(6, myArrayList.get(2));
        Assertions.assertEquals(5, myArrayList.size());
    }

    @Test
    void addAll() {
        addToArrayList();
        myArrayList.addAll(arrayList);
        Assertions.assertEquals(myArrayList.get(1),arrayList.get(1));
        Assertions.assertEquals(myArrayList.get(3),arrayList.get(3));
        Assertions.assertEquals(myArrayList.size(),arrayList.size());
    }
    @Test
    void clear() {
        addToMyList();
        myArrayList.clear();
        Assertions.assertEquals(0, myArrayList.size());
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> myArrayList.get(0));
    }

    @Test
    void indexRemove() {
        addToMyList();
        Assertions.assertEquals(myArrayList.remove(0), 1);
        Assertions.assertEquals(myArrayList.remove(3), 4);
        Assertions.assertEquals(myArrayList.remove(2), 3);
        Assertions.assertEquals(myArrayList.size(), 2);
    }

    @Test
    void objectRemove() {
        addToMyList();
        myArrayList.remove(Integer.valueOf(2));
        myArrayList.remove(Integer.valueOf(124));
        Assertions.assertEquals(myArrayList.size(), 4);
    }
    @Test
    void sort(){
        myArrayList.add(0, 74);
        myArrayList.add(1, 56);
        myArrayList.add(2, 93);
        myArrayList.add(3, 9235435);
        myArrayList.add(2, 235);
        addToMyList();
        myArrayList.sort(Comparator.naturalOrder());
        myArrayList.printArray();
    }
}