* List of all tasks that you need to complete today.
* time that is required to complete each task.
* Priority (or weight) to each work.

* Integer N for the number of jobs you want to complete.
* List T: Time that is required to complete a task.
* List P: Priority ( or weight )

Totaltime(j) = T[1] + T[2] + ... + T[j] where 1 <= j <= N

```java
public int taskup(int[] priorities, int[] times, int totalJobsDone) {
    int[][] s = new int[times.length][2];
    for (int i = 0; i < times.length; i++) {
        s[i] = new int [] { priorities[i] / times[i], i };
    }

    Arrays.sort(s);

    int totalCompleteTime = 0;
    int weigthedSum = 0;
    for (int i = 0; i < totalJobsDone; i++) {
        totalCompleteTime += times[s[i][1]];
        weigthedSum += priorities[s[i][1]] * totalCompleteTime; 
    }
    return weigthedSum;
}
```