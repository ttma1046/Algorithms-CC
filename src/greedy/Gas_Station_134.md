
Approach 1: One pass.
Intuition

The first idea is to check every single station :

* Choose the station as starting point.

* Perform the road trip and check how much gas we have in tank at each station.

That means O(N^2) time complexity, and for sure one could do better.

Let's notice two things.

__It's impossible to perform the road trip if sum(gas) < sum(cost). In this situation the answer is -1.__

One could compute total amount of gas in the tank `total_tank = sum(gas) - sum(cost)` during the round trip, and then return -1 if total_tank < 0.

__It's impossible to start at a station i if gas[i] - cost[i] < 0, because then there is not enough gas in the tank to travel to i + 1 station.__

The second fact could be generalized. Let's introduce curr_tank variable to track the current amount of gas in the tank. If at some station curr_tank is less than 0, that means that one couldn't reach this station.

Next step is to mark this station as a new starting point, and reset curr_tank to zero since one starts with no gas in the tank.

Algorithm

Now the algorithm is straightforward :

1. Initiate total_tank and curr_tank as zero, and choose station 0 as a starting station.

2. Iterate over all stations :

    * Update total_tank and curr_tank at each step, by adding gas[i] and subtracting cost[i].
    * If curr_tank < 0 at i + 1 station, make i + 1 station a new starting point and reset curr_tank = 0 to start with an empty tank.

3. Return -1 if total_tank < 0 and starting station otherwise.

Why this works

Let's imagine the situation when total_tank >= 0 and the above algorithm returns N_s as a starting station.

Algorithm directly ensures that it's possible to go from N_s to the station 0. 
But what about the last part of the round trip from the station 0 to the station N_s?

__How one could ensure that it's possible to loop around to N_s ?__

Let's use here the proof by contradiction and assume that there is a station 0 < k < N_s such that one couldn't reach this station starting from N_s.

The condition total_tank >= 0 could be written as
Let's split the sum on the right side by the starting station N_s and unreachable station k :

The second term is negative by the algorithm definition - otherwise the starting station would be before N_s. It could be equal to zero only in the case of k = N_s - 1.

Equations (2) and (3) together results in

At the same time the station kk is supposed to be unreachable from N_s that means
Eqs. (4) and (5) together result in a contradiction. Therefore, the initial assumption — that there is a station 0 < k < N_s such that one couldn't reach this station starting from N_s — must be false.

Hence, one could do a round trip starting from N_s, that makes N_s to be an answer. The answer is unique according to the problem definition.

Implementation

Current
1 / 13

Complexity Analysis

Time complexity : O(N) since there is only one loop over all stations here.

Space complexity : O(1) since it's a constant space solution.

```java
class Solution {
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int n = gas.length;

    int total_tank = 0;
    int curr_tank = 0;
    int starting_station = 0;
    for (int i = 0; i < n; ++i) {
      total_tank += gas[i] - cost[i];
      curr_tank += gas[i] - cost[i];
      // If one couldn't get here,
      if (curr_tank < 0) {
        // Pick up the next station as the starting one.
        starting_station = i + 1;
        // Start with an empty tank.
        curr_tank = 0;
      }
    }
    return total_tank >= 0 ? starting_station : -1;
  }
}
```