package queue;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;
/*
class LRUCache {
	HashMap<Integer, Integer> myhash = new HashMap<Integer, Integer>();
	Queue<Integer> queue = new LinkedList<Integer>();
	HashMap<Integer, Integer> countinQueue = new HashMap<Integer, Integer>();
	int cacheCapacity = 0;

	public LRUCache(int capacity) {
		cacheCapacity = capacity;
	}

	public int get(int key) {
		if (!myhash.containsKey(key)) {
			return -1;
		}
		addToQueue(key);
		return myhash.get(key);
	}

	public void put(int key, int value) {
		if (myhash.containsKey(key) || myhash.size() < cacheCapacity) {
			myhash.put(key, value);
			addToQueue(key);
			return;
		}

		while (true) {
			int candidate = queue.poll();
			int count = countinQueue.get(candidate);

			if (--count == 0) {
				countinQueue.remove(candidate);
				myhash.remove(candidate);
				myhash.put(key, value);
				addToQueue(key);
				return;
			} else {
				countinQueue.put(candidate, count);
			}
		}
	}

	private void addToQueue(int key) {
		queue.offer(key);
		countinQueue.put(key, countinQueue.getOrDefault(key, 0) + 1);
	}
}
*/

class MyPair {
	int value;
	int iterator;
	MyPair(int value, int iterator) {
		this.value = value;
		this.iterator = iterator;
	}
}

class LRUCache {
	public static void main(String[] args) {
		/*
		LRUCache lRUCache = new LRUCache(2);
		lRUCache.put(1, 1); // cache is {1=1}
		lRUCache.put(2, 2); // cache is {1=1, 2=2}
		System.out.println(lRUCache.get(1));    // return 1
		lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
		System.out.println(lRUCache.get(2));    // returns -1 (not found)
		lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
		System.out.println(lRUCache.get(1));    // return -1 (not found)
		System.out.println(lRUCache.get(3));    // return 3
		System.out.println(lRUCache.get(4));    // return 4
		*/

		LRUCache lRUCache = new LRUCache(2);
		System.out.println(lRUCache.get(2));    // return 1
		lRUCache.put(2, 6); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
		System.out.println(lRUCache.get(1));    // returns -1 (not found)
		lRUCache.put(1, 5); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
		lRUCache.put(1, 2); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
		System.out.println(lRUCache.get(1));    // return -1 (not found)
		System.out.println(lRUCache.get(2));    // return 3
	}

	Map<Integer, Integer> cache = new HashMap<Integer, Integer>();

	List<Integer> accessed = new ArrayList<Integer>();

	int cacheCapacity = 0;

	public LRUCache(int capacity) {
		cacheCapacity = capacity;
	}

	public int get(int key) {
		if (!cache.containsKey(key)) {
			return -1;
		}

		delete(key);
		push(key);

		return cache.get(key);
	}

	public void put(int key, int value) {
		if (cache.containsKey(key)) {
			delete(key);
		} else if (cache.size() == cacheCapacity) {
			int firstKey = accessed.get(0);
			cache.remove(firstKey);
			erase(0);
		}

		cache.put(key, value);
		push(key);
	}

	private void push(int key) {
		accessed.add(key);
	}

	private void delete(int key) {
		int index = accessed.indexOf(key);
		erase(index);
	}

	private void erase(int index) {
		accessed.remove(index);
	}
}