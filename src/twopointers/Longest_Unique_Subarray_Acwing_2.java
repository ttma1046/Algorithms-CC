package twopointers;


class Longest_Unique_Subarray_Acwing_2 {
	int[] s = new int[100010];


    int findLongestSub(int[] arr) {
        int n = arr.lenght;
        int max = 0;
        for (int i = 0, j = 0; i < n; i++) {
        	s[arr[i]]++;

            while(s[arr[i]] > 1) {
            	s[arr[j]]--;
            	j++;                
            }

            max = Math.max(max, i - j + 1);
        }

        return max;
    }

    int findLongestSubII(int[] arr) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j <= i; j++)
                if (check(j, i))
                    res = Math.max(res, i - j  + 1);
    }
    

    public static void main(String[] args) {
        Longest_Unique_Subarray_Acwing_2 obj = new Longest_Unique_Subarray_Acwing_2();
    }

}