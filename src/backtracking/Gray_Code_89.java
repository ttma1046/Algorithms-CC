package backtracking;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/*
An n-bit gray code sequence is a sequence of 2n integers where:

Every integer is in the inclusive range [0, 2n - 1],
The first integer is 0,
An integer appears no more than once in the sequence,
The binary representation of every pair of adjacent integers differs by exactly one bit, and
The binary representation of the first and last integers differs by exactly one bit.
Given an integer n, return any valid n-bit gray code sequence.

Example 1:

Input: n = 2
Output: [0,1,3,2]
Explanation:
The binary representation of [0,1,3,2] is [00,01,11,10].
- 00 and 01 differ by one bit
- 01 and 11 differ by one bit
- 11 and 10 differ by one bit
- 10 and 00 differ by one bit
[0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
- 00 and 10 differ by one bit
- 10 and 11 differ by one bit
- 11 and 01 differ by one bit
- 01 and 00 differ by one bit
Example 2:

Input: n = 1
Output: [0,1]


Constraints:
00 01 11 10
1 <= n <= 16
*/
class Gray_Code_89 {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();

        Set<Integer> isPresent = new HashSet<Integer>();

        result.add(0);
        isPresent.add(0);

        backtracking(result, n, isPresent);

        return result;
    }

    public boolean backtracking(List<Integer> result, int n, Set<Integer> isPresent) {
        if (result.size() == (1 << n)) return true;

        int current = result.get(result.size() - 1);

        for (int i = 0; i < n; i++) {
            int next = current ^ (1 << i);

            if (!isPresent.contains(next)) {
                isPresent.add(next);
                result.add(next);

                if (backtracking(result, n, isPresent)) return true;

                isPresent.remove(next);
                result.remove(result.size() - 1);
            }
        }


        return false;
    }

    /*
    1. Initialize a result list to store the solution sequence.

    Add 0 to the list before calling the `grayCodeHelper` method as all gray code sequences start with 0.

    2. Initialize a set visited.

    This keeps track of the numbers present in the current sequence to prevent repetition.

    3. Start with the number 0.

    4. In the grayCodeHelper() function, use a for loop to find each possible number (next) that can be generated by changing one bit of

    the last number in the result list (current).

    We do so by toggling the i^{th} bit at each iteration.

    Since the maximum possible number of bits present in any number of the sequence is n, we need to toggle n bits.

    5. If next is not present in the set of used numbers (isPresent) add it to the result list and the isPresent set.

    6. Continue the search with next.

        1. If grayCodeHelper(next) returns true, it means we have found a valid sequence.
        So no further search is required (early stop). This early termination improves the runtime.

        2. If no valid sequence is found with next, we remove it from the result list and the isPresent set and continue our search.

    7. Upon reaching the base condition, when the length of the current sequence is 2 ^ n, return true.

    8. Exiting the for loop implies no valid Gray code sequence was found with current as the last number. So return false.


    Complexity Analysis

    Here n is the total number of bits in the Gray code.

    Time complexity: O(n * 2^n)

    For n bits 2^n numbers are possible. The maximum depth of the recursive function stack is 2^n
      (The recursion stops when the size of result list is 2^n).

    In our backtracking algorithm,

    at every function call we iterate over a loop of length n

    and try to find out all possible successors of the last added number in the present sequence.

    We continue our search with the first value that succeeds (not present in the isPresent set).

    Since we are using HashSet and unordered_set which have an amortized runtime of O(1) in all operations,

    use of sets doesn't increase the time complexity.


    Another key observation is that in this particular problem the program never backtracks and always finds a path forward.

    We can check this by executing the solution after removing the following lines from our code:

                isPresent.erase(next);
                result.pop_back();

    This means that greedily changing the right-most bit that does not result in a number already in result,

    will always lead to the solution.

    However, we would not know that beforehand while we are coming up with the solution,

    so we have to assume that backtracking may be needed should something not work out. So in theory the runtime is O(2^n)!

    but in practice, it comes out to O(n * 2 ^ n).


    Space complexity: O(2 ^ n)

    We use a set isPresent which will contain at most 2^n  numbers.

    The space occupied by the output result is not considered in the space complexity analysis.

    */
    public List<Integer> grayCodeII(int n) {
        List<Integer> result = new ArrayList<>();
        grayCodeHelper(result, n);
        return result;
    }

    private void grayCodeHelper(List<Integer> result, int n) {
        if (n == 0) {
            result.add(0);
            return;
        }
        // we derive the n bits sequence from the (n - 1) bits sequence.
        grayCodeHelper(result, n - 1);
        int currentSequenceLength = result.size();
        // Set the bit at position n - 1 (0 indexed) and assign it to mask.
        int mask = 1 << (n - 1);
        for (int i = currentSequenceLength - 1; i >= 0; i--) {
            // mask is used to set the (n - 1)th bit from the LSB of all the numbers present in the current sequence.
            System.out.println("mask:" + mask);
            System.out.println(result.get(i) | mask);
            result.add(result.get(i) | mask);
        }
    }

    public List<Integer> grayCodeIII(int n) {
        List<Integer> result = new ArrayList<>();
        // there are 2 ^ n numbers in the Gray code sequence.
        int sequenceLength = 1 << n;
        for (int i = 0; i < sequenceLength; i++) {
            int num = i ^ i >> 1;
            result.add(num);
        }
        return result;
    }

    public List<Integer> grayCodeIV(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);

        for (int i = 1; i <= n; i++) {
            int previousSequenceLength = result.size();
            int mask = 1 << (i - 1);
            for (int j = previousSequenceLength - 1; j >= 0; j--) {
                result.add(mask + result.get(j));
            }
        }
        return result;
    }

    int nextNum = 0;

    public List<Integer> grayCodeV(int n) {
        List<Integer> result = new ArrayList<>();
        grayCodeHelperI(result, n);
        return result;
    }

    private void grayCodeHelperI(List<Integer> result, int n) {
        if (n == 0) {
            result.add(nextNum);
            return;
        }
        grayCodeHelperI(result, n - 1);
        // Flip the bit at (n - 1)th position from right
        nextNum = nextNum ^ (1 << (n - 1));
        grayCodeHelperI(result, n - 1);

    }

    public static void main(String[] args) {
        Gray_Code_89 obj = new Gray_Code_89();
        obj.grayCodeII(3);

        /*
        int i = 0;

        i = 0 ^ (1 << 0);

        System.out.println(i);

        i = 0 ^ (1 << 1);

        System.out.println(i);

        i = 0 ^ (1 << 2);

        System.out.println(i);

        int t = (1 << 3);
        System.out.println(t);
        */
    }
}