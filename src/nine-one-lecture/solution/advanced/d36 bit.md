# 78. 子集

## 思路

1. 例如我们现在有 3 个元素，那我们分别给这 3 个元素编号为 A B C
2. 实际上这三个元素能取出的所有子集就是这 3 个元素的使用与不使用这两种状态的笛卡尔积。
3. 我们使用 0 与 1 分别表示这 3 个元素的使用与不使用的状态。
4. 那么这 3 个元素能构成的的所有情况其实就是：000, 001, 010 ... 111
5. 依次遍历这些数，将为 1 的元素取出，即为子集:


## 代码

JavaScript Code

```js
var subsets = function (nums) {
  let res = [],
    sum = 1 << nums.length,
    temp;
  for (let now = 0; now < sum; now++) {
    temp = [];
    for (let i = 0; now >> i > 0; i++) {
      if (((now >> i) & 1) == 1) {
        temp.push(nums[i]);
      }
    }
    res.push(temp);
  }
  return res;
};
```

### 复杂度分析

-   时间复杂度：$O(n)$
-   空间复杂度：$O(1)$
