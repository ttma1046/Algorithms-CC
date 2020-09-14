package hashtable;

public class VerifyinganAlianDictionary_953 {
    int[] orderList = new int[26];

    public boolean isAlienSorted(String[] words, String order) {
        if (words.length <= 0 || order == null) {
            return false;
        }


        for (int i = 0; i < order.length(); i++) {
            orderList[order.charAt(i) - 'a'] = i;
        }

        for (int i = 1; i < words.length; i++) {
            if (bigger(words[i - 1], words[i])) {
                return false;
            }
        }

        return true;
    }

    boolean bigger(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        for (int i = 0; i < n && i < m; ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return orderList[s1.charAt(i) - 'a'] > orderList[s2.charAt(i) - 'a'];
            }
        }
        return n > m;
    }

    public static void main(String[] args) {
        String[] words = new String[] {"hello", "leetcode"};
        System.out.println(new VerifyinganAlianDictionary_953().isAlienSorted(words, "hlabcdefgijkmnopqrstuvwxyz"));
    }
}
