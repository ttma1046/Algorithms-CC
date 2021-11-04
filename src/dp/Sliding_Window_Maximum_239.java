package dp;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

class Sliding_Window_Maximum_239 {
    Deque<Integer> deq = new ArrayDeque<Integer>();
    int [] nums;

    public int[] maxSlidingWindowII(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        // init deque and output

        int max_idx = 0;
        for (int i = 0; i < k; i++) {
            clean_deque(i, k, nums);
            deq.addLast(i);
            // compute max in nums[:k]
            if (nums[i] > nums[max_idx]) max_idx = i;
        }

        int [] output = new int[n - k + 1];
        output[0] = nums[max_idx];

        for (int i = k; i < n; i++) {
            clean_deque(i, k, nums);
            deq.addLast(i);
            output[i - k + 1] = nums[deq.getFirst()];
        }

        return output;
    }

    public void clean_deque(int i, int k, int[] nums) {
        // remove indexes of elements not from sliding window
        if (!deq.isEmpty() && deq.getFirst() == i - k) {
            deq.removeFirst();
        }

        // remove from deq indexes of all elements
        // which are smaller than current element nums[i]
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]) {
            deq.removeLast();
        }
    }

    private class MonotonicQueue {
        Deque<Integer> deque;

        public MonotonicQueue() {
            deque = new ArrayDeque<>();
        }

        public Integer max() {
            return deque.peekFirst();
        }

        public void pop(int num) {
            if(!deque.isEmpty() && num == deque.peekFirst())
                deque.pollFirst();
        }

        public void push(int num) {
            while(!deque.isEmpty() && deque.peekLast() < num)
                deque.pollLast();
            deque.addLast(num);
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue windows = new MonotonicQueue();
        int[] res = new int[nums.length - k + 1];
        for(int i = 0; i < nums.length; i++) {
            if(i < k - 1) {
                windows.push(nums[i]);
            } else {
                windows.push(nums[i]);
                res[i + 1 - k] = windows.max();
                windows.pop(nums[i - k + 1]);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Sliding_Window_Maximum_239 obj = new Sliding_Window_Maximum_239();

        int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
        int k = 3;

        int[] res = obj.maxSlidingWindow(nums, k);

        for (int i : res) System.out.println(i);
    }


    public int[] maxSlidingWindowIII(int[] nums, int k) {
        // assume nums is not null
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        int[] result = new int[n - k + 1]; // number of windows

        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((i1, i2) -> (nums[i2] - nums[i1])); // stores values

        for (int i = 0; i < n; ++i) {
            int start = i - k;
            if (start >= 0) maxPQ.remove(start); // remove the out-of-bound value by index

            maxPQ.offer(i);
            if (maxPQ.size() == k) result[i - k + 1] = nums[maxPQ.peek()];
        }

        return result;
    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length < 2) return nums;
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
        LinkedList<Integer> queue = new LinkedList();
        // 结果数组
        int[] result = new int[nums.length - k + 1];
        // 遍历nums数组
        for(int i = 0; i < nums.length; i++) {
            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
            while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            // 添加当前值对应的数组下标
            queue.addLast(i);
            // 判断当前队列中队首的值是否有效
            if(queue.peek() <= i - k) {
                queue.poll();
            }
            // 当窗口长度为k时 保存当前窗口中最大值
            if(i + 1 >= k) {
                result[i + 1 - k] = nums[queue.peek()];
            }
        }
        return result;
    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        // 窗口个数

        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        int[] res = new int[n - k + 1];
        LinkedList<Integer> queue = new LinkedList<>();

        // 遍历数组中元素，right表示滑动窗口右边界
        for(int right = 0; right < n; right++) {
            // 如果队列不为空且当前考察元素大于等于队尾元素，则将队尾元素移除。
            // 直到，队列为空或当前考察元素小于新的队尾元素
            while (!queue.isEmpty() && nums[right] >= nums[queue.peekLast()]) {
                queue.removeLast();
            }

            // 存储元素下标
            queue.addLast(right);

            // 计算窗口左侧边界
            int left = right - k + 1;
            // 当队首元素的下标小于滑动窗口左侧边界left时
            // 表示队首元素已经不再滑动窗口内，因此将其从队首移除
            if (queue.peekFirst() < left) {
                queue.removeFirst();
            }

            // 由于数组下标从0开始，因此当窗口右边界right+1大于等于窗口大小k时
            // 意味着窗口形成。此时，队首元素就是该窗口内的最大值
            if (right + 1 >= k) {
                res[left] = nums[queue.peekFirst()];
            }
        }
        return res;
    }
}