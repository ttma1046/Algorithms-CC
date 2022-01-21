package queue;

/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 capacity);

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/
import java.util.Map;
import java.util.HashMap;

public class LRUCache_146 {
    class DlinkedNode {
        int key;
        int value;

        DlinkedNode prev;
        DlinkedNode next;

        DlinkedNode() {}

        DlinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private DlinkedNode head, tail;
    private int size;
    private int capacity;
    private HashMap<Integer, DlinkedNode> cache = new HashMap<>();

    public LRUCache_146(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DlinkedNode();
        tail = new DlinkedNode();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;        

        DlinkedNode node = cache.get(key);
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (!cache.containsKey(key)) {
            if (size == capacity) {
                DlinkedNode realTail = popTail();
                cache.remove(realTail.key);
                --size;
            }

            DlinkedNode newNode = new DlinkedNode(key, value);
            cache.put(key, newNode);
            addNode(newNode);
            ++size;
        } else {
            DlinkedNode node = cache.get(key);
            node.value = value;
            moveToHead(node);
        }
    }

    private void addNode(DlinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void addNode(DlinkedNode node, DlinkedNode after) {
        node.prev = after;
        node.next = after.next;
        after.next.prev = node;
        after.next = node;
    }

    private void removeNode(DlinkedNode node) {
        DlinkedNode prev = node.prev;
        DlinkedNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(DlinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    private DlinkedNode popTail() {
        DlinkedNode realTail = tail.prev;
        removeNode(realTail);

        return realTail;
    }
}

/*
Complexity Analysis:

Time complexity: O(1) both for put and get.

Space complexity: O(capacity) since the space is used only for a hashmap and double linked list with at most capacity + 1 elements.

*/