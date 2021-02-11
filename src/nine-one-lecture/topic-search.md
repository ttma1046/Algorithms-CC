# 搜索

## 介绍

搜索一般指在有限的状态空间中进行枚举，通过穷尽所有的可能来找到符合条件的解或者解的个数。
根据搜索方式的不同，搜索算法可以分为 DFS，BFS，双向搜索，A\*算法等。这里只介绍 DFS/BFS，以及发生在 DFS 上一种技巧-回溯。

## 分类

### DFS

DFS 的概念来自于图论，但是搜索中 DFS 和图论中 DFS 还是有一些区别，搜索中 DFS 一般指的是通过递归函数实现暴力枚举。

#### 算法流程

1. 首先将根节点放入**stack**中。
2. 从*stack*中取出第一个节点，并检验它是否为目标。如果找到目标，则结束搜寻并回传结果。否则将它某一个尚未检验过的直接子节点加入**stack**中。
3. 重复步骤 2。
4. 如果不存在未检测过的直接子节点。将上一级节点加入**stack**中。
   重复步骤 2。
5. 重复步骤 4。
6. 若**stack**为空，表示整张图都检查过了——亦即图中没有欲搜寻的目标。结束搜寻并回传“找不到目标”。

> 这里的 stack 可以理解为自实现的栈，也可以理解为调用栈

#### 算法模板

```js
const visited = {}
function dfs(i) {
	if (满足特定条件）{
		// 返回结果 or 退出搜索空间
	}

	visited[i] = true // 将当前状态标为已搜索
	for (根据i能到达的下个状态j) {
		if (!visited[j]) { // 如果状态j没有被搜索过
			dfs(j)
		}
	}
}
```

### BFS

BFS 也是图论中算法的一种，不同于 DFS 的是 BFS 采用横向搜索的方式，在数据结构上通常采用队列结构。

#### 算法流程

1. 首先将根节点放入队列中。
2. 从队列中取出第一个节点，并检验它是否为目标。
   - 如果找到目标，则结束搜索并回传结果。
   - 否则将它所有尚未检验过的直接子节点加入队列中。
3. 若队列为空，表示整张图都检查过了——亦即图中没有欲搜索的目标。结束搜索并回传“找不到目标”。
4. 重复步骤 2。

#### 算法模板

```js
const visited = {}
function bfs() {
	let q = new Queue()
	q.push(初始状态)
	while(q.length) {
		let i = q.pop()
		if (visited[i]) continue
		for (i的可抵达状态j) {
			if (j 合法) {
				q.push(j)
			}
		}
	}
	// 找到所有合法解
}
```

### 回溯

回溯是 DFS 中的一种技巧。回溯法采用 [试错](https://zh.wikipedia.org/wiki/%E8%AF%95%E9%94%99) 的思想，它尝试分步的去解决一个问题。在分步解决问题的过程中，当它通过尝试发现现有的分步答案不能得到有效的正确的解答的时候，它将**取消上一步甚至是上几步的计算，再通过其它的可能的分步解答再次尝试寻找问题的答案**。

通俗上讲，回溯是一种走不通就回头的算法。

回溯的本质是穷举所有可能，尽管有时候可以通过剪枝去除一些根本不可能是答案的分支， 但是从本质上讲，仍然是一种暴力枚举算法。

回溯法可以抽象为树形结构，并且是是一颗高度有限的树（N 叉树）。回溯法解决的都是在集合中查找子集，集合的大小就是树的叉树，递归的深度，都构成的树的高度。

以求数组 [1,2,3] 的子集为例：

![](https://tva1.sinaimg.cn/large/0081Kckwly1gkau6ustfdj30v80igtag.jpg)

以上图来说， 我们会在每一个节点进行加入到结果集这一次操作。

![](https://tva1.sinaimg.cn/large/0081Kckwly1gkau9jceowj30uj0jrdhv.jpg)

对于上面的灰色节点， 加入结果集就是 [1]。

![](https://tva1.sinaimg.cn/large/0081Kckwly1gkauahh57bj30tj0j0wgg.jpg)

这个加入结果集就是 [1,2]。

![](https://tva1.sinaimg.cn/large/0081Kckwly1gkaub4scgij30uu0io40h.jpg)

这个加入结果集就是 [2,3]，以此类推。一共有六个子集，分别是 [1], [1,2], [1,2,3], [2], [2,3] 和 [3]。

而对于全排列问题则会在叶子节点加入到结果集，不过这都是细节问题。掌握了思想之后， 大家再去学习细节就会事半功倍。

下面我们来看下具体代码怎么写。

#### 算法流程

1. 构造空间树。
2. 进行遍历。
3. 如遇到边界条件，即不再向下搜索，转而搜索另一条链。
4. 达到目标条件，输出结果。

#### 算法模板

伪代码：

```js
const visited = {}
function dfs(i) {
	if (满足特定条件）{
		// 返回结果 or 退出搜索空间
	}

	visited[i] = true // 将当前状态标为已搜索
	dosomething(i) // 对i做一些操作
	for (根据i能到达的下个状态j) {
		if (!visited[j]) { // 如果状态j没有被搜索过
			dfs(j)
		}
	}
	undo(i) // 恢复i
}
```

#### 剪枝

回溯题目的另外一个考点是剪枝， 通过恰当地剪枝，可以有效减少时间，比如我通过剪枝操作将**石子游戏 V**的时间从 900 多 ms 优化到了 500 多 ms。

剪枝在每道题的技巧都是不一样的， 不过一个简单的原则就是**避免根本不可能是答案的递归**。

#### 笛卡尔积

一些回溯的题目，我们仍然也可以采用笛卡尔积的方式，将结果保存在返回值而不是路径中，这样就避免了回溯状态，并且由于结果在返回值中，因此可以使用记忆化递归， 进而优化为动态规划形式。

参考题目：

- [140. 单词拆分 II](https://github.com/azl397985856/leetcode/blob/master/problems/140.word-break-ii.md)
- [816. 模糊坐标](https://github.com/azl397985856/leetcode/blob/master/problems/816.ambiguous-coordinates.md)

这类问题不同于子集和全排列，其组合是有规律的，我们可以使用笛卡尔积公式，将两个或更多子集联合起来。

#### 经典题目

- [39. 组合总和](https://github.com/azl397985856/leetcode/blob/master/problems/39.combination-sum.md)
- [40. 组合总和 II](https://github.com/azl397985856/leetcode/blob/master/problems/40.combination-sum-ii.md)
- [46. 全排列](https://github.com/azl397985856/leetcode/blob/master/problems/46.permutations.md)
- [47. 全排列 II](https://github.com/azl397985856/leetcode/blob/master/problems/47.permutations-ii.md)
- [52. N 皇后 II](https://github.com/azl397985856/leetcode/blob/master/problems/52.N-Queens-II.md)
- [78. 子集](https://github.com/azl397985856/leetcode/blob/master/problems/78.subsets.md)
- [90. 子集 II](https://github.com/azl397985856/leetcode/blob/master/problems/90.subsets-ii.md)
- [113. 路径总和 II](https://github.com/azl397985856/leetcode/blob/master/problems/113.path-sum-ii.md)
- [131. 分割回文串](https://github.com/azl397985856/leetcode/blob/master/problems/131.palindrome-partitioning.md)
- [1255. 得分最高的单词集合](https://github.com/azl397985856/leetcode/blob/master/problems/1255.maximum-score-words-formed-by-letters.md)

## 相关专题

- [小岛问题](https://github.com/azl397985856/leetcode/blob/master/thinkings/island.md)
- [深度优先遍历](https://github.com/azl397985856/leetcode/blob/master/thinkings/DFS.md)
- [回溯算法](https://github.com/azl397985856/leetcode/blob/master/thinkings/backtrack.md)

## 总结

BFS 是面，每一层的节点同时进行搜索。而 DFS 是线，纵向一个一个解决。一般来说找最短路径的时候使用 BFS，其他时候还是 DFS 写起来比较方便。

BFS 一般需要借助于队列这种数据结构，而 BFS 则可以借助递归或者栈来进行。

回溯的本质就是暴力枚举所有可能。要注意的是，由于回溯通常结果集都记录在回溯树的路径上，因此如果不进行撤销操作， 则可能在回溯后状态不正确导致结果有差异， 因此需要在递归到底部往上冒泡的时候进行撤销状态。
