# 145.二叉树的后序遍历

## 递归

### 思路

1. 遍历左子树
2. 遍历右子树
3. 遍历当前节点

> 在遍历顺序中，根节点是在**最后面**的，所以叫做**后序遍历**。

```
preorder(root.left)
preorder(root.right)
print(root)
```

### 代码

JavaScript Code

```js
var postorderTraversal = function(root, res = []) {
    if(!root) return []
    root.left && postorderTraversal(root.left, res)
    root.right && postorderTraversal(root.right, res)
    res.push(root.val)
    return res
};
```

### 复杂度分析

-   时间复杂度：$O(n) 将所有节点遍历一遍。
-   空间复杂度：$O(h)$，h 为二叉树的高度（递归时函数栈帧占用的空间）。

## 迭代

### 思路

1. 用栈来模拟dfs
2. 先将root入栈， pre指针初始化为空（用于记录上次root指针的指向，避免环路）
3. root指针指向root的左节点直到没有左子树，此过程中依次将左子树入栈
4. 如果root有右节点针则root指向右节点且右节点不等于pre指针，然后循环第3步
5. 否则将当前节点的值存入结果数组res中，pre指针指向root，弹出栈顶元素 node，root指针指向node，循环第3步

### 代码

JavaScript Code

```js
var postorderTraversal = function(root) {
    if(!root) return []
    let data = [], stack = [], pre = null
    while(root || stack.length){
        while(root){
            stack.push(root)
            root = root.left
        }
        root = stack.pop()
        if(root.right && root.right !== pre){
            stack.push(root)
            root = root.right
        } else {
            data.push(root.val)
            pre = root
            root = null
        }
    }
    return data
};
```

### 复杂度分析

-   时间复杂度：$O(n) 将所有节点遍历一遍。
-   空间复杂度：$O(h)$，h 为二叉树的高度（类似于使用递归时占用的函数栈帧）。


## 双色球

### 代码

JavaScript Code

```js
var postorderTraversal = function(root) {
    if(!root) return []
    const WHITE = 1, GREAY = 0 
    let data = [], stack = [[WHITE, root]]
    while(stack.length){
        let [color, node] = stack.pop()
        if(color === WHITE) {
            stack.push([GREAY, node])
            node.right && stack.push([WHITE, node.right])
            node.left && stack.push([WHITE, node.left])
        } 
        if(color === GREAY){
            data.push(node.val)
        }
    }
    return data
};
```

## 扩展

前序与中序遍历与后序遍历的思路与后序遍历一样都是dfs，只是某些步骤做一些调整，这两种遍历就不再赘述。


# 102.二叉树的层序遍历

## 迭代

### 思路

1. 使用一个数组level用来存当前层的所有节点
2. 遍历该数组，同时将下一层的节点存入newLevel数组
3. 令level等于newLevel循环第一步直到level为空


### 复杂度分析

-   时间复杂度：$O(n)$，n 为树的节点数。
-   空间复杂度：$O(h)$，h 为每层的节点个数。

### 代码

JavaScript Code

```js
var levelOrder = function(root) {
    if(!root) return []
    let res = [], level = [root]
    while(level.length){
       let newLevel = [], levelVal = []
       level.forEach(item => {
           levelVal.push(item.val)
           if(item.left) newLevel.push(item.left)
           if(item.right) newLevel.push(item.right)
       })
       res.push(levelVal)
       level = newLevel;
    }
    return res;
};
```


## 递归

### 思路

1. 使用一个标志位标志当前是树的第n层。 
2. 遍历左子树
3. 遍历右子树
4. 将当前节点存入第n层的结果数组中


### 复杂度分析

-   时间复杂度：$O(n)$, n 为二叉树的节点数。
-   空间复杂度：$O(h)$，h 是二叉树的深度（递归产生的函数调用栈）。

### 代码

JavaScript Code

```js
var postorderTraversal = function(root, res = []) {
    if(!root) return []
    root.left && postorderTraversal(root.left, res)
    root.right && postorderTraversal(root.right, res)
    res.push(root.val)
    return res
};
```
