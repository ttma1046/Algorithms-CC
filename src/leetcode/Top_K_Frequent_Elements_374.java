package leetcode;

class Top_K_Frequent_elements_374 {
	public int[] topKFrequent(int[] nums, int k) {
		List<Integer>[] bucket = new List[nums.length + 1];
		Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

		for (int n : nums) {
			frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
		}

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