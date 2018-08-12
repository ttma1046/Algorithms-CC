package bfs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class ShortestReach {
    public static class Graph {
        private HashMap<Integer, Node> nodeLookup = new HashMap<Integer, Node>();

        private Node[] nodes;
        private static int EDGE_DISTANCE = 6;

        public Graph(int size) {

        }

        private class Node {
            private int id;
            int[] neighbors;
            private Node(int id) {
                this.id = id;
            }
        }

        private Node getNode(int id) {
            return nodeLookup.get(id);
        }

        public void addEdge(int first, int second) {
            Node s = getNode(first);
            Node d = getNode(second);
            s.neighbors.add(d);
        }

        public int[] shortestReach(int startId) {
            LinkedList<Integer> queue = new LinkedList<Integer>();
            queue.add(startId);

            int[] distances = new int[nodes.length];

            Arrays.fill(distances, -1);

            distances[startId] = 0;

            while(!queue.isEmpty()) {
                int node = queue.poll();
                for (int neighbor : nodes[node].neighbors)
                {
                    if (distances[neighbor] == -1) {
                        distances[neighbor] = distances[node] + EDGE_DISTANCE;
                        queue.add(neighbor);
                    }

                }
            }

            return distances;
        }
    }
}