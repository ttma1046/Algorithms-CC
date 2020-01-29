package dfs;
import java.util.List;

class NestedListWeightSum_339 {

    // This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
    class NestedInteger {
        // Constructor initializes an empty nested list.
        public NestedInteger() {}

        // Constructor initializes a single integer.
        public NestedInteger(int value) {}

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() { return true; }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() { return 0; }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {}

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {}

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList() { return null; }
    }

    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        int level = 1;
        return calSum(nestedList, level);
    }

    public int calSum(List<NestedInteger> nestedList, int level) {
        if (nestedList == null) return 0;
        int result = 0;
        for (NestedInteger item: nestedList) {
            if (item.isInteger()) {
                result += item.getInteger() * level;
            }

            if (!item.isInteger() && item.getList() != null) {
                result += calSum(item.getList(), level + 1);
            }
        }

        return result;
    }
}