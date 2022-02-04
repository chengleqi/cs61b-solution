public class LinkedListDeque<T> {
    private static class Node<T> {
        public Node<T> prev;
        public T item;
        public Node<T> next;

        public Node(Node<T> p, T i, Node<T> n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node<T> sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node<T>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        size++;
        Node<T> node = new Node<>(null, item, null);
        sentinel.next.prev = node;
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next = node;
    }

    public void addLast(T item) {
        size++;
        Node<T> node = new Node<>(null, item, null);
        sentinel.prev.next = node;
        node.prev = sentinel.prev;
        node.next = sentinel;
        sentinel.prev = node;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node<T> p = sentinel.next;
        while (p != sentinel) {
            System.out.println((p.item).toString());
            p = p.next;
        }
    }

    public T removeFirst() {
        size--;
        if (sentinel.next == sentinel) {
            return null;
        }
        Node<T> node = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        return node.item;
    }

    public T removeLast() {
        size--;
        if (sentinel.next == sentinel) {
            return null;
        }
        Node<T> node = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return node.item;
    }

    public T get(int index) {
        Node<T> p = sentinel.next;
        for (int i = 0; i < index; i++) {
            if (p == sentinel) {
                return null;
            }
            p = p.next;
        }
        return p.item;
    }

    private Node<T> getRecursiveHelper(int targetIndex, int currentIndex, Node<T> p) {
        if (p == sentinel) {
            return null;
        }
        if (currentIndex == targetIndex) {
            return p;
        }
        return getRecursiveHelper(targetIndex, currentIndex + 1, p.next);
    }

    public T getRecursive(int index) {
        Node<T> p = sentinel.next;
        return getRecursiveHelper(index, 0, p).item;
    }
}
