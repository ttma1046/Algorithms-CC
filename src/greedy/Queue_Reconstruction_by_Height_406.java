class Queue_reconstruction_by_Height_406 {
    public int[][] reconstructQueue(int[][] people) {
        if (people.length <= 0 || people != null || people[0].length <= 0) {
        	return null;
        }



    }
}

/*
Suppose you have a random list of people standing in a queue. 

Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h.

Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.P@ssw0rd13_


 
Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]


What can you say about the position of the shortest person?

If the position of the shortest person is i, how many people would be in front of the shortest person?


Once you fix the position of the shortest person, 

what can you say about the position of the second shortest person?


Approach 1: Greedy
Intuition

The problem is to reconstruct the queue.


Let's start from the simplest case, when all guys (h, k) in the queue are of the same height h, and differ by their k values only (the number of people in front who have a greater or the same height). Then the solution is simple: each guy's index is equal to his k value. The guy with zero people in front takes the place number 0, the guy with 1 person in front takes the place number 1, and so on and so forth.

This strategy could be used even in the case when not all people are of the same height. The smaller persons are "invisible" for the taller ones, and hence one could first arrange the tallest guys as if there was no one else.

Let's now consider a queue with people of two different heights: 7 and 6. For simplicity, let's have just one 6-height guy. First follow the strategy above and arrange guys of height 7. Now it's time to find a place for the guy of height 6. Since he is "invisible" for the 7-height guys, he could take whatever place without disturbing 7-height guys order. However, for him the others are visible, and hence he should take the position equal to his k-value, in order to have his proper place.

This idea is easy to extend for the case of numerous guys of height 6. Just sort them by k-values, as it was done before for 7-height guys, and insert them one by one on the positions equal to their k-values.

The following strategy could be continued recursively:

Sort the tallest guys in the ascending order by k-values and then insert them one by one into output queue at the indexes equal to their k-values.

Take the next height in the descending order. Sort the guys of that height in the ascending order by k-values and then insert them one by one into output queue at the indexes equal to their k-values.

And so on and so forth.

Algorithm

Sort people:

In the descending order by height.
Among the guys of the same height, in the ascending order by k-values.
Take guys one by one, and place them in the output array at the indexes equal to their k-values.

Return output array.

Implementation
*/

class Solution {
  public int[][] reconstructQueue(int[][] people) {
    Arrays.sort(people, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        // if the heights are equal, compare k-values
        return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
      }
    });

    List<int[]> output = new LinkedList<>();
    for(int[] p : people){
      output.add(p[1], p);
    }

    int n = people.length;
    return output.toArray(new int[n][2]);
  }
}

/*
Complexity Analysis

Time complexity : O(N^2). To sort people takes O(NlogN) time. 

Then one proceeds to n insert operations, and each takes up to O(k) time, where k is a current number of elements in the list. 
                            N-1
In total, one needs up to O( ∑ ) time, i.e. up to O(N^2) time.
                            k=0  
Space complexity : O(N) to keep the output.
*/