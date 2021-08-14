package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;

class Node {
    private int id;
    LinkedList<Node> adjacent = new LinkedList<Node>();
    private Node(int d) {
        this.id = d;
    }
}

class BFS_ripple {
    int BFS(Node start, Node target) {
        Queue<Node> q;
        Set<Node> visited;

        q.offer(start);
        visited.add(start);
        int step = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; ++i) {
                Node node = q.poll();

                if (node == target) return step;

                for (Node item : node.adjacent) {
                    q.offer(item);
                    visited.add(item);
                }
            }

            step++;
        }
    }
}