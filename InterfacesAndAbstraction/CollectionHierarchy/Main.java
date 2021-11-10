package CollectionHierarchy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AddCollection addCollection = new AddCollection();
        AddRemoveCollection addRemoveCollection = new AddRemoveCollection();
        MyListImpl myList = new MyListImpl();

        String[] input = scanner.nextLine().split("\\s+");
        int removeOperations = Integer.parseInt(scanner.nextLine());

        printAddCollection(input, addCollection);
        printAddCollection(input,addRemoveCollection);
        printAddCollection(input, myList);

        printRemove(removeOperations, addRemoveCollection);
        printRemove(removeOperations, myList);


    }

    private static void printRemove(int removeOperations, AddRemovable collection) {
        for (int i = 0; i < removeOperations; i++) {
            System.out.print(collection.remove() + " ");
        }
        System.out.println();
    }

    private static void printAddCollection(String[] input, Addable collection) {
        for (String s : input) {
            System.out.print(collection.add(s) + " ");
        }
        System.out.println();
    }
}
