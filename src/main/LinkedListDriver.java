package main;

import java.util.ListIterator;

/**
 * Tests the linked list created for COMP 2526 201710 A3.
 * @author BCIT
 * @version 201710
 */
public class LinkedListDriver {
	
	private static LinkedList<String> myLinkedList = new LinkedList<String>();

	/**
	 * Adds the elements in the specified array to the specified LinkedList
	 * @param data an array
	 * @param testList a LinkedList
	 */
	public static void addToList(String[] data, LinkedList<String> testList) {
		for (String d : data) {
			testList.add(d);
		}
	}

	/**
	 * Displays the specified LinkedList.
	 * @param testList a LinkedList
	 */
	public static void displayList(LinkedList<String> testList) {
		if (testList == null)
			return;
		for (String d : testList)
			System.out.print(" " + d);
		System.out.println("");
	}

	/**
	 * Displays the elements in the specified array.
	 * @param testArray an array
	 */
	public static void displayArray(Object[] testArray) {
		if (testArray == null)
			return;
		for (Object o : testArray)
			System.out.print(" " + o);
		System.out.println("");
	}

	/**
	 * Displays the contents of the specified LinkedList in reverse.
	 * @param testList
	 */
	public static void displayListReverse(LinkedList<String> testList) {
		if (testList == null)
			return;
		ListIterator<String> it = (ListIterator<String>) testList.iterator();
		System.out.println("previous index = " + it.previousIndex());
		while (it.hasNext())
			it.next();
		System.out.println("next index = " + it.nextIndex());
		while (it.hasPrevious())
			System.out.print(it.previousIndex() + ": " + it.previous() + " ");
		System.out.println();
	}

	/**
	 * Drives the program.
	 * @param args command line arguments (not used)
	 */
	public static void main(String[] args) {
		String[] values = { "hello", "test", "kale", "nomad", "apple", "fish" };
		String[] test1 = { "test", "nomad", "fish" };
		String[] test2 = { "house", "fish", "car", "garage", "apple" };

		addToList(values, myLinkedList);
		displayList(myLinkedList);
		System.out.println("**********is kale in collection?" + myLinkedList.contains("kale"));
		System.out.println("**********is apples in collection?" + myLinkedList.contains("apples"));
		LinkedList<String> l2 = new LinkedList<String>();
		addToList(test1, l2);
		System.out.println("**********display test1 values");
		displayList(l2);

		System.out.println("**********does the collection contain all values in test1? " + myLinkedList.containsAll(l2));

		Object[] array = myLinkedList.toArray();
		System.out.println("**********convert list to an array and display as an array");
		displayArray(array);
		System.out.println("**********remove kale and reprint list");
		myLinkedList.remove("kale");
		displayList(myLinkedList);
		System.out.println("**********remove all values from list that are the same as list2");
		myLinkedList.removeAll(l2);
		displayList(myLinkedList);
		System.out.println("**********clear list2");
		l2.clear();
		displayList(l2);
		System.out.println("**********recreate list2 with list values");
		addToList(values, l2);
		displayList(l2);
		System.out.println("**********convert list2 to a String array and display");
		String[] stringArray = l2.toArray(new String[0]);
		displayArray(stringArray);
		System.out.println("**********retain all values in list2 that are found in common with list");
		l2.retainAll(myLinkedList);
		displayList(l2);

		System.out.println("**********construct a new list3 with mouse");
		LinkedList<String> l3 = new LinkedList<String>("mouse");
		displayList(l3);
		System.out.println("**********add test2 values to list3");
        addToList(test2, l3);
		displayList(l3);
		System.out.println("**********combine list2 with list3");
		l3.addAll(l2);
		displayList(l3);
		System.out.println("**********l3 in reverse");
		//displayListReverse(l3);
		System.out.println("**********retain all values in list3 that are found in common with list");
		l3.retainAll(myLinkedList);
		displayList(l3);

	}
}