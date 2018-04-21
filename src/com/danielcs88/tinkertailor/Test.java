package com.danielcs88.tinkertailor;

class Test {

    private static Test instance;
    private CircleList<Integer> circleList;

    private Test() {
        init();
    }

    private void init() {
        circleList = new CircleList<>();
        System.out.println("Test initializing...");
        System.out.println("Adding numbers 1-20...");
        for (int i = 20; i > 0; i--) {
            circleList.add(i);
        }
        System.out.println("List initialized: " + circleList + "\n");
    }

    static Test getInstance() {
        if (instance == null) {
            instance = new Test();
        }
        return instance;
    }

    void start() {
        System.out.println("Clearing list of size " + circleList.size() + "...");
        circleList.clear();
        System.out.println("List currently: " + circleList + "\n");

        System.out.println("Adding 10 new numbers...");
        for (int i = 10; i > 0; i--) {
            circleList.add(i * 5);
        }
        System.out.println("We should see multiples of 5 from 5 to 50: " + circleList + "\n");

        System.out.println("Adding a 13 to the middle (index 5)...");
        circleList.insert(5, 13);
        System.out.println("New list: " + circleList + "\n");

        System.out.println("Removing 13 from index 5...");
        circleList.remove(5);
        System.out.println("List now: " + circleList + "\n");

        System.out.println("Attempting to remove 45...");
        circleList.removeElement(45);
        System.out.println("List: " + circleList + "\n");

        System.out.println("Setting the number in the 2nd index (that is 15) to 140...");
        circleList.set(2, 140);
        System.out.println("List after the modification: " + circleList + "\n");

        System.out.println("Getting all the elements with an even number index...");
        for (int i = 0; i < 10; i += 2) {
            System.out.print(circleList.get(i) + " ");
        }

        System.out.println("\n\nUsing forEach method to print out all the triples...");
        circleList.forEach(elem -> System.out.print(elem * 3 + " "));

        System.out.println("\n\nAnd concluding the test by checking the size again and whether the list is empty:");
        System.out.println("Size: " + circleList.size() + " Is it empty? " + circleList.isEmpty());

        System.out.println("\nClearing and testing again:");
        circleList.clear();
        System.out.println("Size: " + circleList.size() + " Is it empty? " + circleList.isEmpty());
        System.out.println("\n-----TESTS CONCLUDED-----");
    }
}
