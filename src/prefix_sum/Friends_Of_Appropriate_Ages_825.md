#### Approach #1: Counting [Accepted]

**Intuition**
Instead of processing all `20000` people, we can process pairs of `(age, count)` representing how many people are that age.  Since there are only 120 possible ages, this is a much faster loop.
**Algorithm**
For each pair `(ageA, countA)`, `(ageB, countB)`, if the conditions are satisfied with respect to age, then `countA * countB` pairs of people made friend requests.
If `ageA == ageB`, then we overcounted: we should have `countA * (countA - 1)` pairs of people making friend requests instead, as you cannot friend request yourself.

```java
class Solution {
    public int numFriendRequests(int[] ages) {
        int[] count = new int[121];
        for (int age: ages) count[age]++;

        int ans = 0;
        for (int ageA = 0; ageA < count.length; ageA++) {
            int countA = count[ageA];
            for (int ageB = 0; ageB <= 120; ageB++) {
                int countB = count[ageB];
                if (ageA * 0.5 + 7 >= ageB) continue;
                if (ageA < ageB) continue;
                if (ageA < 100 && 100 < ageB) continue;
                ans += countA * countB;
                if (ageA == ageB) ans -= countA;
            }
        }

        return ans;
    }
}
```

**Complexity Analysis**
* Time Complexity: `O(A^2 + N)`, where `N` is the number of people, and `A` is the number of ages.
* Space Complexity: `O(A)`, the space used to store `count`.
