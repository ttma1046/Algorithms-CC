package array;
/*
Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.

Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a huge number, return this modulo 10^9 + 7.

 

Example 1:



Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
Output: 4 
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
Example 2:



Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
Output: 6
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.
Example 3:

Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
Output: 9
 

Constraints:

2 <= h, w <= 10^9
1 <= horizontalCuts.length < min(h, 10^5)
1 <= verticalCuts.length < min(w, 10^5)
1 <= horizontalCuts[i] < h
1 <= verticalCuts[i] < w
It is guaranteed that all elements in horizontalCuts are distinct.
It is guaranteed that all elements in verticalCuts are distinct.
*/
class Maximum_Area_of_a_Piece_of_Cake_After_Horizontal_and_Vertical_Cuts_1456 {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int MOD = 1000000007;
        return (int)((long)getMax(h, horizontalCuts) * getMax(w, verticalCuts) % MOD);
    }
    
    private int getMax(int length, int[] cuts) {
        Arrays.sort(cuts);
        int maxCut = 0;
        for (int i = 0; i <= cuts.length; i++) {
            if (i == 0) {
                maxCut = cuts[0];
                continue;
            } 
            
            if (i == cuts.length) {
                if (length - cuts[i - 1] > maxCut) maxCut = length - cuts[i - 1];
                continue;
            }
            
            if (cuts[i] - cuts[i - 1] > maxCut) maxCut = cuts[i] - cuts[i - 1];
        }
        
        return maxCut;
    }

    public static void main(String[] args) {
    	Maximum_Area_of_a_Piece_of_Cake_After_Horizontal_and_Vertical_Cuts_1456 myObj = new Maximum_Area_of_a_Piece_of_Cake_After_Horizontal_and_Vertical_Cuts_1456();

    	System.out.println(myObj.maxArea(5, 4, new int[] { 1,2,4 }, new int[] { 1,3 }));

    	System.out.println(myObj.maxArea(5, 4, new int[] { 1,2,4 }, new int[] { 1,3 }));
    }
}