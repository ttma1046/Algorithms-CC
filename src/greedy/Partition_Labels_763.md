Approach 1: Greedy
Intuition

Let's try to repeatedly choose the smallest left-justified partition. Consider the first label, say it's 'a'. The first partition must include it, and also the last occurrence of 'a'. However, between those two occurrences of 'a', there could be other labels that make the minimum size of this partition bigger. For example, in "abccaddbeffe", the minimum first partition is "abccaddb". This gives us the idea for the algorithm: For each letter encountered, process the last occurrence of that letter, extending the current partition [anchor, j] appropriately.

Algorithm

We need an array last[char] -> index of S where char occurs last. Then, let anchor and j be the start and end of the current partition. If we are at a label that occurs last at some index after j, we'll extend the partition j = last[c]. If we are at the end of the partition (i == j) then we'll append a partition size to our answer, and set the start of our new partition to i+1.


```java
class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;
        
        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }
}
```


Complexity Analysis

Time Complexity: O(N), where N is the length of SS.

Space Complexity: O(1) to keep data structure last of not more than 26 characters.

Figure out the rightmost index first and use it to denote the start of the next section.

Reset the left pointer at the start of each new section.

Store the difference of right and left pointers + 1 as in the result for each section.
    
![Image](partition_labels_763.png)

```java
public List<Integer> partitionLabels(String S) {
    HashMap<Character, Integer> map = new HashMap();

    for (int i = 0; i < S.length(); i ++) {
        map.put(S.charAt(i), i);
    }

    List<Integer> result = new LinkedList();
    int right = 0;
    int size = 0;

    for (int left = 0; left < S.length(); left++) {
        size++;
        right = Math.max(right, map.get(S.charAt(left)));

        if (left == right) {
            result.add(size);
            size = 0;
        }
    }

    return result;
}
```

```java
    public List<Integer> partitionLabels(String S) {
        
        int[] wordRange = new int[26];
        char[] chArr = S.toCharArray();
        
        List<Integer> ans = new ArrayList<>();
        if(S == null && S.length() == 0) return ans;
        
        for(int i=0; i < chArr.length; i++){
            wordRange[chArr[i]-'a'] = i;
        }
        
        int i = 0;
        
        while(i < S.length()){
            int t = i;
            
            i = iteratingWord(chArr, i, wordRange);
            ans.add(i-t+1);
            i++;
        }
        
        return ans;
    }
    
    public int iteratingWord(char[] chArr, int index, int[] wordRange){
        
        int start = index;
        int end = wordRange[chArr[index]-'a'];
        
        while(start < end){
            
            int updatedEnd = end;
            
            for(int j=start; j < end; j++){
                updatedEnd = Math.max(wordRange[chArr[j] - 'a'],updatedEnd);
            }
            
            start = end+1;
            
            if(updatedEnd != end){
                end = updatedEnd;
            }
        }
        
        return end;
    }
```

