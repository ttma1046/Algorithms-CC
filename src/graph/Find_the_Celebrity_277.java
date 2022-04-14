
package graph;
/*
Suppose you are at a party with n people labeled from 0 to n - 1 and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know the celebrity, but the celebrity does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is ask questions like: "Hi, A. Do you know B?" to get information about whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) that tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if they are at the party.

Return the celebrity's label if there is a celebrity at the party. If there is no celebrity, return -1.



Example 1:


Input: graph = [[1,1,0],[0,1,0],[1,1,1]]
Output: 1
Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
Example 2:


Input: graph = [[1,0,1],[1,1,0],[0,1,1]]
Output: -1
Explanation: There is no celebrity.


Constraints:

n == graph.length
n == graph[i].length
2 <= n <= 100
graph[i][j] is 0 or 1.
graph[i][i] == 1


Follow up: If the maximum number of allowed calls to the API knows is 3 * n, could you find a solution without exceeding the maximum number of calls?
*/

/*
public class Solution extends Relation {
    public int findCelebrity(int n) {
        for (int i = 0; i < n; i++)
            if (isCelebrity(i, n))
                return i;

        return -1;
    }

    private boolean isCelebrity(int i, int n) {
        for (int j = 0; j < n; j++) {
            if (i == j)
                continue; // Don't ask if they know themselves.

            if (knows(i, j) || !knows(j, i))
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Find_the_Celebrity_277 obj = new Find_the_Celebrity_277();
        int res = obj.findCelebrity(2);

        System.out.println(res);
    }


    // Time Complexity : O(n^2).

    // For each of the n people, we need to check whether or not they are a celebrity.

    // Checking whether or not somebody is a celebrity requires making 2 API calls for each of the n - 1 other people,

    // for a total of 2 * (n − 1) = 2 * n − 2 calls.

    // In big-oh notation, we drop the constants, leaving O(n).

    // So each of the nn celebrity checks will cost O(n), giving a total of O(n^2).

    // Space Complexity : O(1).

    // Our code only uses constant extra space. The results of the API calls are not saved.
}
*/

public class Find_the_Celebrity_277 {
    public int findCelebrity(int n) {
        boolean[][] res = buildGraph(n);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (j == n - 1 && i == j) return i;

                if (i != j)
                    if (res[i][j])
                        break;
                    else if (j == n - 1 && !res[i][j])
                        return i;
            }
        }

        return -1;
    }

    private boolean[][] buildGraph(int n) {
        boolean[][] graph = new boolean[n][n];

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j) {
                if (i != j)
                    graph[i][j] = knows(i, j);
            }

        return graph;
    }

    private boolean knows(int i, int j) {
        int[][] res = new int[][] {{1, 0, 1}, {0, 1, 1}, {0, 0, 1}};

        return res[i][j] == 1;
    }

    public static void main(String[] args) {
        Find_the_Celebrity_277 obj = new Find_the_Celebrity_277();
        int res = obj.findCelebrity(3);

        System.out.println(res);
    }

    public int findCelebrity(int n) {
        int candidate = 0;

        for (int i = 0; i < n; i++)
            if (knows(candidate, i))
                candidate = i;

        if (isCelebrity(candidate, n))
            return candidate;

        return -1;
    }

    private boolean isCelebrity(int i, int n) {
        for (int j = 0; j < n; ++j) {
            if (i == j) continue;

            if (knows(i, j) || !knows(j, i))
                return false;
        }

        return true;
    }

    /*
    Time Complexity : O(n).

    Our code is split into 2 parts.

    The first part finds a celebrity candidate.

    This requires doing n − 1 calls to knows(...) API, and so is O(n).

    The second part is the same as before—checking whether or not a given person is a celebrity. We determined that this is O(n).

    Therefore, we have a total time complexity of O(n + n) = O(n).

    Space Complexity : O(1).

    Same as above. We are only using constant extra space.
    */

    boolean[] cached;

    public int findCelebrity(int n) {
        cached = new boolean[n];

        int candidate = 0;

        for (int i = 0; i < n; i++)
            if (knows(candidate, i)) {
                candidate = i;
                cached = new boolean[n];
            } else {
                cached[i] = true;
            }

        if (isCelebrity(candidate, n))
            return candidate;

        return -1;
    }

    private boolean isCelebrity(int i, int n) {
        for (int j = 0; j < n; ++j) {
            if (i == j) continue;

            if (cached[j])
                continue;

            if (knows(i, j) || !knows(j, i))
                return false;
        }

        return true;
    }

    private int numberOfPeople;
    private Map<Pair<Integer, Integer>, Boolean> cache = new HashMap<>(); 
    
    @Override
    public boolean knows(int a, int b) {
        if (!cache.containsKey(new Pair(a, b))) {
            cache.put(new Pair(a, b), super.knows(a, b));
        }
        return cache.get(new Pair(a, b));
    }
    
    public int findCelebrity(int n) {
        numberOfPeople = n;
        int celebrityCandidate = 0;
        for (int i = 0; i < n; i++) {
            if (knows(celebrityCandidate, i)) {
                celebrityCandidate = i;
            }
        }
        if (isCelebrity(celebrityCandidate)) {
            return celebrityCandidate;
        }
        return -1;
    }
    
    private boolean isCelebrity(int i) {
        for (int j = 0; j < numberOfPeople; j++) {
            if (i == j) continue; // Don't ask if they know themselves.
            if (knows(i, j) || !knows(j, i)) {
                return false;
            }
        }
        return true;
    }
}