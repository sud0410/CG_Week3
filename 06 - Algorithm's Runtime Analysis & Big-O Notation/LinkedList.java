import java.util.NoSuchElementException;
public class LinkedList {

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    Node first;
    Node last;

    // Add element at the end
    void addLast(int data) {
        Node node = new Node(data);
        if (first == null) {
            first = last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    // Add element at the beginning
    void addFirst(int data) {
        Node node = new Node(data);
        if (first == null) {
            first = last = node;
        } else {
            node.next = first;
            first = node;
        }
    }

    // Return index of element
    int indexOf(int data) {
        int index = 0;
        Node current = first;
        while (current != null) {
            if (current.data == data)
                return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    // Check if element exists
    boolean contains(int data) {
        return indexOf(data) != -1;
    }

    // Remove first element
    void removeFirst() {
        if (first == null)
            throw new NoSuchElementException();

        if (first == last) {
            first = last = null;
        } else {
            Node second = first.next;
            first.next = null;
            first = second;
        }
    }

    // Remove last element
    void removeLast() {
        if (first == null)
            throw new NoSuchElementException();

        if (first == last) {
            first = last = null;
        } else {
            Node previous = getPrevious(last);
            last = previous;
            last.next = null;
        }
    }
    // Helper to find the node before a given node
    Node getPrevious(Node node) {
        Node current = first;
        while (current != null) {
            if (current.next == node)
                return current;
            current = current.next;
        }
        return null;
    }
    // Print the full list
    void printList() {
        Node current = first;
        while (current != null) {
            System.out.println("Current element: " + current.data);
            current = current.next;
        }
        System.out.println("Null val");
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addFirst(30);
        list.removeFirst();
        list.removeLast();

        System.out.println("The index of 30: " + list.indexOf(30));
        System.out.println("Contains 20? " + list.contains(20));
        list.printList();
    }
}
