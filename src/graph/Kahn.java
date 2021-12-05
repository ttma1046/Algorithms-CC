package graph;
class Kahn {
	public boolean canFinish(int N, int[][] edges) {
		Map<Integer, List<Integer>> graph = new HashMap<>();

		int[] indegree = new int[N];    // 1. 建图

		for (int[] edge: edges) {       // 2. 建indegree
			int end = edge[0], start = edge[1];

			graph.computeIfAbsent(start, x -> new ArrayList<>()).add(end);
			indegree[end]++;           // 【0, 1] 图的方向是从1指向0， 0的入度有所增加， end <- start
		}

		Queue<Integer> q = new LinkedList<>();  // 3. 找到有向图的入口，入度为0的点

		for (int i = 0; i < N; i++)
			if (indegree[i] == 0) q.add(i);

		int count = 0;                          // 4. BFS 拓扑排序

		while(q.size() > 0) {
			int cur = q.poll();
			count++;
			for (int nei: graph.getOrDefault(cur, new ArrayList<>()))
				if (--indegree[nei] == 0) q.offer(nei);
		}

		return count == N;
	}
}