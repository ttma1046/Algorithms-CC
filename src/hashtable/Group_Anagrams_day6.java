package hashtable;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Group_Anagrams_day6 {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length <= 0) {
            return null;
        }


        HashMap<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();


        for(String s : strs) {
            String sortedString = sortString(s);
            ArrayList<String> strings = new ArrayList<String>();
            if (myMap.containsKey(sortedString)) {
                strings = myMap.get(sortedString);

            }
            strings.add(s);
            myMap.put(sortedString, strings);
        }

        List<List<String>> result = new ArrayList<List<String>>();

        for(ArrayList<String> list : myMap.values()) {
            result.add(list);
        }

        return result;
    }

    private String sortString(String inputString) {
        // convert input string to char array
        char tempArray[] = inputString.toCharArray();

        // sort tempArray
        Arrays.sort(tempArray);

        // return new sorted string
        return new String(tempArray);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        int count = 0;
        Map<String, Integer> map = new HashMap<>();

        List<List<String>> l1 = new ArrayList<>();
        
        for(int i = 0; i < strs.length; i++) {
            char arr[] = strs[i].toCharArray();
            Arrays.sort(arr);
            String s = new String(arr);

            if(map.containsKey(s)) {
                int j = map.get(s);
                l1.get(j).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(strs[i]);
                l1.add(temp);
                map.put(s, count++);
            }
        }
        return l1;
    }

    public static void main(String[] args) {
        String[] test = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        var result = new Group_Anagrams_day6().groupAnagrams(test);

        for(List<String> item : result) {
            for(String re : item) {
                System.out.println(re);
            }
        }
    }


}