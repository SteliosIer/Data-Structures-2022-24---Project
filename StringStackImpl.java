import java.io.PrintStream;
import java.util.NoSuchElementException;

/*
 * Stack implementation using singly linked list.
 */
public class StringStackImpl<T> implements StringStack<T> {

    private Node<T> top = null;
    private int length = 0;

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.setNext(top);
        top = newNode;
        length++;
    }

    @Override
    public T pop() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T data = top.getData();
        Node<T> temp = top.getNext();
        top = temp;
        length--;
        return data;
    }

    @Override
    public T peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return top.getData();
    }

    @Override
    public void printStack(PrintStream stream) {
        if (isEmpty()) {
            stream.println("Stack is empty.");
        } else {
            Node<T> temp = top;
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
