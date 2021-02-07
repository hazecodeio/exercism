import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

class BinarySearchTree<T extends Comparable<T>> {

    Node<T> root;

    void insert(T value) {
        root = addRecursively(root, value);
    }

    Node<T> addRecursively(Node<T> current, T value) {
        if (current == null)
            return new Node<>(null, value, null);

        if(value.compareTo(current.value) <= 0)
            current.left = addRecursively(current.left, value);
        else if(value.compareTo(current.value) > 0)
            current.right = addRecursively(current.right, value);

        return current;
    }

    void traverseInOrder(Node<T> current, Consumer<T> c){
        if(current != null){
            traverseInOrder(current.left, c);
            c.accept(current.value);
            traverseInOrder(current.right, c);
        }
    }
    void traverseBF(Node<T> current, Consumer<T> c){
        Queue<Node<T>> q = new LinkedList<>();
        q.add(current);
        while(!q.isEmpty()){
            Node<T> n = q.remove();
            c.accept(n.value);
            if(n.left != null)
                q.add(n.left);
            if(n.right != null)
                q.add(n.right);
        }
    }

    List<T> getAsSortedList() {
        List<T> l = new ArrayList<>();
        traverseInOrder(root, l::add);
        return l;
    }

    List<T> getAsLevelOrderList() {
        List<T> l = new ArrayList<>();
        traverseBF(root, l::add);
        return l;
    }

    Node<T> getRoot() {
        return root;
    }

    static class Node<T> {

        T value;
        Node<T> left;
        Node<T> right;

        public Node() {
        }

        public Node(Node<T> left, T value, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        Node<T> getLeft() {
            return left;
        }

        Node<T> getRight() {
            return right;
        }

        T getValue() {
            return value;
        }
    }
}
