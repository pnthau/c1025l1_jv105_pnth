package module_2.src.ss11_stack_queue.thuc_hanh;

import java.util.EmptyStackException;
import java.util.LinkedList;

public class MyStack<T> {
    private LinkedList<T> stack;

    public MyStack() {
        this.stack = new LinkedList<T>();
    }

    public void push(T element) {
        stack.add(element);
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.removeFirst();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
