    Approach #2: Greedy with Heap [Accepted]

    Intuition

    One consequence of the reasoning in Approach #1,

    is that a greedy approach that tries to write the most common letter (that isn't the same as the previous letter written) will work.

    The reason is that the task is only impossible if the frequency of a letter exceeds (N+1) / 2.

    Writing the most common letter followed by the second most common letter keeps this invariant.

    A heap is a natural structure to repeatedly return the current top 2 letters with the largest remaining counts.

    Approach

    We store a heap of (count, letter). [In Python, our implementation stores negative counts.]

    We pop the top two elements from the heap (representing different letters with positive remaining count),

    and then write the most frequent one that isn't the same as the most recent one written.

    After, we push the correct counts back onto the heap.

    Actually, we don't even need to keep track of the most recent one written.

    If it is possible to organize the string, the letter written second can never be written first in the very next writing.

    At the end, we might have one element still on the heap, which must have a count of one.

    If we do, we'll add that to the answer too.

    Proof of Invariant

    The invariant mentioned in the [Intuition] section seems true when playing with it, but here is a proof.
    Let Ci be the count of each letter yet to be written, and N be the number of letters left to write.
    We want to show this procedure maintains the invariant 2 ∗ max(Ci) <= N + 1
    ​                                                            i
    Say C'_i are the counts after one writing step.

    This completes the proof of this invariant.

    * if max(Ci) > 3rdmax(Ci), then (C'_i) <= max(Ci) - 1, so 2max(C'i) <= 2max(Ci) - 2 <= N - 1 as desired.
    * if M = max(Ci) = 3rdmax(Ci), then 3M <= N, Also ,because M >= 1, N + 3. Then, 2M <= 2N/3 <= N -1 as desired.

    Complexity Analysis

    Time Complexity: O(NlogA)), where N is the length of S, and A is the size of the alphabet. If A is fixed, this complexity is O(N).

    Space Complexity: O(A). If A is fixed, this complexity is O(1).