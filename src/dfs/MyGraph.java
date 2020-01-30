package dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

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

    private boolean hasPathDFS(Node sourceNode, Node destinationNode, HashSet<Integer> isVisited) {
        if (isVisited.contains(sourceNode.id)) {
            return false;
        }

        if (sourceNode == destinationNode) {
            return true;
        }

        isVisited.add(sourceNode.id);
        if (sourceNode.adjacent != null && sourceNode.adjacent.size() > 0) {
            for (Node childNode : sourceNode.adjacent) {
                if (hasPathDFS(childNode, destinationNode, isVisited)) {
                    return true;
                }
            }
        }

        return false;
    }
}
