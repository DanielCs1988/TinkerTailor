package com.danielcs88.tinkertailor;

import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> test = new CircleList<>();
        for (int i = 0; i < 10; i++) {
            test.add(i);
        }
        System.out.println("Size: " + test.size() + "\n");
        Iterator<Integer> iter = test.iterator();
        for (int i = 0; i < 20; i++) {
            System.out.println(iter.next());
        }
    }
}
