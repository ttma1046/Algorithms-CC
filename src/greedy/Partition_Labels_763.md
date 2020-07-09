
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

    for (int left = 0; left < S.length(); left ++) {
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