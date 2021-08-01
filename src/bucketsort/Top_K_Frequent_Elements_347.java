package bucketsort;
import java.u
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
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;

        int[] temp = nums;
        Collections.sort(temp, (a, b) -> b - a);

        int[] freq = new int[temp[0]];
        for (int i : nums) freq[i]++;

        int maxFreq = Collections.max(freq);

        List<Integer>[] bucket = new ArrayList[maxFreq + 1];

        for (int i = 0; i < freq.length; ++i) {
            if (freq[i] > 0) bucket[freq[i]].add(i);
        }

        int[] res = new int[k];
        int j = maxFreq;
        while (k > 0 && j > 0) {
            List<Integer> numbers = bucket[j--];

            for (int number : numbers) {
                res[(k--) - 1] = b;

                if (k == 0) break;
            }
        }

        return res;
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        List<Integer>[] bucket = new List[nums.length + 1];

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }

        int[] rel = new int[res.size()];

        for (int i = 0; i < rel.length; i++) {
            rel[i] = res.get(i).intValue();
        }
        return rel;
    }

    public int[] topKFrequent(int[] nums, int k) {
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

    public void quickSort(int[] nums, int k, int start, int end, Map<Integer, Integer> map) {
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
}