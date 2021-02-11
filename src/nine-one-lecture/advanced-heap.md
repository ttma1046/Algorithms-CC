# 堆

堆（英语：Heap）是计算机科学中的一种特别的树状数据结构。

## 堆的性质

- 在一个 最小堆(min heap) 中, 如果 P 是 C 的一个父级节点, 那么 P 的 key(或 value)应小于或等于 C 的对应值. 正因如此，堆顶元素一定是最小的，我们可以利用这个特点求最小值。或者结合 pop 操作 求第 k 小的值。
- 在一个 最大堆(max heap) 中, P 的 key(或 value)大于 C 的对应值。类似的，我们可以利用这个特点求最大和第 k 大的值。

需要注意的是优先队列不仅有堆一种，还有其它实现，但这里我们把两者做等价。而堆的实现有很多，本文提到的堆实现都是数组模拟完全二叉树。

## 堆的常见操作（以大顶堆为例）

- 插入

在堆中插入一个元素时，一般把元素插入到堆的尾部，然后对该元素进行上浮的操作（接下来会讲到）。

> 为什么选择在尾部插入，然后将元素上浮，而不是在头部插入，然后将元素下沉呢？

- 删除

堆只能删除根节点，不能删除其他的节点。由于堆顶被删除，因此它的左右孩子需要继承它，究竟选谁取决于谁更大，这样逐渐下沉即可。为了操作简单，我们可以在下沉之前与尾部节点进行一个交换。

> 为什么是尾部节点？大家可以思考一下。

## 堆的恢复

当我们向堆中插入一个元素或者从堆中删除一个元素时，很容易破坏堆的性质。因此必须提供一种方式，在堆被破坏的时候恢复堆的性质。那么如何恢复呢？这里就需要借助接下来要讲的两个操作**上浮**和 **下沉**。

1. **上浮 shift_up**

一般用于往一个堆的末尾中添加一个新元素，此时需要将上浮的节点与父节点比较，如果上浮的节点大于父节点，就将上浮节点与父节点交换位置，不断重复操作，直至上浮节点移到正确的位置。因此这里会有一个 类似 while 循环的东西。

由于调整至设计当前元素和它的祖先元素，因此时间复杂度为取决于树的高度，如果是完全二叉树，那么树的高度就是对数，也就是 $O(logN)$。

> 例如： 新建一个**大顶堆**

![img](https://upload-images.jianshu.io/upload_images/4064751-51fd43a2d2191488.png?imageMogr2/auto-orient/strip|imageView2/2/w/190/format/webp)

> 插入新元素16



![img](https://upload-images.jianshu.io/upload_images/4064751-7dd89da71927acb8.png?imageMogr2/auto-orient/strip|imageView2/2/w/190/format/webp)

> 然后将新元素与父节点进行比较，如果当前节点大于父节点则进行上浮操作

![img](https://upload-images.jianshu.io/upload_images/4064751-785a7a6cd104f775.png?imageMogr2/auto-orient/strip|imageView2/2/w/204/format/webp)

> 重复上一步，直到根节点或者小于父节点时停止



![img](https://upload-images.jianshu.io/upload_images/4064751-d7dae273e1f5a0ba.png?imageMogr2/auto-orient/strip|imageView2/2/w/192/format/webp)

```js
var Heap = function(k) {
    this.data = new Array(K + 1)
    this.data[0] = null
};

Heap.prototype.up = function(index) {
    // 如果循环到了根节点则结束递归
    if(index <= 1) return
    // 获取当前节点的父节点
    let parentIndex = parseInt(index / 2)
    // 如果当前节点大于父节点，则进行上浮操作
    if(this.data[index] > this.data[parentIndex]){
        this.swap(index, parentIndex)
        // 递归执行上浮操作
        this.up(parentIndex)
    }
};

Heap.prototype.swap = function(from, to) {
    let temp = this.data[from]
    this.data[from] = this.data[to]
    this.data[to] = temp
}
```







2. **下沉 shift_down**

一般用于从堆中删除元素的时候，此时需要将下沉的节点与左右字节点比较，如果当下沉的节点小于左右子节点，就将下沉节点与子节点交换位置，不断重复操作，直至下沉节点移到正确的位置。因此这里会有一个 类似 while 循环的东西。由于调整过程中涉及当前元素和它的**子节点中的一个**，因此时间复杂度为取决于树的高度，如果是完全二叉树，那么树的高度就是对数，也就是 $O(logN)$。

> 例如： 新建一个**大顶堆**

![img](https://upload-images.jianshu.io/upload_images/4064751-7eb32c6486a44f73.png?imageMogr2/auto-orient/strip|imageView2/2/w/190/format/webp)

> 删除根节点，*注意只能删除根节点*

![img](https://upload-images.jianshu.io/upload_images/4064751-7eb32c6486a44f73.png?imageMogr2/auto-orient/strip|imageView2/2/w/190/format/webp)

> 让尾节点成为根节点,



![img](https://upload-images.jianshu.io/upload_images/4064751-c28b15dc371b8e97.png?imageMogr2/auto-orient/strip|imageView2/2/w/190/format/webp)

> 对节点的左右子节点进行比较，如果小于左右子节点，则进行下沉操作

![img](https://upload-images.jianshu.io/upload_images/4064751-bfc42e89b5411f9b.png?imageMogr2/auto-orient/strip|imageView2/2/w/190/format/webp)

> 重复上一步，直到最底层或者大于左右子节点时停止

![img](https://upload-images.jianshu.io/upload_images/4064751-8b04275965f88961.png?imageMogr2/auto-orient/strip|imageView2/2/w/208/format/webp)

```js
var Heap = function(k) {
    this.data = new Array(K + 1)
    this.data[0] = null
};

Heap.prototype.down = function(index) {
    // 执行到了最底层，则结束递归
    if(index >= this.data.length - 1) return
    // 获取左右子节点
    let leftSon = index * 2, 
        rightSon = index * 2 + 1,
        target = index
    if(leftSon < this.data.length && this.data[target] < this.data[leftSon]){
        target = leftSon
    }
    if(rightSon < this.data.length && this.data[target] < this.data[rightSon]){
        target = rightSon
    }
    // 如果父节点小于某个子节点，则进行下沉操作
    if(target != index) {
        this.swap(target, index)
        // 递归执行下沉
        this.down(target)
    }
};

Heap.prototype.swap = function(from, to) {
    let temp = this.data[from]
    this.data[from] = this.data[to]
    this.data[to] = temp
}

```



> 你可以形象地将上浮和下沉的过程经过的节点综合看成一个路径。

## 堆的常见题型

利用对的性质我们可以做很多有趣的事情，这里我举几个例子。

1. 取第 top K 的数

维护一个大小为 K 的堆，直接取堆顶即可。比如求第 K 大， 则维护一个大小为 K 的小顶堆，依次将数据放入堆中，当堆的大小满了的时候，只需要将堆顶元素与下一个数比较：如果大于堆顶元素，则将当前的堆顶元素抛弃，并将该元素插入堆中。遍历完全部数据，堆中剩下的是按照小顶堆排列的最大的 K 个数，由于堆顶是最小的， 因此 Top K 的元素就是堆顶元素。如果是求前 K 个最小的数，只需要改为大顶堆即可。

2. 求某一个数组的中位数

假如数组是有序（例如升序）的，那中位数会将数组分成两半。如果我们将左边 n/2个较 小的元素构建成一个大顶堆，再将右边 n/2 个较大元素构建成一个小顶堆。那么：

- 数组数量是偶数时，中位数为：（大顶堆堆顶 + 小顶堆的堆顶）/ 2
- 数组数量为奇数，中位数为：数量较多的堆的堆顶元素

3. 堆排序

   3.1. 建堆

   3.2. 删除顶部元素

   3.3. 调整堆

   3.4. 循环 2 和 3

## 代码

### 建堆

 Python:

```py
import heapq
nums=[1,8,2,23,7,-4,18,23,42,37,2]
print(heapq.nlargest(3,nums))# [42, 37, 23]
print(heapq.nsmallest(3,nums)) # [-4, 1, 2]
```

### 堆化

```py
import heapq
nums=[1,8,2,23,7,-4,18,23,42,37,2]
heapq.heapify(heap)# 将列表原地转换成堆 [-4, 2, 1, 23, 7, 2, 18, 23, 42, 37, 8]
```

### push 和 pop

```py
# heap: [-4, 2, 1, 23, 7, 2, 18, 23, 42, 37, 8]
heapq.heappop(heap) #-4
heapq.heappop(heap) # 1
heapq.heappop(heap) # 2
```
