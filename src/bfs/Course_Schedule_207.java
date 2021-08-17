package bfs;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
/*
207. Course Schedule

There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0. So it is possible.

Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.


Constraints:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
1 <= numCourses <= 10^5
*/

// [[1,0],[2,0],[3,1],[3,2]]


/*
0, [1, 2]
1, [3]
2, [3]


[0, 0] [1, 1] [2, 1] [3, 2]
*/


class Course_Schedule_207 {
    public static void main(String[] args) {
        System.out.println(new Course_Schedule_207().canFinish(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> indegree = new HashMap<>();
        int[] prerCount = new int[numCourses];
        for (int[] prer : prerequisites) {
            List<Integer> items = indegree.containsKey(prer[1]) ?  indegree.get(prer[1]) : new ArrayList<>();
            items.add(prer[0]);
            indegree.put(prer[1], items);
            prerCount[prer[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; ++i)
            if (prerCount[i] == 0)
                queue.offer(i);

        int count = 0;
        while(queue.size() > 0) {
            int course = queue.poll();
            count++;

            for (int serve : indegree.getOrDefault(course, new ArrayList<>()))
                if (--prerCount[serve] == 0) queue.offer(serve);
        }

        return count == numCourses;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        List<Integer>[] graph = new List[numCourses];

        for(int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] each : prerequisites) {
            int from = each[0];
            int to = each[1];
            indegree[to]++;
            graph[from].add(to);
        }

        // BFS
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int size = q.size();
            for(int j = 0; j < size; j++) {
                int cur = q.poll();
                for(int next : graph[cur]) {
                    indegree[next]--;
                    if(indegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }
        }
        for(int ind : indegree) {
            if(ind > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] map  = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        int count = 0;

        for (int i = 0; i < numCourses; i++) {
            map[i] = new ArrayList<Integer>();
        }

        for (int[] prerequisite : prerequisites) {
            map[prerequisite[1]].add(prerequisite[0]);
            indegree[prerequisite[0]]++;
        }

        Queue<Integer> myQueue = new LinkedList<Integer>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                myQueue.offer(i);
            }
        }


        while (!myQueue.isEmpty()) {
            int index = myQueue.poll();
            count++;

            ArrayList<Integer> k = map[index];

            for (int j : k) {
                if (--indegree[j] == 0) {
                    myQueue.offer(j);
                }
            }
        }

        return count == numCourses;
    }

    /*
    public boolean canFinishII(int numCourses, int[][] prerequisites) {
    	int[][] matrix = new int[numCourses][numCourses]; // i -> j
    	int[] indegree = new int[numCourses];

    	for (int i = 0; i < prerequisites.length; i++) {
    		int ready = prerequisites[i][0];
    		int pre = prerequisites[i][1];
    		if (matrix[pre][ready] == 0)
    			indegree[ready]++; //duplicate case
    		matrix[pre][ready] = 1;
    	}

    	int count = 0;
    	Queue<Integer> queue = new LinkedList();
    	for (int i = 0; i < indegree.length; i++) {
    		if (indegree[i] == 0) queue.offer(i);
    	}
    	while (!queue.isEmpty()) {
    		int course = queue.poll();
    		count++;
    		for (int i = 0; i < numCourses; i++) {
    			if (matrix[course][i] != 0) {
    				if (--indegree[i] == 0)
    					queue.offer(i);
    			}
    		}
    	}
    	return count == numCourses;
    }


    public boolean canFinishIII(int numCourses, int[][] prerequisites) {
    	ArrayList[] graph = new ArrayList[numCourses];
    	for (int i = 0; i < numCourses; i++)
    		graph[i] = new ArrayList();

    	boolean[] visited = new boolean[numCourses];
    	for (int i = 0; i < prerequisites.length; i++) {
    		graph[prerequisites[i][1]].add(prerequisites[i][0]);
    	}

    	for (int i = 0; i < numCourses; i++) {
    		if (!dfs(graph, visited, i))
    			return false;
    	}
    	return true;
    }

    private boolean dfs(ArrayList[] graph, boolean[] visited, int course) {
    	if (visited[course])
    		return false;
    	else
    		visited[course] = true;;

    	for (int i = 0; i < graph[course].size(); i++) {
    		if (!dfs(graph, visited, (int)graph[course].get(i)))
    			return false;
    	}
    	visited[course] = false;
    	return true;
    }

    public boolean canFinishIV(int numCourses, int[][] prerequisites) {
    	ArrayList[] graph = new ArrayList[numCourses];
    	int[] degree = new int[numCourses];
    	Queue queue = new LinkedList();
    	int count = 0;

    	for (int i = 0; i < numCourses; i++)
    		graph[i] = new ArrayList();

    	for (int i = 0; i < prerequisites.length; i++) {
    		degree[prerequisites[i][1]]++;
    		graph[prerequisites[i][0]].add(prerequisites[i][1]);
    	}
    	for (int i = 0; i < degree.length; i++) {
    		if (degree[i] == 0) {
    			queue.add(i);
    			count++;
    		}
    	}

    	while (queue.size() != 0) {
    		int course = (int)queue.poll();
    		for (int i = 0; i < graph[course].size(); i++) {
    			int pointer = (int)graph[course].get(i);
    			degree[pointer]--;
    			if (degree[pointer] == 0) {
    				queue.add(pointer);
    				count++;
    			}
    		}
    	}
    	if (count == numCourses)
    		return true;
    	else
    		return false;
    }


    public boolean canFinishV(int n, int[][] prerequisites) {
    	ArrayList<Integer>[] G = new ArrayList[n];
    	int[] degree = new int[n];
    	ArrayList<Integer> bfs = new ArrayList();
    	for (int i = 0; i < n; ++i) G[i] = new ArrayList<Integer>();
    	for (int[] e : prerequisites) {
    		G[e[1]].add(e[0]);
    		degree[e[0]]++;
    	}
    	for (int i = 0; i < n; ++i) if (degree[i] == 0) bfs.add(i);
    	for (int i = 0; i < bfs.size(); ++i)
    		for (int j : G[bfs.get(i)])
    			if (--degree[j] == 0) bfs.add(j);
    	return bfs.size() == n;
    }

    public boolean canFinishVII(int numCourses, int[][] prerequisites) {
    	List[] graphs = new ArrayList[numCourses];
    	if (numCourses == 0 || prerequisites == null || prerequisites.length == 0)return true;
    	boolean[]  visited = new boolean[numCourses];
    	boolean[]  recStk = new boolean[numCourses];
    	for (int i = 0; i < numCourses; i++) {
    		graphs[i] = new ArrayList<>();
    	}

    	for (int i = 0; i < prerequisites.length; i++) {
    		graphs[prerequisites[i][1]].add(prerequisites[i][0]);
    	}

    	for (int i = 0; i < graphs.length; i++) {
    		// if(recStk[i])continue;
    		if (!dfsI(i, visited, recStk, graphs)) {
    			return false;
    		}
    	}

    	return true;
    }

    private static boolean dfsI(int s, boolean[] visited, boolean[] recStk, List[] graph) {
    	if (visited[s]) return false;
    	if (recStk[s]) return true;
    	visited[s] = true;
    	recStk[s] = true;

    	for (int i = 0; i < graph[s].size(); i++) {
    		if (!dfsI((int)graph[s].get(i),  visited, recStk, graph))return false;
    	}
    	visited[s] = false;
    	return true;
    }
    */
}