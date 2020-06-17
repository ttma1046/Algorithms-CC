Q & A
Q: Say if there are n passengers and the first passenger took the 3rd seat. Now, like you explained, there are n - 1 passengers and n - 1 seats left. But when the 2nd passenger comes in, he doesn't have 2 options to make it possible for the nth passenger to take the nth seat. Instead, he only has one option, which is to take the 2nd seat because it is not occupied by the first passenger. I don't see how that is the case of a subproblem of (n - 1). Could you shed some light please, thanks!
A: For any case, we can get rid of those sitting on own seats (except the first passenger) and get a problem of n' (= n - k, where k is the number of passengers sitting on own seats), then re-number (without changing the relative order) them as passenger 1, 2, ..., n', hence the result is in same form, the only difference is to change n to n'.
Except n' = 1, results for n' of other values are independent on n'. In short, changing from n to n' will not influence the result.

Part 1: [Java] 2 liners w/ explanation and analysis.
For the 1st passenger, there are 2 cases that the nth passenger could take the right seat:
1st passenger

Take his own seat, the probability is 1 / n;
Take a seat neither his own nor the one of the nth passenger, and the corresponding probability is (n - 2) / n; In addition, other passengers (except the nth one) should not occupy the nth seat;
Now there are n - 1 passengers and n - 1 seats remaining, and the 2nd passenger, like the 1st one, have 2 options to make it possible the nth passenger take the right seat:
a) take the 1st passenger's seat, the probability is 1 / (n - 1);
b) Take a seat that is neither the 1st passenger's nor the nth passenger's, and the corresponding probability is ((n - 1) - 2) /( n - 1);
Obviouly, we recurse to subproblem of (n - 1) .
Combined the above 2 cases, we have the following code:

    public double nthPersonGetsNthSeat(int n) {
        if (n == 1) return 1.0d;
        return 1d / n + (n - 2d) / n * nthPersonGetsNthSeat(n - 1);
    }
Analysis
Time: O(n), space: O(1).

Based on the code in part 1, we have the following formula:

f(n) = 1 / n + (n - 2) / n * f(n - 1)
Part2: Proof when n > 1, the f(n) is 1/2
n = 2, we have f(2) = 1/2; the assumption holds;
Suppose n = k we have f(k) = 1/2, when n = k + 1,
f(k + 1) = 1 / (k + 1) + (k + 1 - 2) / (k + 1) * f(k)
         = 2 / (2 * (k + 1)) + (k - 1) / (k + 1) * 1/2
         = 1 / 2
That is, f(k + 1) = 1 / 2 also holds.

From above 1 and 2, we complete the proof.

With the conclusion, it is easy to have 1 liners for Java and Python 3:

    public double nthPersonGetsNthSeat(int n) {
        return n == 1 ? 1.0d : .5d;
    }
    def nthPersonGetsNthSeat(self, n: int) -> float:
        return 1.0 if n == 1 else 0.5