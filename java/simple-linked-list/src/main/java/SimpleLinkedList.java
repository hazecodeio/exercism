import java.util.NoSuchElementException;

/*

Since this exercise has a difficulty of > 4 it doesn't come
with any starter implementation.
This is so that you get to practice creating classes and methods
which is an important part of programming in Java.

Please remove this comment when submitting your solution.

*/
public class SimpleLinkedList<E> {

    private int size;
    private Node first;
    private Node last;

    public SimpleLinkedList() {
    }

    public SimpleLinkedList(E[] values) {
        for (E e : values)
            push(e);
    }

    public int size() {
        return size;
    }

    public E pop() {
        if (size == 0)
            throw new NoSuchElementException();

        E e = first.element;

        if (first.next == null) {// one element left
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;

        return e;
    }

    public void push(E e) {
        Node newNode = new Node(null, e, null);
        if (first == null) {
            first = newNode;
            last = first;
        } else {
            first.prev = newNode;
            newNode.next = first;
            first = newNode;
        }
        size++;
    }

    public void reverse() {
        Node current = first;
        first = last;
        last = current;
        while (current != null) {
            Node next = current.next;
            Node prev = current.prev;
            current.next = prev;
            current.prev = next;
            current = next;
        }
    }

    public <T> T[] asArray(Class<T> classType) { // ToDO - TBC
        return null;
    }

    private class Node {
        E element;
        Node next;
        Node prev;

        public Node() {
        }

        Node(Node prev, E element, Node next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }
}