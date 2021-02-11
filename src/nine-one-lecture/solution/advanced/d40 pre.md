# 网易面试题
最直观的思路是 m 次查询，每次都暴力地计算第 i 个人所处的位置。这种算法的时间复杂度为 $O(m * n)$，如果提交会超时，我们考虑优化。

## 思路
一种思路是先对分数排序，然后每次查询的时候可以通过二分查找进行匹配，由于用到了排序，需要花 O(nlogn) 的时间，m 次查询花的时间大致为 O(mlogn)，时间复杂度可以算是 $O(max(m, n) * logn)$，这个时间复杂度通过所有的测试用例。 由于本次是前缀和，代码就不贴了。

接下来我们介绍一种更加巧妙的前缀和方法。由于每个同学的分数都在 0 - 150 的 离散区间，因此我们可以用一个数组 arr，然后让 arr[i] 表示分数不超过 i 的人数。 前缀和的构造:

``` 
for(i = 1; i < arr.length; i++){
	arr[i] = arr[i] + arr[i -1];
}
```
如果求类似 arr[i] ~ arr[j] 区间的和，这个时候就可以考虑使用前缀和的方式了。

## 代码

```py
class Solution:
    def f(self, scores, m):
		n = len(scores)
		arr = [0] * 151
        ans = []
        # 离散化，统计分数为 i 的有多少人
        for i in range(n):
            arr[scores[i]] += 1
	    # 构造前缀和
        for i in range(1, len(arr)):
            arr[i] = arr[i] + arr[i - 1]
        # 模拟 m 次询问
        for i in range(m):
            score = scores[m[i] - 1]
            sum = arr[score]
            ans.append(sum / n * 100)
        return ans  
void f(int scores[], int m[]) {
  int n = scores.length;
  int[] arr = new int[151];
  in[] ans = new int[];

  // 离散化，统计分数为 i 的有多少人
  for (int i = 0; i < n; i++) {
    arr[scores[i]]++;
  }

  // 构造前缀和
  for (int i = 1; i < arr.length; i++) {
    arr[i] = arr[i] + arr[i - 1];
  }

  // 模拟 m 次询问
  for (int i = 0; i < m.length; i++) {
    int score = scores[m[i] - 1];
    int sum = arr[score];
    ans.push(sum * 1.0 / n * 100)
  }

  return ans
}
```
## 复杂度分析

- 时间复杂度：$O(m + n)$
- 空间复杂度：$O(1)$，我们只开辟了额外的 151 长度的数组。

# 1371. 每个元音包含偶数次的最长子字符串
[1371. 每个元音包含偶数次的最长子字符串](https://github.com/azl397985856/leetcode/blob/master/problems/1371.find-the-longest-substring-containing-vowels-in-even-counts.md)

# 560. 和为K的子数组
[560. 和为K的子数组](https://github.com/azl397985856/leetcode/blob/master/problems/560.subarray-sum-equals-k.md)
