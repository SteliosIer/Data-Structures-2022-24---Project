import java.io.PrintStream;
import java.util.NoSuchElementException; 

/*
 * Queue implementation using singly linked list.
 */
public class StringQueueWithOnePointer<T> implements StringQueue<T> {

    private Node<T> tail = null;
    private int length = 0;

    @Override
    public boolean isEmpty() {
        return tail == null;
    }

    @Override
    public void put(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            newNode.setNext(newNode); // tail points to itself
            tail = newNode;
        } else {
            newNode.setNext(tail.getNext()); // tail points to the first item in the queue
            tail.setNext(newNode);
            tail = newNode;
        }
        length++;
    }

    @Override
    public T get() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<T> head = tail.getNext();
        T data = head.getData();
        if (head == tail) { // length == 1
            tail = null;
        } else {
            Node<T> temp = head.getNext();
            if (temp == tail) { // length == 2
                tail.setNext(tail);
            } else {
                tail.setNext(temp);
            }
        }
        length--;
        return data;
    }

    @Override
    public T peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.getNext().getData();
    }

    @Override
    public void printQueue(PrintStream stream) {
        if (isEmpty()) {
            stream.println("Queue is empty.");
        } else {
            Node<T> head = tail.getNext();
            while (head != tail) {
                stream.println(head.getData());
                head = head.getNext();
            }
            stream.println(head.getData());
        }
    }

    @Override
    public int size() {
        return length;
    }
}
