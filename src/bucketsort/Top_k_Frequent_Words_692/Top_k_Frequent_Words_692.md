Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest.
If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:

```
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
```

Example 2:

```
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
```

Note:

- You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
- Input words contain only lowercase letters.

Follow up:

- Try to solve it in O(n log k) time and O(n) extra space.



### Approach #1: Sorting [Accepted]

**Intuition and Algorithm**

Count the frequency of each word, and sort the words with a custom ordering relation that uses these frequencies. Then take the best `k` of them.

**Java**

```java
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        List<String> candidates = new ArrayList(count.keySet());
        Collections.sort(candidates, (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                         w1.compareTo(w2) : count.get(w2) - count.get(w1));

        return candidates.subList(0, k);
    }
}
```

**Complexity Analysis**

- Time Complexity: `O(N \\log{N})`, where `N` is the length of `words`. We count the frequency of each word in `O(N)` time, then we sort the given words in `O(N \\log{N})` time.

- Space Complexity: `O(N)`, the space used to store our `candidates`.

#### Approach #2: Heap [Accepted]

**Intuition and Algorithm**

Count the frequency of each word, then add it to heap that stores the best `k` candidates. Here, \"best\" is defined with our custom ordering relation, which puts the worst candidates at the top of the heap. At the end, we pop off the heap up to `k` times and reverse the result so that the best candidates are first.

In Python, we instead use `heapq.heapify`, which can turn a list into a heap in linear time, simplifying our work.

**Java**

```java
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<String>(
            (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
            w2.compareTo(w1) : count.get(w1) - count.get(w2) );

        for (String word : count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }

        List<String> ans = new ArrayList();
        while (!heap.isEmpty()) ans.add(heap.poll());
        Collections.reverse(ans);
        return ans;
    }
}
```

**Complexity Analysis**

- Time Complexity: `O(N \\log{k})`, where `N` is the length of `words`. We count the frequency of each word in `O(N)` time, then we add `N` words to the heap, each in `O(\\log {k})` time. Finally, we pop from the heap up to `k` times. As `k \\leq N`, this is `O(N \\log{k})` in total.

In Python, we improve this to `O(N + k \\log {N})`: our `heapq.heapify` operation and counting operations are `O(N)`, and each of `k` `heapq.heappop` operations are `O(\\log {N})`.

- Space Complexity: `O(N)`, the space used to store our `count`.
