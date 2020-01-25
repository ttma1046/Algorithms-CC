package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class IsOneNodeToOther_Fourpointone {
    boolean search(Node start, Node end) {
        if (start == null || end == null) return false;

        if (start == end) return true;

        Queue<Node> myQueue = new LinkedList<Node>();
        start.state = State.Visiting;

        myQueue.add(start);
        Node current;
        while(!myQueue.isEmpty()) {
            current = myQueue.poll();

            if (current != null) {
                for(Node child: current.adjacent) {
                    if (child.state == State.Unvisited) {
                        if (child == end) {
                            return true;
                        } else {
                            child.state = State.Visiting;
                            myQueue.add(child);
                        }
                    }
                }

                current.state = State.Visited;
            }
        }
        return false;
    }

    boolean searchII(Node start, Node end) {
        if (start == null || end == null) return false;

        if (start == end) return true;

        Queue<Node> myQueue = new LinkedList<Node>();

        myQueue.add(start);
        Node current;
        while(!myQueue.isEmpty()) {
            current = myQueue.poll();

            if (current.state == State.Unvisited) {
                current.state = State.Visiting;

                if (current == end) {
                    return true;
                }

                if (current.adjacent != null || current.adjacent.size() > 0) {
                    for (Node child : current.adjacent) {
                        if (child != null && child.state == State.Unvisited) {
                            myQueue.add(child);
                        }
                    }
                }

                current.state = State.Visited;
            }
        }
        return false;
    }
}
