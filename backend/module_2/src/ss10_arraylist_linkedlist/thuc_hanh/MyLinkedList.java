package module_2.src.ss10_arraylist_linkedlist.thuc_hanh;

public class MyLinkedList<E> {
    private Node head;
    private int numNodes = 0;

    public MyLinkedList() {
    }

    public MyLinkedList(Object data) {
        this.head = new Node(data);
        numNodes++;
    }

    public boolean add(int index, E data) {
        Node newNode = new Node(data);
        if (this.isEmpty()) {
            this.head = newNode;
            return true;
        }
        Node currentNode = this.head;

        for (int i = 1; i < index && currentNode != null; i++) {
            currentNode = currentNode.next;
        }

        Node nextNode = currentNode.next;
        currentNode.next = newNode;
        newNode.next = nextNode;
        this.numNodes++;
        return true;
    }

    public boolean addFirst(E data) {
        Node newNode = new Node(data);
        if (this.isEmpty()) {
            this.head = newNode;
            return true;
        }
        Node currentNode = this.head;

        newNode.next = currentNode;
        this.head = newNode;
        this.numNodes++;

        return true;
    }

    public boolean addLast(E data) {
        Node newNode = new Node(data);
        if (this.isEmpty()) {
            this.head = newNode;
            return true;
        }

        Node currentNode = this.head;

        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }

        currentNode.next = newNode;
        this.numNodes++;

        return true;
    }

    public void remove(int index) {
        Node currentNode = this.head;
        Node previousNode = this.head;

        for (int i = 1; i < index && currentNode.next != null; i++) {
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        previousNode.next = currentNode.next;
    }

    public void remove(Object o) {
        Node currentNode = this.head;
        Node previousNode = this.head;

        for (int i = 1; currentNode.data != o && currentNode != null; i++) {
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        previousNode.next = currentNode.next;
    }

    public E get(int index) {
        Node currentNode = this.head;
        for (int i = 1; i <= index && currentNode != null; i++) {
            currentNode = currentNode.next;
        }
        return (E) currentNode.data;
    }

    public E getFirst() {
        return (E) this.head.data;
    }

    public E getLast() {
        Node currentNode = this.head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        return (E) currentNode.data;
    }

    public void printList() {
        Node currentNode = this.head;
        while (currentNode != null) {
            System.out.println(currentNode.data);
            currentNode = currentNode.next;
        }
    }

    public int size() {
        return this.numNodes;
    }

    public Object clone() {
        MyLinkedList<E> linkedList = new MyLinkedList<>();
        Node currentNode = this.head;
        while (currentNode != null) {
            linkedList.addLast((E) currentNode.data);
            currentNode = currentNode.next;
        }
        return linkedList;
    }

    public boolean contains(E e) {
        Node currentNode = this.head;

        while (currentNode != null) {
            if (e.equals(currentNode.data)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public int indexOf(int index) {
        Node currentNode = this.head;

        for (int i = 0; i < this.numNodes && currentNode != null; i++) {
            if (i == index) {
                return i;
            }
            currentNode = currentNode.next;
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.numNodes == 0;
    }

    public void clear() {
        this.head = null;
        this.numNodes = 0;
    }

    private class Node {
        private Node next;
        private Object data;

        public Node(Object data) {
            this.data = data;
        }

        public Object getData() {
            return this.data;
        }
    }

}
