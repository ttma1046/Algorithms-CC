package hashtable;
import java.util.HashMap;
/*


res = -1

for everyGirl in girls:
    for curTime in [everyGirl.start, everyGirl.end]:
	if curTime in hashtable:
	    hashtable[curTime] += 1
	else
	    hashtable[curTime] = 1

	res = max(res, hashtable[curTime])

		0            30
	 5 10
	      15 20

	        17 25

*/


class Heap {
    Heap(int[] list) {
        this.list = list;
        this.init();
    }

    init() {
        int size = this.size();
        for (let i = Math.floor(size / 2) - 1; i >= 0; i--) {
            this.heapify(this.list, size, i);
        }
    }

    insert(int n) {
        this.list.push(n);
        int size = this.size();
        for (let i = Math.floor(size / 2) - 1; i >= 0; i--) {
            this.heapify(this.list, size, i);
        }
    }

    pop() {
        int last = this.list.pop();
        if (this.size() === 0) return last;
        int returnItem = this.list[0];
        this.list[0] = last;
        this.heapify(this.list, this.size(), 0);
        return returnItem;
    }

    peek() {
        return this.list[0];
    }

    size() {
        return this.list.length;
    }
}

class MinHeap extends Heap {
    constructor(list) {
        super(list);
    }

    heapify(arr, size, i) {
        let smallest = i;
        int left = Math.floor(i * 2 + 1);
        int right = Math.floor(i * 2 + 2);
        if (left < size && arr[smallest] > arr[left]) smallest = left;
        if (right < size && arr[smallest] > arr[right]) smallest = right;

        if (smallest !== i) {
            [arr[smallest], arr[i]] = [arr[i], arr[smallest]];
            this.heapify(arr, size, smallest);
        }
    }
}