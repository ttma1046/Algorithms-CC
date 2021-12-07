package bfs;
/*
269. Alien Dictionary

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
]

Output: ""

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
*/
class Alien_Dictionary_269 {
    public String alienOrder(String[] words) {
        // Step 0: Create data structures and find all unique letters.
        Map<Character, List<Character>> adjList = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegree.put(c, 0);
                adjList.put(c, new ArrayList<>());
            }
        }

        // Step 1: Find all edges.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            // Check that word2 is not a prefix of word1.
            if (word1.length() > word2.length() && word1.startsWith(word2)) return "";

            // Find the first non match and insert the corresponding relation.
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    adjList.get(word1.charAt(j)).add(word2.charAt(j));
                    indegree.put(word2.charAt(j), indegree.get(word2.charAt(j)) + 1);
                    break;
                }
            }
        }

        // Step 2: Breadth-first search.
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (Character c : indegree.keySet())
            if (indegree.get(c) == 0) queue.offer(c);

        while (!queue.isEmpty()) {
            Character c = queue.poll();
            sb.append(c);
            for (Character neighbour : adjList.getOrDefault(c, new ArrayList<>())) {
                indegree.put(neighbour, indegree.get(neighbour) - 1);
                if (indegree.get(neighbour) == 0) queue.add(neighbour);
            }
        }

        return sb.length() < indegree.size() ? "" : sb.toString();
    }


    Map<Character, List<Character>> graph = new HashMap<>();
    Map<Character, Integer> indegree = new HashMap<>();
    boolean valid = true;

    public void build(String[] words) {
        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegree.put(c, 0);
                graph.put(c, new ArrayList<>());
            }
        }

        // Step 1: Find all edges.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            // Check that word2 is not a prefix of word1.
            if (word1.length() > word2.length() && word1.startsWith(word2)) valid = false;

            // Find the first non match and insert the corresponding relation.
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    graph.get(word1.charAt(j)).add(word2.charAt(j));
                    indegree.put(word2.charAt(j), indegree.get(word2.charAt(j)) + 1);
                    break;
                }
            }
        }
    }

    // BFS
    public String alienOrder(String[] words) {
        build(words);

        if (!valid) return "";

        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (Character c : indegree.keySet())
            if (indegree.get(c) == 0) queue.offer(c);

        while (!queue.isEmpty()) {
            Character c = queue.poll();
            sb.append(c);
            for (Character neighbour : adjList.getOrDefault(c, new ArrayList<>())) {
                indegree.put(neighbour, indegree.get(neighbour) - 1);
                if (indegree.get(neighbour) == 0) queue.add(neighbour);
            }
        }

        return sb.length() < indegree.size() ? "" : sb.toString();
    }

    // DFS
    StringBuilder sb = new StringBuilder();
    Map<Character, Integer> visited = new HashMap<>();
    public String alienOrder(String[] words) {
        build(words);
        for (char c : graph.keySet()) visited.put(c, 0);
        for (char c : graph.keySet())
            if (visited.get(c) == 0) dfs(c);
        if (!valid) return "";
        return sb.length() < graph.size() ? "" : sb.reverse().toString();
    }

    private void dfs(char c) {
        visited.put(c, 1);
        for (char neigh : graph.getOrDefault(c, new ArrayList<>())) {
            if (visited.get(nei) == 0) dfs(nei);
            if (visited.get(nei) == 1) valid = false;
        }

        sb.append(c);
        visited.put(c, 2);
    }
}