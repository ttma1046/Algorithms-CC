1. Prim Native implementation O(V2) 稠密图
2. Prim PQ implementation O(ELogV) 稀疏图
3. Kruskal UF implementation O(ELogE) (或者更大范围ELogV, E <= V2) 稀疏图

The Java API does not provide a Fibonacci Heap.
The java.util.PriorityQueue is a binary heap.