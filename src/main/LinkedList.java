package main;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.IntStream;

/**
 * LinkedList creates list of E elements and provides controls for various alterations to the list.
 *
 * @author Hamish
 * @date 2017-03-25
 */
public class LinkedList<E> implements Collection<E> {

    private Node head;
    private Node tail;
    // maybe add size

    /**
     * Constructor: Default.
     */
    LinkedList() {
        head = tail = null;
    }

    /**
     * Constructor: Overloaded to accept initial data input.
     *
     * @param e The data to be input as first element in list.
     */
    LinkedList(E e) {
        head = new Node(e);
        tail = head;
    }

    /**
     * Getter for head.
     *
     * @return First element in the list.
     */
    Node getHead() {
        return head;
    }

    /**
     * Getter for tail.
     *
     * @return Last Node in the list.
     */
    Node getTail() {
        return tail;
    }

    /**
     * Add a new element to the list.
     *
     * @param e Data to be added.
     * @return True if new element added to list, false if nothing has changed.
     */
    @Override
    public boolean add(E e) {

        Node newNode = new Node(e);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }

        return true;
    }

    /**
     * Size of the linked list; how many elements are within the list.
     *
     * @return Size of the list.
     */
    @Override
    public int size() {
        Node temp;
        int count;
        if (head == null) {
            return 0;
        } else {
            temp = head;
            count = 1;
            while (temp.next != null) {
                temp = temp.next;
                count++;
            }
            return count;
        }
    }

    /**
     * Indicates if there are no elements in the list.
     *
     * @return True if list is empty, false if there exists some elements.
     */
    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Indicates if there is existence of Object o inside list.
     *
     * @param o The element being searched for within the list.
     * @return True if element exists in list, false if not.
     */
    @Override
    public boolean contains(Object o) {

        for (E e : this) {
            if (e.equals(o)) {
                if (e.getClass() != o.getClass()) {
                    throw new ClassCastException();
                }
                return true;
            }
        }

        return false;
    }

    /**
     * Iterates through the list by creating Iterator.
     *
     * @return An anonymous Iterator object containing LinkedList data.
     */
    @Override
    public Iterator<E> iterator() {
        return new ListIterator<E>() {

            int index = 0;

            /**
             * Indicates if there is another element after current.
             * @return True if list has more element(s), false if it is at the end of the list.
             */
            @Override
            public boolean hasNext() {
                return index < size();
            }

            /**
             * Retrieves the next element in the list.
             * @return The next Object of type E in the list.
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node temp = head;
                for (int i = 0; i < index; i++) {
                    temp = temp.next;
                }

                if (index <= size()) {
                    index++;
                }

                return temp.getData();
            }

            /**
             * Retrieves the previous element in the list.
             * @return The previous Object of type E in the list.
             */
            @Override
            public boolean hasPrevious() {
                return index > 0;
            }

            /**
             * Indicates if there is another element after current.
             * @return True if list has more element(s), false if it is at the end of the list.
             */
            @Override
            public E previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                Node temp = head;

                if (index > 0) {
                    index--;
                }

                for (int i = 0; i < index; i++) {
                    temp = temp.next;
                }

                return temp.getData();
            }

            /**
             * Get next index.
             * @return Index for next element.
             */
            @Override
            public int nextIndex() {
                return index + 1;
            }

            /**
             * Get previous index.
             * @return Index for previous element.
             */
            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            public void remove() {
                throw new NotImplementedException();
            }

            @Override
            public void set(E e) {
                throw new NotImplementedException();
            }

            @Override
            public void add(E e) {
                throw new NotImplementedException();
            }
        };
    }

    /**
     * Prints list to an array of type Object.
     *
     * @return An array of type Object containing the elements of the LinkedList.
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        Node temp = head;

        if (!isEmpty()) {
            for (int i = 0; i < size(); i++) {
                array[i] = temp.getData();
                temp = temp.getNext();
            }
        }
        return array;
    }

    /**
     * Returns an array of a specific type depending on the parameter input.
     *
     * @param a   Array of type T.
     * @param <T> Data type of a.
     * @return Array of type T.
     */
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size()) {
            return (T[]) Arrays.copyOf(toArray(), size(), a.getClass());
        }
        System.arraycopy(toArray(), 0, a, 0, size());
        if (a.length > size()) {
            a[size()] = null;
        }
        return a;
    }

    /**
     * Remove an Object from the LinkedList.
     *
     * @param o Object to be removed from LinkedList.
     * @return True if removal was successful, false if LinkedList
     * is empty or if the list did not contain Object o.
     */
    @Override
    public boolean remove(Object o) {

        LinkedList<E> lTemp = new LinkedList<>();

        if (!this.isEmpty() && this.contains(o)) {
            for (E e : this) {
                if (e.equals(o)) {
                    continue;
                }
                lTemp.add(e);
            }
            clear();
            this.addAll(lTemp);
            return true;
        }
        return false;
    }

    /**
     * Indicates if LinkedList contains all of the elements of specific Collection.
     *
     * @param c Collection list of elements.
     * @return True if LinkedList contains all contents of Collection c, false if one or more contents
     * of Collection c are missing from LinkedList.
     */
    @Override
    public boolean containsAll(Collection<?> c) {

        if (c.isEmpty()) {
            return false;
        }

        Iterator it = c.iterator();

        return IntStream.range(0, c.size()).allMatch(i -> this.contains(it.next()));

    }

    /**
     * Insert all elements of a Collection list into LinkedList.
     *
     * @param c Collection list of elements to be inserted into LinkedList.
     * @return True if Collection c elements were added to LinkedList,
     * false if c is empty and no changes were made.
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0) {
            return false;
        }
        for (E e : c) {
            this.add(e);
        }

        return true;
    }

    /**
     * Remove all elements of Collection c from LinkedList.
     *
     * @param c Collection of elements to be removed from LinkedList.
     * @return True if one or more elements from Collection c were removed from LinkedList.
     */
    @Override
    public boolean removeAll(Collection<?> c) {

        int removed = 0;

        for (Object aC : c) {
            remove(aC);
            removed++;
        }
        return removed > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LinkedList<?> that = (LinkedList<?>) o;

        return (head != null ? head.equals(that.head) : that.head == null)
                && (tail != null ? tail.equals(that.tail) : that.tail == null);
    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + (tail != null ? tail.hashCode() : 0);
        return result;
    }

    /**
     * Removes all elements from LinkedList that don't correspond with elements from Collection c.
     *
     * @param c Collection of elements to be retained in LinkedList.
     * @return True if changes were made, false if no changes were made.
     */
    @Override
    public boolean retainAll(Collection<?> c) {

        int removed = 0;

        LinkedList<E> lTemp = new LinkedList<>();

        Object[] aTemp = c.toArray();

        if (!this.isEmpty()) {
            for (E e : this) {
                for (Object anATemp : aTemp) {
                    if (!e.equals(anATemp)) {
                        removed++;
                        continue;
                    }
                    lTemp.add(e);
                }
            }
            clear();
            this.addAll(lTemp);
        }
        return removed > 0;
    }

    /**
     * Clear LinkedList of all elements.
     */
    @Override
    public void clear() {
        head = tail = null;
    }

    /**
     * Inner-class: Node. Node holds data and are the links in the LinkedList.
     */
    public class Node {
        private E data;
        private Node previous;
        private Node next;

        /**
         * Constructs Node.
         *
         * @param e Element
         */
        Node(E e) {
            data = e;
            next = previous = null;
        }

        /**
         * Get the data from a Node.
         *
         * @return Data from the LinkedList elements.
         */
        E getData() {
            return data;
        }

        /**
         * Set data for element.
         *
         * @param data Data held in Node.
         */
        void setData(E data) {
            this.data = data;
        }

        /**
         * Getter for a Node's next.
         *
         * @return A Node's next link.
         */
        Node getNext() {
            return next;
        }

        /**
         * Set next element in LinkedList.
         *
         * @param next Next element in the LinkedList.
         */
        void setNext(Node next) {
            this.next = next;
        }

        /**
         * Getter for a Node's previous.
         *
         * @return A Node's previous link.
         */
        Node getPrevious() {

            return previous;
        }

        /**
         * Set previous element in the LinkedList.
         *
         * @param previous Previous element in the LinkedList.
         */
        void setPrevious(Node previous) {
            this.previous = previous;
        }
    }

}
