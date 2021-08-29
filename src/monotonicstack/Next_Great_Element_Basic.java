package monotonicstack;

// 输入一个数组 nums = [2, 1, 2, 4, 3],返回数组[ 4, 2, 4, -1, -1 ].
class Next_Great_Element_Basic {
    int[] nextGreaterElement(int[] nums) {
        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[j] > nums[i]) {
                    res[i] = nums[j];
                    break;
                }
            }
            if (res[i] == 0) res[i] = -1;
        }
        res[res.length - 1] = -1;
        return res;
    }


    int[] nextGreaterElementII(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = nums.length - 1; i >= 0; --i) {
        	while(stack.size() > 0 && stack.peek() <= nums[i]) stack.pop();
        	res[i] = stack.isEmpty() ? -1; stack.peek();
        	stack.push(nums[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        Next_Great_Element_Basic obj = new Next_Great_Element_Basic();
        int[] input = new int[] { 2, 1, 2, 4, 3 };
        int[] res = obj.nextGreaterElement(input);

        for (int i : res) System.out.println(i);
    }
}



































/*
		Stack<Integer> stack = new Stack<>();

		for (int i = nums.length - 1; i >= 0; i--) {
			while (!stack.isEmpty() && nums[i] >= stack.peek()) stack.pop(); // monotonic stack
			res[i] = stack.isEmpty（）？ -1 ： stack.peek();
			stack.push(nums[i]);
		}

		return res;
		*/