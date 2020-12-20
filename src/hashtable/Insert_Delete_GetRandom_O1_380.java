package hashtable;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

/*
Implement the RandomizedSet class:

bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
Follow up: Could you implement the functions of the class with each function works in average O(1) time?



Example 1:

Input
["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]
Output
[null, true, false, true, 2, true, false, 2]

Explanation
RandomizedSet randomizedSet = new RandomizedSet();
randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
randomizedSet.insert(2); // 2 was already in the set, so return false.
randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.


Constraints:

-231 <= val <= 231 - 1
At most 105 calls will be made to insert, remove, and getRandom.
There will be at least one element in the data structure when getRandom is called.
*/
class Insert_Delete_GetRandom_O1_380 {
    // Map<Integer, Integer> myMap = HashMap<Integer, Integer>();
    // List<Integer> myList = ArrayList<Integer>();
    Map<Integer, Integer> myMap;
    List<Integer> myList;

    Random rand = new Random();

    /** Initialize your data structure here. */
    public Insert_Delete_GetRandom_O1_380() {
        myMap = new HashMap<>();
        myList = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (myMap.containsKey(val)) return false;

        myMap.put(val, myList.size());
        myList.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (myMap.containsKey(val)) {
            int lastElement = myList.get(myList.size() - 1);
            int index = myMap.get(val);

            myList.set(index, lastElement);
            myMap.put(lastElement, index);
            myList.remove(myList.size() - 1);
            myMap.remove(val);

            return true;
        }

        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return myList.get(rand.nextInt(myList.size()));
    }

    public static void main(String[] args) {
        Insert_Delete_GetRandom_O1_380 randomizedSet = new Insert_Delete_GetRandom_O1_380();

        System.out.println(randomizedSet.insert(1)); // Inserts 1 to the set. Returns true as 1 was inserted successfully.

        System.out.println(randomizedSet.remove(2)); // Returns false as 2 does not exist in the set.

        System.out.println(randomizedSet.insert(2)); // Inserts 2 to the set, returns true. Set now contains [1,2].

        System.out.println(randomizedSet.getRandom()); // getRandom() should return either 1 or 2 randomly.

        System.out.println(randomizedSet.remove(1)); // Removes 1 from the set, returns true. Set now contains [2].

        System.out.println(randomizedSet.insert(2)); // 2 was already in the set, so return false.

        System.out.println(randomizedSet.getRandom()); // Since 2 is the only number in the set, getRandom() will always return 2.

    }
}

class RandomizedSet {
    private HashMap<Integer, Integer> map;
    private int[] value;
    private int size;
    private Random rand;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>(10000);
        value = new int[10000];
        size = 0;
        rand = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain
     * the specified element.
     */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, size);
            value[size++] = val;
            return true;
        }
        return false;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified
     * element.
     */
    public boolean remove(int val) {
        Integer idx = map.remove(val);
        if (idx != null) {
            size--;
            if (idx < size) {
                value[idx] = value[size];
                map.put(value[idx], idx);
            }
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return value[rand.nextInt(size)];
    }
}
/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */


/*
Implement the RandomizedSet class:

    bool insert(int val) Inserts an item val into the set if not present.
        Returns true if the item was not present, false otherwise.
    bool remove(int val) Removes an item val from the set if present.
        Returns true if the item was present, false otherwise.
    int getRandom() Returns a random element from the current set of elements
        (it's guaranteed that at least one element exists when this method is called).
        Each element must have the same probability of being returned.

    Follow up:
        Could you implement the functions of the class with each function works in average O(1) time?

 

Example 1:

Input
["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]
Output
[null, true, false, true, 2, true, false, 2]

Explanation
RandomizedSet randomizedSet = new RandomizedSet();
randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
randomizedSet.insert(2); // 2 was already in the set, so return false.
randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 

Constraints:

-231 <= val <= 231 - 1
At most 105 calls will be made to insert, remove, and getRandom.
There will be at least one element in the data structure when getRandom is called.


*/
class RandomizedSet {
    Map<Integer, Integer> map;
    List<Integer> list;
    Random rand;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        rand = new Random();
        map = new HashMap<Integer, Integer>();
        list = new ArrayList<Integer>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;

        list.add(val);

        map.put(val, list.size() - 1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int lastElement = list.get(list.size() - 1);
            int index = map.get(val);

            list.set(index, lastElement);
            map.put(lastElement, index);

            list.remove(list.size() - 1);
            map.remove(val);

            return true;
        }

        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}


class RandomizedSet {
    Map<Integer, Integer> map;
    int[] list;
    int size;
    Random rand;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        rand = new Random();
        map = new HashMap<Integer, Integer>(10000);
        list = new int[10000];
        size = 0;
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, size);
        list[size++] = val;
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int lastElement = list[--size];
            int elementIndex = map.remove(val);

            if (elementIndex != size) {
                map.put(lastElement, elementIndex);
                list[elementIndex] = lastElement;
            }

            return true;
        }

        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list[rand.nextInt(size)];
    }
}

