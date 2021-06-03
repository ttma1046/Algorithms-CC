package bfs;

import java.util.LinkedList;

public class Node {
    private int id;
    State state;
    LinkedList<Node> adjacent = new LinkedList<Node>();
    private Node(int id) {
        this.id = id;
        this.state = State.Unvisited;
    }
}