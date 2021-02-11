### 定义

分治算法的基本思想是将一个规模为N的问题分解为K个规模较小的子问题，并根据子问题的解求原问题。这些子问题相互独立且与原问题性质相同，求出子问题的解，就可得到原问题的解。

分治法顾名思义由分和治来组成。

- 分。 将一个规模为N的问题分解为K个规模较小的子问题
- 治。 根据子问题的解求原问题


### 常用场景

一般题目具有以下3个特征，就可以考虑使用分治算法


1) 如果问题可以被分解为若干个规模较小的相同问题

2) 这些被分解的问题的结果可以进行合并

3) 这些被分解的问题是相互独立的，不包含重叠的子问题（类似动态规划？）



### 解题步骤

![分治](<https://img-blog.csdnimg.cn/20200823212452121.png>)



1. 将原问题分解至，达到求解边界的结构相同互相独立的子问题
2. 对所有的子问题进行求解
3. 将所有子问题的解进行合并，从而得到原问题



### 思路

1. 思考子问题的求解边界(问题缩小至什么规模时可以求解)与求解思路
2. 思考如果将子问题的解进行合并
3. 思考编码思路（一般利用递归）



例如经典的归并排序算法

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200823220453220.png)



1. 如果时成千上万个数据进行排序，肯定头大，但是如果规模缩小到只有一个元素时，问题就变得很容易解了



2. 然后我们发现将子问题的解两两之间进行合并其实就是合并两个有序数组（使用[双指针](<https://github.com/leetcode-pp/91alg-1/blob/master/basic-05.md>)轻松解决）

3. 编码思路就很清晰了，

- 将问题不断进行二分，直到所有的子问题的规模为1个元素
- 对子问题进行求解
- 利用合并两个有序数组的思路，将子问题两两进行合并
- 得到最终解



#### 代码（JS）



```js
var sortArray = function(nums) {
    var len = nums.length;
    if (len < 2) {
        return nums;
    }
    var middle = parseInt(len / 2),
        left = nums.slice(0, middle),
        right = nums.slice(middle);
    return merge(sortArray(left), sortArray(right));
}
 
function merge(left, right) {
    let ans = []
    let leftPoint = rightPoint = 0
    let leftLen = left.length, rightLen = right.length
    while(leftPoint < leftLen && rightPoint < rightLen){
        if(left[leftPoint] < right[rightPoint]){
            ans.push(left[leftPoint])
            leftPoint++
        } else {
            ans.push(right[rightPoint])
            rightPoint++
        }
    }
    return ans.concat(leftPoint < leftLen ? left.slice(leftPoint) : right.slice(rightPoint));
}
```



### 经典题型

#### [面试题 08.06. 汉诺塔问题](https://leetcode-cn.com/problems/hanota-lcci/)

#### [96. 不同的二叉搜索树](https://leetcode-cn.com/problems/unique-binary-search-trees/)

例如96题

我们拿到题目可以发现该题存在以下特点

1. 如果数据规模减小时（例如只有2个节点时），求解会变得简单
2. 问题可以进行拆分，并且子问题相互独立
3. 知道了左右子树的排列情况即可求解，子问题的结果可以进行合并





按照上面提到的思考模板

1. 当给定的n小于2时我们可以直接得出答案，所以子问题的最小边界为2

2. 当我们知道左子树的节点数与右子树的节点数时，

   即可得到所有的排列情况  == > （左子树的排列情况总数） * （右子树的排列情况总数）

   > 对子问题进行合并  

3. 很可能会出现大量的重复计算，所以可以把之前计算出来的结果存起来

代码（JS）：

```js
var numTrees = function(n) {
    let memory = new Array(n + 1)
    memory[0] = memory[1] = 1
    return getNum(n, memory)
};

function getNum(n, memory){
    if(memory[n]){
        return memory[n]
    }
    let ans = 0
    for(let i = 1;i <= n; i++){
        ans += getNum(i - 1, memory) * getNum(n - i, memory)
    }
    return memory[n] = ans
}
```

> 这道题也可以不用递归，使用动态规划的思路迭代求解，效率会更高
