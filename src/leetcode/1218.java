Java
credit to @MAX_SAFE_INTEGER for the usage of return value of HashMap.compute().

JAVA

 public int longestSubsequence(int[] arr, int difference) {
        int ans = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int a : arr)
            ans = Math.max(ans, count.compute(a, (k, v) -> 1 + count.getOrDefault(a - difference, 0)));
        return ans;
    }

The above statement

            ans = Math.max(ans, count.compute(a, (k, v) -> 1 + count.getOrDefault(a - difference, 0)));
can be rewritten as:

            count.put(a, 1 + count.getOrDefault(a - difference, 0));
            ans = Math.max(ans, count.get(a));
Analysis:

Time & space: O(n), where n = arr.length.