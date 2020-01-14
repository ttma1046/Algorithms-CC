package linkedlist;
import java.util.ArrayList;

public class RemoveKthNodeFromEnd {
    public static void removeKthNodeFromEnd(MyLinkedList head, int k) {
        MyLinkedList pervious = head;
        MyLinkedList forward = head;
        int distance = k + 1;

        while(forward != null)   {
            forward = forward.next;

            if (distance == 0) {
                pervious = pervious.next;
            } else {
                distance--;
            }
        }

        if (distance == 0) {
            MyLinkedList temp = pervious.next;
            pervious.next = pervious.next.next;
            temp.next = null;
        } else if (distance == 1) {
            head.value = head.next.value;
            head.next = head.next.next;
        }
    }
}

class MyLinkedList {
    int value;
    MyLinkedList next = null;

    public MyLinkedList(int value) {
        this.value = value;
    }

    public ArrayList<Integer> getNodesInArray() {
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        MyLinkedList current = this;
        while (current != null) {
            nodes.add(current.value);
            current = current.next;
        }
        return nodes;
    }
}

class TestLinkedList extends MyLinkedList {
    public TestLinkedList(int value) {
        super(value);
    }

    public static void main(String [] args) {
        TestLinkedList test = new TestLinkedList(0);
        test.addMany(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9});

        System.out.println(test.getNodesInArray());
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        RemoveKthNodeFromEnd.removeKthNodeFromEnd(test, 10);
        System.out.println(test.getNodesInArray());
        System.out.println(compare(test.getNodesInArray(), expected));
    }

    public static boolean compare(ArrayList<Integer> arr1, int[] arr2) {
        System.out.println(arr1.size());
        System.out.println(arr2.length);
        if (arr1.size() != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.size(); i++) {
            if (arr1.get(i) != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public void addMany(int[] values) {
        MyLinkedList current = this;
        while (current.next != null) {
            current = current.next;
        }
        for (int value : values) {
            current.next = new MyLinkedList(value);
            current = current.next;
        }
    }

    public ArrayList<Integer> getNodesInArray() {
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        MyLinkedList current = this;
        while (current != null) {
            nodes.add(current.value);
            current = current.next;
        }
        return nodes;
    }
}
