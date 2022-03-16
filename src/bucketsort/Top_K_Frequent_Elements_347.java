package bucketsort;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
/*
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:

Input: nums = [1], k = 1
Output: [1]

Constraints:

1 <= nums.length <= 105
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.

Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/
class Top_K_Frequent_elements_347 {
    public static void main(String[] args) {
        Top_K_Frequent_elements_347 obj = new Top_K_Frequent_elements_347();
        int[] nums = new int[] {1, 1, 1, 2, 2, 3};
        int[] res = obj.topKFrequent(nums, 2);

        for (int i : res) System.out.println(i);

        nums = new int[] {1};
        res = obj.topKFrequent(nums, 1);

        for (int i : res) System.out.println(i);
    }

    /*
    int largest(int[] arr) {
         int i;

         // Initialize maximum element
         int max = arr[0];

         // Traverse array elements from second and
         // compare every element with current max
         for (i = 1; i < arr.length; i++)
             if (arr[i] > max)
                 max = arr[i];

         return max;
    }

    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;

        int[] temp = nums;
        Arrays.sort(temp);

        int[] freq = new int[temp[temp.length - 1] + 1];
        for (int i : nums) freq[i]++;

        int maxFreq = largest(freq);

        List<List<Integer>> bucket = new ArrayList<>();

        for (int i = 0; i <= maxFreq - 1; ++i) bucket.add(new ArrayList<Integer>());

        for (int i = 0; i < freq.length; ++i) {
            if (freq[i] > 0) bucket.get(freq[i]).add(i);
        }

        int[] res = new int[k];
        int j = maxFreq;
        while (k > 0 && j > 0) {
            List<Integer> numbers = bucket.get(j--);

            for (int number : numbers) {
                res[(k--) - 1] = number;

                if (k == 0) break;
            }
        }

        return res;
    }
    */

    public int[] topKFrequentIV(int[] nums, int k) {
        if (nums.length == k) return nums;

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));

        for (int n : map.keySet()) {
            pq.offer(n);


            if (pq.size() > k) pq.poll();
        }

        int[] res = new int[k];
        for (int i = 0; i < k; ++i)
            res[i] = pq.poll();

        return res;
    }

    /*
    Time complexity : O(Nlogk) if k < N and O(N) in the particular case of N = kN=k. That ensures time complexity to be better than \mathcal{O}(N \log N)O(NlogN).

    Space complexity : O(N+k) to store the hash map with not more NN elements and a heap with kk elements.
    */

    // bucket sort
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;

        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();

        for (int i : nums) freq.put(i, freq.getOrDefault(i, 0) + 1);

        List<List<Integer>> bucket = new ArrayList<>();

        int maximumFrequency = Collections.max(freq.values());

        for (int i = 0; i <= maximumFrequency; ++i) bucket.add(new ArrayList<Integer>());

        for (int key : freq.keySet()) {
            int frequency = freq.get(key);
            bucket.get(frequency).add(key);
        }

        int[] res = new int[k];

        int index = maximumFrequency;

        while(k > 0 && index > 0) {
            for (int j : bucket.get(index--)) {
                res[--k] = j;

                if (k == 0) break;
            }
        }

        return res;
    }

    // bucket sort (copied)
    /*
    public int[] topKFrequentII(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int n : nums)
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);

        List<Integer>[] bucket = new ArrayList<Integer>[nums.length + 1];

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null)
                bucket[frequency] = new ArrayList<>();

            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null)
                res.addAll(bucket[pos]);
        }

        int[] rel = new int[res.size()];

        for (int i = 0; i < rel.length; i++)
            rel[i] = res.get(i).intValue();

        return rel;
    }
    */

    // quick sort
    public int[] topKFrequentIII(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int[] result = new int[map.size()];
        int index = 0;
        for (int i : map.keySet()) {
            result[index] = i;
            index++;
        }
        quickSort(result, result.length - k, 0, result.length - 1, map);
        return Arrays.copyOfRange(result, result.length - k, result.length);
    }

    private void quickSort(int[] nums, int k, int start, int end, Map<Integer, Integer> map) {
        int left = start;
        int right = end;
        int pivot = map.get(nums[start]);
        while (left < right) {
            while (map.get(nums[right]) >= pivot && left < right) {
                right--;
            }
            if (left < right) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
            }
            while (map.get(nums[left]) < pivot && left < right) {
                left++;
            }
            if (left < right) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
            }
        }

        if (left == k) {
            return;
        } else if (left < k) {
            quickSort(nums, k, left + 1, end, map);
        } else {
            quickSort(nums, k, start, left - 1, map);
        }
    }

    /*
    public int[] topKFrequentBucket(int[] nums, int k) {
        List<Integer>[] bucket = new ArrayList<>[nums.length + 1];

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums)
            map.put(i, map.getOrDefault(i, 0) + 1);

        for (int i : map.keySet()) {
            int count = map.get(i);

            if (bucket[count] == null)
                bucket[count] = new ArrayList<>();

            bucket[count].add(i);
        }

        int t = k - 1;

        int res = new int[k];

        for(int pos = bucket.length - 1; pos >= 0 && t >= 0; --pos) {
            if (bucket[pos] != null)
                for (int j : bucket.get(size - 1))
                    if (t >= 0)
                        res[t--] = j;
        }

        return res;
    }
    */

    public int[] topKFrequentBucketI(int[] nums, int k) {
        List<List<Integer>> bucket = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums)
            map.put(i, map.getOrDefault(i, 0) + 1);

        for (int i : map.keySet()) {
            int count = map.get(i);

            if (bucket.get(count) == null)
                bucket.get(count) = new ArrayList<>();

            bucket.get(count).add(i);
        }

        int t = k - 1;

        int res = new int[k];

        for(int pos = bucket.size() - 1; pos >= 0 && t >= 0; --pos) {
            if (bucket.get(pos) != null)
                for (int j : bucket.get(pos))
                    if (t >= 0)
                        res[t--] = j;
        }

        return res;
    }
}