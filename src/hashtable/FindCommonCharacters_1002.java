package hashtable;

import java.util.*;

public class FindCommonCharacters_1002 {
    public List<String> commonChars(String[] A) {
        if (A.length <= 0) {
            return null;
        }

        int[] allCount = new int[26];
        for (int j = 0; j < A[0].length(); j++) {
            allCount[A[0].charAt(j) - 'a']++;
        }

        for (int i = 1; i < A.length; i++) {
            int[] count = new int[26];
            for (int j = 0; j < A[i].length(); j++) {
                count[A[i].charAt(j) - 'a']++;
            }

            for (int z = 0; z < 26; z++) {
                if (allCount[z] > 0 && allCount[z] > count[z]) {
                    allCount[z] = count[z];
                }
            }
        }
        List<String> result = new ArrayList<>();
        for(int i = 0; i < allCount.length; i++){
            while(allCount[i]-- > 0){
                result.add(Character.toString((char)(i + 'a')));
            }
        }
        return result;
    }

    public List<String> commonCharsII(String[] A) {
        List<String> list = new ArrayList<>();
        int arr[] = new int[26];
        Arrays.fill(arr,Integer.MAX_VALUE);

        for(int i = 0; i < A.length; i++){
            int tmp[] = new int[26];
            char[] charArr = A[i].toCharArray();
            for(int j = 0; j < charArr.length; j++) tmp[charArr[j] - 'a']++;

            for(int k = 0; k < 26; k++){
                arr[k] = Math.min(tmp[k], arr[k]);
            }
        }

        for(int i = 0; i < arr.length; i++){
            while(arr[i] > 0){
                list.add(Character.toString((char)(i + 'a')));
                arr[i]--;
            }

        }
        return list;
    }

    public static void main(String[] args) {
        List<String> result = new FindCommonCharacters_1002().commonChars(new String[] { "bella", "label", "roller"});
        for (String k: result) {
            System.out.println(k);
        }

        result = new FindCommonCharacters_1002().commonChars(new String[] { "cool", "lock", "cook"});
        for (String k: result) {
            System.out.println(k);
        }
    }
}
