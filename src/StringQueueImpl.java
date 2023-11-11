import java.io.PrintStream;
import java.util.NoSuchElementException;

/*
 * Queue implementation using singly linked list.
 */
public class StringQueueImpl<T> implements StringQueue<T> {

    private Node<T> head = null;
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
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        length++;
    }

    @Override
    public T get() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T data = head.getData();
        Node<T> temp = head.getNext();
        head = temp;
        length--;
        return data;
    }

    @Override
    public T peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.getData();
    }

    @Override
    public void printQueue(PrintStream stream) {
        if (isEmpty()) {
            stream.println("Queue is empty.");
        } else {
            Node<T> temp = head;
            while (temp != null) {
                stream.println(temp.getData());
                temp = temp.getNext();
            }
        }
    }

    @Override
    public int size() {
        return length;
    }
}
