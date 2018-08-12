package linkedlist;

class Node {
    int data;
    Node next;
    public Node(int data) {
        this.data = data;
    }
}

public class LinkedList {
    Node head;

    public void append(int data) {
        if (head == null) {
            head = new Node(data);
            return;
        }

        Node current = head;
        while(current.next != null) {
            current = current.next;
        }

        current.next = new Node(data);
    }

    public void prepend(int data) {
        Node newHead = new Node(data);
        newHead.next = head;
        head = newHead;
    }

    public void insertAfterHead(int data) {
        if (head == null)
        {
            head = new Node(data);
            return;
        }

        Node newNode = new Node(data);
        newNode.next = head.next;
        head.next = newNode;
    }

    public Boolean search(int data)
    {
        if (head == null) { return false; }

        Node current = head;
        while(current != null)
        {
            if (current.data == data)
            {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public void deleteWithValue(int data) {
        if (head == null) return;
        if (head.data == data) {
            head = head.next;
            return;
        }

        Node current = head;
        while(current.next != null ) {
            if (current.next.data == data) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next = new Node(4);

        LinkedList list = new LinkedList();
        list.head = head;

        list.insertAfterHead(3);

        Node current = list.head;
        while(current != null) {
            System.out.printf("Input: %d\n",current.data);
            current = current.next;
        }

        System.out.println(list.search(3).toString());
        System.out.println(list.search(6).toString());
    }
}
