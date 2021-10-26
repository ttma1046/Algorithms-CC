class PriorityQueue {
	int[] queue;
	int pqSize;

	public PriorityQueue() {
		this.queue = new int[100];
	}

	public boolean offer(int num) {
		queue[pqSize] = num;
		shiftUp(pqSize);
		pqSize++;
		return true;
	}

	public int poll() {
		int value = queue[0];
		queue[0] = queue[pqSize - 1];
		pqSize--;
		if (pqSize > 0) shiftDown(0);
		return value;
	}

	public int peek() {
		if (pqSize == 0) return null;
		return queue[0];
	}

	private void shiftDown(int nodeIndex) {
		int smallest = nodeIndex;
		int left = 2 * nodeIndex + 1;
		int right = 2 * nodeIndex + 2;

		if (left < pqSize && queue[left] < queue[smallest])
			smallest = left;

		if (right < pqSize && queue[right] < queue[smallest])
			smallest = right;

		if (smallest != nodeIndex) {
			swap(nodeIndex, smallest);
			shiftDown(smallest);
		}
	}

	private void shiftUp(int nodeIndex) {
		if (nodeIndex != 0) {
			int parentIndex = (nodeIndex - 1) / 2;
			if (queue[parentIndex] > queue[nodeIndex])
				swap(parentIndex, nodeIndex);
		}
	}

	private void swap(int i, int j) {
		int temp = queue[i];
		queue[i] = queue[j];
		queue[j] = temp;
	}
}