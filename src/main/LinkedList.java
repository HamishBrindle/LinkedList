package main;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * LinkedList creates list of E elements.
 * @author Hamish
 * @date 2017-03-25
 */
public class LinkedList<E> implements Collection<E> {

    private Node head;
    private Node tail;

    public LinkedList() {
        head = tail = null;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public LinkedList(E e) {
        head = new Node(e);
        tail = head;
    }

    public class Node {
        private E data;
        private Node previous;
        private Node next;

        /**
         * Constructs Node.
         * @param e Element
         */
        public Node(E e) {
            data = e;
            next = previous = null;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    @Override
    public boolean add(E e) {

        Node newNode = new Node(e);

        // Node is empty
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        return true;
        // Node already has element
    }

    @Override
    public int size() {
        Node temp;
        int count;
        if (head == null) {
            return 0;
        } else {
            temp = head;
            count = 1;
            while(temp.next != null) {
                temp = temp.next;
                count++;
            }
            return count;
        }

    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public boolean contains(Object o) {

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                Node temp = head;

                for (int i = 0; i < index; i++) {
                    temp = temp.next;
                }

                index++;

                return temp.getData();

            }
        };
    }

    public ListIterator<E> listIterator(int index) {
        return new ListIterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public E previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(E e) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        Node temp = this.head;

        if (!isEmpty())
            for (int i = 0 ; i < size(); i++) {
                array[i] = temp.getData();

            }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {

        return null;
    }


    @Override
    public boolean remove(Object o) {

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if ( c.size() == 0)
            return false;
        for (E element : c) {
            this.add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {


        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head = tail = null;
    }


}
