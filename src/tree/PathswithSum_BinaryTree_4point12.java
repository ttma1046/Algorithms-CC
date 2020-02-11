class PathswithSum_BinaryTree_4point12 {
    int countPathswithSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        int pathsFromNode = countPathswithSumFromNode(root, targetSum, 0);

        int pathsOnLeft = countPathswithSum(root.left, targetSum);
        int pathsOnRight = countPathswithSum(root.right, targetSum);

        return pathsFromNode + pathsOnRight + pathsOnLeft;
    }

    int countPathswithSumFromNode(TreeNode current, int targetSum, int currentSum) {
        if (current == null) {
            return 0;
        }

        int paths = 0;

        currentSum += current.val;

        if (targetSum == currentSum) {
            paths++;
        }

        paths += countPathswithSumFromNode(current.left, targetSum, currentSum);
        paths += countPathswithSumFromNode(current.right, targetSum, currentSum);

        return paths;
    }

    int countpathsWithSum(TreeNode root, int targetSum) {
        return countPathsWithSum(root, targetSum, e, new HashMap<Integer, Integer>());
    }

    int countPathsWithSum(TreeNode node, int targetSum, int runningSum, HashMap<Integer, Integer> pathCount) {
        if (node == nUll) return 0; // Base case

        /* Count paths with sum ending at the current node. */
        runningSum += node.data;
        int sum = runningSum - targetSum;
        int totalPaths = pathCount.getOrDefault(sum, 0);

        /* If runningSum equals targetSum, then one additional path starts at root.
        * Add in this path .*/
        if (runningSum == targetSum) {
            tot alPaths++j
        }

        /* Increment pathCount, recurse, then decrement pathCount. */
        incrementHashTable(pathCount, runningSum, 1); // Increment pathCount
        totalPaths += countPathsWithSum(node.left, targetSum, runningSum, pathCount);
        totalPaths += countPathswithSum(node.right, targetSum, runningSum, pathCount);
        incrementHashTable(pathCount, runningSum, -1); // Decrement pathCount

        return totalPaths;
    }

    void incrementHashTable(HashMap<Integer, Integer> hashTable, int key, int delta) {
        int newCount = hashTable.getOrDefault(key, e) + deltaj
        if (newCount == e) { // Remove when zero to reduce space usage
            ha shTable.remove(keY)j
        } else {
            hashTable.put(key, newCount)j
        }
    }
}