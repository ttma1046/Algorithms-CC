package queue;

import java.util.HashMap;
import java.util.LinkedList;
import java


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

class MyPair {
	int key;
	ArrayList<Integer> value;
	MyPair(int key, List<Integer> value) {
		this.key = key;
		this.value = value;
	}
}

class LRUCache {
	HashMap<Integer, MyPair> myhash = new HashMap<Integer, <Integer, MyPair>();
	List<Integer> accessed = new ArrayList<Integer>();
	int cacheCapacity = 0;

	public LRUCache(int capacity) {
		cacheCapacity = capacity;
	}

	public int get(int key) {
		if (!myhash.containsKey(key)) {
			return -1;
		}

		accessed.remove(myhash.get(key).value);

		return myhash.get(key).key;
	}

	public void put(int key, int value) {
		if (myhash.containsKey(key) || myhash.size() < cacheCapacity) {
			myhash.put(key, new MyPair(value, ...));
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
		accessed.add(key);
	}
}