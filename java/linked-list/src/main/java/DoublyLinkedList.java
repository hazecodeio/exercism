import java.util.NoSuchElementException;

/*
 * push (insert value at back)
 * pop (remove value at back)
 * shift (remove value at front)
 * unshift (insert value at front)
 */
class DoublyLinkedList<E> {

    private int size;
    private Node first;
    private Node last;

    private void addFirst(E e) { // to the front
        Node newNode = new Node(null, e, null);

        if (first == null) { // when empty
            first = newNode;
            last = first;
        } else { // when at least one element
            first.prev = newNode;
            newNode.next = first;
            first = newNode;
        }

        size++;
    }

    private E removeFirst() { // from the front
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

    private void addLast(E e) { // to the back
        Node newNode = new Node(null, e, null);

        if (last == null) { // when empty
            last = newNode;
            first = last;
        } else { // when at least one element
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
    }

    private E removeLast() { // from the back
        if (size == 0)
            throw new NoSuchElementException();

        E e = last.element;

        if (last.prev == null) { // one element left
            last = null;
            first = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;

        return e;
    }

    /**
     * push (insert value at back)
     *
     * @param e
     */
    public void push(E e) {
        addLast(e);
    }

    /**
     * pop (remove value at back)
     *
     * @return
     */
    public E pop() {
        return removeLast();
    }

    /**
     * shift (remove value at front)
     *
     * @return
     */
    public E shift() {
        return removeFirst();
    }

    /**
     * unshift (insert value at front)
     *
     * @param e
     */
    public void unshift(E e) {
        addFirst(e);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private class Node {
        Node prev;
        E element;
        Node next;

        public Node() {
        }

        public Node(Node prev, E element, Node next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }
}