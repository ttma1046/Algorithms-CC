package stack;
import java.util.NoSuchElementException;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
/*
You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists. Implement an iterator to flatten it.

Implement the NestedIterator class:

NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
int next() Returns the next integer in the nested list.
boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
Your code will be tested with the following pseudocode:

initialize iterator with nestedList
res = []
while iterator.hasNext()
    append iterator.next() to the end of res
return res
If res matches the expected flattened list, then your code will be judged as correct.

Example 1:

Input: nestedList = [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:

Input: nestedList = [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].

Constraints:

1 <= nestedList.length <= 500
The values of the integers in the nested list is in the range [-106, 106].
*/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 */
interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();
    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();
    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}
/**/
class Flatten_Nested_List_Iterator_341 {
    public static void main(String[] args) {
        NestedIterator obj = new NestedIterator();
    }
}

/*
public class NestedIterator implements Iterator<Integer> {
    private List<Integer> list = new ArrayList<>();
    private int position = 0;

    public NestedIterator(List<NestedIterator> nestedList) {
        flattenList(nestedList);
    }

    private void flattenList(List<NestedIterator> nestedList) {
        for (NestedIterator nestedInteger: nestedList) {
            if (nestedInteger.isInteger())
                list.add(nestedInteger.getInteger());
            else
                flattenList(nestedInteger.getList());
        }
    }

    @Override
    public boolean hasNext() {
        return position < list.size();
    }


    @Override
    public Integer next() {
        if (position >= list.size())
            throw new NoSuchElementException();
        return list.get(position++);
    }
}
*/

class NestedIterator implements Iterator<Integer> {
    private Deque<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new ArrayDeque(nestedList);
    }

    @Override
    public boolean hasNext() {
        makeStackTopAnInteger();
        return stack.size() > 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) 
            throw new NoSuchElementException();
        
        return stack.removeFirst().getInteger();
    }

    private void makeStackTopAnInteger() {
        while(!stack.isEmpty() && !stack.peekFirst().isInteger()) {
            List<NestedInteger> nestedList = stack.removeFirst().getList();

            for (int i = nestedList.size(); i > 0; --i)
                stack.addFirst(nestedList.get(i));
        }
    }
}

public class NestedIterator implements Iterator<Integer> {
    private Deque<List<NestedInteger>> listStack = new ArrayDeque<>();
    private Deque<Integer> indexStack = new ArrayDeque<>();
    
    public NestedIterator(List<NestedInteger> nestedList) {
        listStack.addFirst(nestedList);
        indexStack.addFirst(0);
    }
        
    @Override
    public Integer next() {
        if (!hasNext()) 
            throw new NoSuchElementException();
        
        int currentIndex = indexStack.removeFirst();
        indexStack.addFirst(currentIndex + 1);
        return listStack.peekFirst().get(currentIndex).getInteger();
    }
    
    @Override
    public boolean hasNext() {
        makeStackTopAnInteger();
        return !indexStack.isEmpty();
    }

    private void makeStackTopAnInteger() {    
        while (!indexStack.isEmpty()) {                        
            // If the top list is used up, pop it and its index.
            if (indexStack.peekFirst() >= listStack.peekFirst().size()) 
                indexStack.removeFirst();
                listStack.removeFirst();
                continue;            

            // Otherwise, if it's already an integer, we don't need to do anything.
            if (listStack.peekFirst().get(indexStack.peekFirst()).isInteger())
                break;            

            // Otherwise, it must be a list. We need to update the previous index
            // and then add the new list with an index of 0.
            listStack.addFirst(listStack.peekFirst().get(indexStack.peekFirst()).getList());
            indexStack.addFirst(indexStack.removeFirst() + 1);
            indexStack.addFirst(0);
        }
    }
}