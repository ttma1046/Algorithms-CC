package dfs;

public class IsOneNodeToOther_Fourpointone {
    boolean search(Node start, Node end) {
        if (start == null || end == null) return false;

        if (start == end) return true;
        return hasRouteDFS(start, end);
        // return hasRouteDFS(start, end);
    }

    private boolean hasRouteDFS(Node current, Node end) {
        if (current.state == State.Unvisited) {
            current.state = State.Visiting;
            if (current == end) return true;

            if (current.adjacent != null && current.adjacent.size() > 0) {
                for (Node u: current.adjacent) {
                    if (hasRouteDFS(u, end)) {
                        return true;
                    }
                }
            }
            current.state = State.Visited;
        }

        return false;
    }
}
