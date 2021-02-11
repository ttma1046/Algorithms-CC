# 435. 无重叠区间

https://leetcode-cn.com/problems/non-overlapping-intervals/

- [435. 无重叠区间](#435-无重叠区间)
  - [题目描述](#题目描述)
  - [方法 1: 贪心](#方法-1-贪心)
    - [思路](#思路)
    - [复杂度分析](#复杂度分析)
    - [代码](#代码)
    
    
## 方法 1: 贪心

### 思路

因为需要**移除尽量少**的区间，换句话说，就是要**保留尽量多**的区间。在选择要保留的区间的时候，需要考虑的是区间的**结束时间**，因为结束时间越早，留给其他区间的空间就越多，就越有可能保留更多的区间。所以我们采取的贪心策略是：**优先保留结束时间小并且不与其他区间相交的区间**。

具体实现就是要按区间结束时间升序排序。

<!-- > 对于合并问题，可以考虑按区间开头排序。
> 对于覆盖问题，可以考虑按区间结尾排序。 -->

![](https://cdn.jsdelivr.net/gh/suukii/91-days-algorithm/assets/435_0.png)

### 复杂度分析

-   时间复杂度：$O(NlogN)$, N 为数组长度，排序的时间。
-   空间复杂度：$O(1)$。

### 代码

JavaScript Code

```js
/**
 * @param {number[][]} intervals
 * @return {number}
 */
var eraseOverlapIntervals = function (intervals) {
    // 按区间结尾升序排序
    intervals.sort((a, b) => a[1] - b[1]);

    let ans = 0;
    let cur = 0,
        next = 1;

    while (next < intervals.length) {
        // 如果两个区间相交，则移除 next 区间 (next++，cur不动)
        if (intervals[cur][1] > intervals[next][0]) {
            ans++;
            next++;
        }
        // 如果两个区间不相交，则继续比较后面两个区间 (cur=next, next++)
        else {
            cur = next;
            next++;
        }
    }

    return ans;
};
```
