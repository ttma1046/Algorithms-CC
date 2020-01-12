package dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class MyGraph {
    private HashMap<Integer, Node> nodeLookup = new HashMap<Integer, Node>();

    public static class Node {
        private int id;
        LinkedList<Node> adjacent = new LinkedList<Node>();
        private Node(int id) {
            this.id = id;
        }
    }

    private Node getNode(int id) {
        return nodeLookup.getOrDefault(id, null);
    }

    public void addEdge(int source, int destination) {
        Node s = getNode(source);
        Node d = getNode(destination);
        s.adjacent.add(d);
    }

    public boolean hasPathDFS(int source, int destination) {
        Node s = getNode(source);
        Node d = getNode(destination);

        HashSet<Integer> visited = new HashSet<Integer>();
        return hasPathDFS(s, d, visited);
    }

    public boolean hasPathBFS(int source, int destination) {
        Node s = getNode(source);
        Node d = getNode(destination);

        HashSet<Integer> visited = new HashSet<Integer>();
        return hasPathBFS(s, d);
    }

    private boolean hasPathDFS(Node sourceNode, Node destinationNode, HashSet<Integer> isVisited) {
        if (isVisited.contains(sourceNode.id)) {
            return false;
        }

        if (sourceNode == destinationNode) {
            return true;
        }

        isVisited.add(sourceNode.id);
        if (sourceNode.adjacent != null && sourceNode.adjacent.size() > 0) {
            for(Node childNode: sourceNode.adjacent) {
                if (hasPathDFS(childNode, destinationNode, isVisited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasPathBFS(Node sourceNode, Node destinationNode) {
        if (sourceNode == destinationNode) {
            return true;
        }

        HashSet<Integer> visited = new HashSet<>();
        Queue<Node> myQueue = new LinkedList<Node>();

        myQueue.add(sourceNode);

        while (!myQueue.isEmpty()) {
            Node current = myQueue.poll();
            if (current == destinationNode) {
                return true;
            }

            if (visited.contains(current.id)) {
                continue;
            }
            visited.add(current.id);

            if (current.adjacent == null || current.adjacent.size() > 0) {
                for (Node childNode: current.adjacent) {
                    myQueue.add(childNode);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

    }


}
