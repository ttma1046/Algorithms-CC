package binarysearch;

public class ClassicBinarySearch {
    public int FindPosition(int[] nums, int target)
    {
        if (nums == null || nums.length == 0)
        {
            return -1;
        }

        int start = 0, end = nums.length - 1;

        // start < end
        // start <= end
        // start + 1 < end // 相邻就要退出循环， start = 1， end = 2
        while (start + 1 < end) {
            // start = 2^31 - 1, end = 2^31 - 2
            // int = [-2^31, 2^31 - 1]
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)
            {
                return mid;
            }

            if (nums[mid] < target)
            {
                start = mid;
                // start = mid + 1;
            } else {
                end = mid;
                // end = mid - 1;
            }
        }

        if (nums[start] == target)
        {
            return start;
        }

        if (nums[end] == target)
        {
            return end;
        }

        return -1;
    }
}
