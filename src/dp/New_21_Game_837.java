package dp;
/*
Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points and draws numbers while she has less than k points. During each draw, she gains an integer number of points randomly from the range [1, maxPts], where maxPts is an integer. Each draw is independent and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets k or more points.

Return the probability that Alice has n or fewer points.

Answers within 10-5 of the actual answer are considered accepted.

Example 1:

Input: n = 10, k = 1, maxPts = 10
Output: 1.00000
Explanation: Alice gets a single card, then stops.

Example 2:

Input: n = 6, k = 1, maxPts = 10
Output: 0.60000
Explanation: Alice gets a single card, then stops.
In 6 out of 10 possibilities, she is at or below 6 points.

Example 3:

Input: n = 21, k = 17, maxPts = 10
Output: 0.73278

Constraints:

0 <= k <= n <= 104
1 <= maxPts <= 104
*/
class New_21_Game_837 {
    public double new21Game(int n, int k, int pointsLimit) {
        // Corner cases.
        if (k == 0) return 1;

        int maxPoint = k + pointsLimit - 1;
        // probability[i] is probability of getting point i.
        double[] probability = new double[maxPoint + 1];

        probability[0] = 1;
        for (int point = 1; point <= maxPoint; point++) {
            for (int card = 1; card <= pointsLimit; card++) {
                if (point - card >= 0 && point - card < k)
                    probability[point] += probability[point - card] * 1 / pointsLimit;
            }
        }

        double targetProbability = 0; // Probability of N or less points.
        for (int z = k; z <= n; z++) targetProbability += probability[z];       

        return targetProbability;
    }

    public static void main(String[] args) {
    	
    }
}