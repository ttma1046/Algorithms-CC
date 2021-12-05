package graph;
/*
There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.

 

Example 1:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
Output: 200
Explanation: The graph is shown.
The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
Output: 500
Explanation: The graph is shown.
The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 

Constraints:

1 <= n <= 100
0 <= flights.length <= (n * (n - 1) / 2)
flights[i].length == 3
0 <= fromi, toi < n
fromi != toi
1 <= pricei <= 104
There will not be any multiple flights between two cities.
0 <= src, dst, k < n
src != dst
*/
class Cheapest_Flights_Within_K_Stops_787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();

        for (int[] f: flights) prices.computeIfAbsent(f[0], value -> new HashMap<>()).put(f[1], f[2]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

    	pq.add(new int[] {0, src , k + 1});

    	while(pq.size() > 0) {
    		int[] cur = pq.poll();
    		int price = cur[0], city = cur[1], stops = cur[2];

    		if (city == dst) return price;

    		if (stops > 0) {
    			Map<Integer, Integer> neighborsPrice = prices.getOrDefault(city, new HashMap<>());

    			for (int nei: neighborsPrice.keySet()) 
    				pq.add(new int[] { price + neighborsPrice.get(nei), nei, stops - 1});
    		}
    	}

    	return -1;
    }

    final int MAX = 100000000;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    	int[] cost = new int[n];

    	Arrays.fill(cost, MAX);

    	cost[src] = 0;

    	int res = MAX;

    	for (int i = 0; i <= K; i++) {
    		int[] curr = Arrays.copyOf(cost, n);

    		for (int[] flight: flights)
    			cur[flight[1]] = Math.min(cur[flight[1]], cost[flight[0]] + flight[2]);

    		res = Math.min(res, cur[dst]);

    		cost = cur;
    	}

    	return res == MAX ? -1 : res;
    }
}