package matrix;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/*
Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example 1:

Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]

Example 2:

Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []

Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] is a lowercase English letter.
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
All the strings of words are unique.
*/
class Word_Search_II_212 {
    public List<String> findWords(char[][] board, String[] words) {
        int n = board.length;
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        List<String> res = new ArrayList<>();
        for (String word : words) {
            visited = new boolean[n][m];
            char[] w = word.toCharArray();
            boolean founded = false;
            for (int i = 0; i < n; ++i) {
                if (founded) break;
                for (int j = 0; j < m; ++j) {
                    if (board[i][j] == w[0] && dfs(board, w, i, j, 0, visited)) {
                        res.add(word);
                        founded = true;
                        break;
                    }
                }
            }
        }

        return res;
    }

    private boolean dfs(char[][] board, char[] w, int i, int j, int k, boolean[][] visited) {
        if (i >= 0 && i < board.length && j >= 0 && j < board[0].length && !visited[i][j] && w[k] == board[i][j]) {
            if (k == w.length - 1) return true;

            visited[i][j] = true;

            if (
                dfs(board, w, i + 1, j, k + 1, visited) ||
                dfs(board, w, i - 1, j, k + 1, visited) ||
                dfs(board, w, i, j + 1, k + 1, visited) ||
                dfs(board, w, i, j - 1, k + 1, visited))
                return true;

            visited[i][j] = false;
        }

        return false;
    }

    public static void main(String[] args) {
        Word_Search_II_212 obj = new Word_Search_II_212();

        char[][] board = new char[][] {
            {'o', 'a', 'a', 'n'},
            {'e', 't', 'a', 'e'},
            {'i', 'h', 'k', 'r'},
            {'i', 'f', 'l', 'v'}
        };
        String[] words = new String[] {"oath", "pea", "eat", "rain"};

        List<String> res = obj.findWordsII(board, words);

        for (String str : res)
            System.out.println(str);

        board = new char[][] {
            {'o', 'a', 'b', 'n'},
            {'o', 't', 'a', 'e'},
            {'a', 'h', 'k', 'r'},
            {'a', 'f', 'l', 'v'}
        };
        words = new String[] {"oa", "oaa"};
        res = obj.findWords(board, words);

        for (String str : res)
            System.out.println(str);
    }

    char[][] board = null;
    ArrayList<String> res = new ArrayList<String>();

    public List<String> findWordsII(char[][] board, String[] words) {
        // Step 1). Construct the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;

            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word;  // store words in Trie
        }

        this.board = board;
        // Step 2). Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }

        return this.res;
    }

    private void backtracking(int row, int col, TrieNode parent) {
        Character letter = this.board[row][col];
        TrieNode currNode = parent.children.get(letter);

        // check if there is any match
        if (currNode.word != null) {
            this.res.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        this.board[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= this.board.length || newCol < 0
                    || newCol >= this.board[0].length) {
                continue;
            }
            if (currNode.children.containsKey(this.board[newRow][newCol])) {
                backtracking(newRow, newCol, currNode);
            }
        }

        // End of EXPLORATION, restore the original letter in the board.
        this.board[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty())
            parent.children.remove(letter);        
    }
}

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    String word = null;
    public TrieNode() {}
}

