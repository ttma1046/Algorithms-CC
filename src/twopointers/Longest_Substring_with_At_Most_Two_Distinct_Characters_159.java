package twopointers;

/*
Longest SubString with At Most Two Distinct Characters

Given a strnig s, find the length substring t the contains at most 2 distinct characters.

Example 1:
    Input: "eceba"
    Output: 3
    Explanation: t is "ece" which its length is 3.

Example 2:
    Input: "ccaabbb"
    Output: 5
*/

class Longest_Substring_with_At_Most_Two_Distinct_Characters_159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length(), 
        int i = 0;
        int j = 0;
        int res = 0;
        int k = 0;

        int[] map = new int[94];

        for (j = 0; j < n; ++j) {
            if (map[s.charAt(j) - ' ']++ == 0) k++;

            while (k > 2) {
                if (--map[s.charAt(i) - ' '] == 0) --k;
                i++;
            }

            if (j - i + 1 > res) res = j - i + 1;            
        }

        return res;
    }

    public List<Integer> slidingWindowTemplateByHarryChaoyangHe(String s, String t) {
        //init a collection or int value to save the result according the question.
        List<Integer> result = new LinkedList<>();
        if(t.length() > s.length()) return result;

        //create a hashmap to save the Characters of the target substring.
        //(K, V) = (Character, Frequence of the Characters)
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        //maintain a counter to check whether match the target string.
        int counter = map.size();//must be the map size, NOT the string size because the char may be duplicate.

        //Two Pointers: begin - left pointer of the window; end - right pointer of the window
        int start = 0, end = 0;

        //the length of the substring which match the target string.
        int len = Integer.MAX_VALUE;

        //loop at the begining of the source string
        while(end < s.length()) {
            char c = s.charAt(end);//get a character

            if(map.containsKey(c)) {
                map.put(c, map.get(c) - 1); // plus or minus one
                if(map.get(c) == 0) counter--; // modify the counter according the requirement(different condition).
            }
            end++;

            //increase start pointer to make it invalid/valid again
            while(counter == 0) { // counter condition. different question may have different condition
                char tempc = s.charAt(begin);//***be careful here: choose the char at start pointer, NOT the end pointer
                if(map.containsKey(tempc)) {
                    map.put(tempc, map.get(tempc) + 1);//plus or minus one
                    if(map.get(tempc) > 0) counter++;//modify the counter according the requirement(different condition).
                }

                /* save / update(min/max) the result if find a target*/
                // result collections or result int value

                start++;
            }
        }
        return result;
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int counter = 0;
        int len = 0;
        while(end < s.length()) {
            char endC = s.charAt(end);
            map.put(endC, map.getOrDefault(endC, 0) + 1);
            if(map.get(endC) == 1) counter++; // new char
            
            end++;
            
            while(counter > 2) {
                char startC = s.charAt(begin);
                map.put(startC, map.get(startC) - 1);
                if(map.get(startC) == 0) counter--;

                begin++;
            }

            len = Math.max(len, end - begin);
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(new Longest_Substring_with_At_Most_Two_Distinct_Characters_159().lengthOfLongestSubstringTwoDistinct("eceba"));
        System.out.println(new Longest_Substring_with_At_Most_Two_Distinct_Characters_159().lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }
}