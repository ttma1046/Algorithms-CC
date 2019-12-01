package heap;
import java.util.ArrayList;

// Do not edit the class below except for the buildHeap,
// siftDown, siftUp, peek, remove, and insert methods.
// Feel free to add new properties and methods to the class.
/*
 [1,2,3,4,5,6,7]
 */
class MinHeapMy {
    static class MinHeap {
        ArrayList<Integer> heap = new ArrayList<Integer>();

        public MinHeap(ArrayList<Integer> array) {
            heap = buildHeap(array);
        }

        // O(n) time | O(1) space
        public ArrayList<Integer> buildHeap(ArrayList<Integer> array) {
            int firstParentIdx = this.getParentIndex(array.size() - 1);

            for(int currentIdx = firstParentIdx; currentIdx >= 0; currentIdx--) {
                this.siftDown(currentIdx, array.size() - 1, array);
            }
            return array;
        }

        // O(Log(n)) time | O(1) space
        public void siftDown(int currentIdx, int endIdx, ArrayList<Integer> heap) {
            int leftChildIdx = this.getLeftChildIndex(currentIdx);
            while (leftChildIdx <= endIdx) {
                int rightChildIdx = this.getRightChildIndex(currentIdx);

                if (rightChildIdx > endIdx) {
                    rightChildIdx = -1;
                }

                int idxToSwap;
                if (rightChildIdx > -1 && heap.get(rightChildIdx) < heap.get(leftChildIdx)) {
                    idxToSwap = rightChildIdx;
                } else {
                    idxToSwap = leftChildIdx;
                }

                if (heap.get(idxToSwap) < heap.get(currentIdx)) {
                    this.swap(currentIdx, idxToSwap, heap);
                    currentIdx = idxToSwap;
                    leftChildIdx = currentIdx * 2 + 1;
                } else {
                    return;
                }
            }
        }

        // O(Log(n)) time | O(1) space
        public void siftUp(int currentIdx, ArrayList<Integer> heap) {
            int parentIdx = this.getParentIndex(currentIdx);
            while (currentIdx > 0 && heap.get(currentIdx) < heap.get(parentIdx)) {
                this.swap(currentIdx, parentIdx, heap);
                currentIdx = parentIdx;
                parentIdx = this.getParentIndex(currentIdx);
            }
        }

        public int peek() {
            return this.heap.get(0);
        }

        public int remove() {
            this.swap(0, this.heap.size() - 1, this.heap);
            int valueToRemove = this.heap.get(this.heap.size() - 1);
            this.heap.remove(this.heap.size() - 1);
            this.siftDown(0, this.heap.size() - 1, this.heap);
            return valueToRemove;
        }

        public void insert(int value) {
            this.heap.add(value);
            siftUp(this.heap.size() - 1, this.heap);
        }

        // leftChildLocation = parentLocation * 2 + 1;
        // rightChildLocation = parentLocation * 2 + 2
        // parentLocation = (i - 1) / 2
        private int getParentIndex(int index) {
            return (index - 1) / 2;
        }

        private int getLeftChildIndex(int index) {
            return index * 2 + 1;
        }

        private int getRightChildIndex(int index) {
            return index * 2 + 2;
        }

        private void swap(int idxOne, int idxTwo, ArrayList<Integer> heap) {
            int temp = heap.get(idxOne);
            heap.set(idxOne, heap.get(idxTwo));
            heap.set(idxTwo, temp);
        }
    }
}