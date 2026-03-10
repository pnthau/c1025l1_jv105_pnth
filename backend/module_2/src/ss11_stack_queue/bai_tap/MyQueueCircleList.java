package module_2.src.ss11_stack_queue.bai_tap;

public class MyQueueCircleList {
    private Node head;
    private Node rear;
    private int size = 0;

    public void enqueue(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            this.head = this.rear = newNode;
            this.size = 1;
            return;
        }
        this.rear.link = newNode;
        this.rear = newNode;
        this.rear.link = this.head;
        this.size++;
    }

    public Node dequeue() {
        if (this.head == null) {
            return null;
        }
        Node deletedNode = this.head;
        this.head = this.head.link;
        if (this.head == null) {
            this.rear = null;
        }
        return deletedNode;
    }

    public class Node {
        public Node(int data) {
            this.data = data;
            this.link = null;
        }

        public int data;
        public Node link;
    }
}
