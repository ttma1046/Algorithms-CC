package twopointers;

class Longest_Substring_with_At_Most_Two_Distinct_Characters_159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length(), i = 0, j = 0, res = 0, k = 0;
        int[] map = new int[94];

        for (j = 0; j < n; ++j) {
            if (map[s.charAt(j) - ' ']++ == 0) k++;

            while (k > 2) {
                --map[s.charAt(i) - ' '];
                if (map[s.charAt(i) - ' '] == 0) --k;
                i++;
            }

            if (j - i + 1 > res) {
                res = j - i + 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Longest_Substring_with_At_Most_Two_Distinct_Characters_159().lengthOfLongestSubstringTwoDistinct("eceba"));
        System.out.println(new Longest_Substring_with_At_Most_Two_Distinct_Characters_159().lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }
}