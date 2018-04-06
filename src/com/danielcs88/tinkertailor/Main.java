package com.danielcs88.tinkertailor;

import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> test = new CircleList<>();
        for (int i = 5; i > 0; i--) {
            test.add(i);
        }
        System.out.println(test);
        Iterator<Integer> iter = test.iterator();
        iter.next();
        iter.next();
        iter.remove();
        System.out.println(test);
        iter.next();
        iter.next();
        iter.next();
        iter.remove();
        System.out.println(test);
        iter.next();
        iter.next();
        iter.next();
        iter.remove();
        System.out.println(test);
        iter.next();
        iter.next();
        iter.next();
        iter.remove();
        System.out.println(test);
    }
}
