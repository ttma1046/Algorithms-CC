package string;

public class RepeatedSubstringPattern_459 {
    public boolean repeatedSubstringPattern1(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }

        int length = s.length();
        for (int subLen = length / 2; subLen >= 1; subLen--) {
            if (length % subLen == 0 && s.charAt(subLen - 1) == s.charAt(length - 1)) {
                String subStr = s.substring(0, subLen);
                int count = length / subLen;
                StringBuilder builder = new StringBuilder();
                while (count-- > 0) {
                    builder.append(subStr);
                }
                if (builder.toString().equals(s)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean repeatedSubstringPattern2(String s) {
        if(s == null || s.length() <= 1) {
            return false;
        }
        int len = s.length();
        for (int i = len / 2 - 1; i >= 0; i--) {
            if (len % (i + 1) != 0) {
                continue;
            }
            String pattern = s.substring(0, i + 1);
            int offset = 0;
            while(offset < len && s.startsWith(pattern, offset)) {
                offset += i + 1;
            }
            if(offset == len) {
                return true;
            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern3(String s) {
        String ori;
        int n = s.length();
        int k = 0;
        for(int i = n / 2; i >= 1; i--) {
            if(n % i == 0) {
                ori = s.substring(0, i);
                k = i;
                while(k < s.length() && s.startsWith(ori, k)) {
                    k += i;
                }
                if(k == s.length()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern4(String s) {
        int len = s.length();
        if(len == 0) {
            return false;
        }
        for(int i = 1; i <= len / 2; i++) {
            if(len % i == 0) {
                String sub = s.substring(0, i);
                int offset = 0;
                while(offset < len && s.startsWith(sub, offset)) {
                    offset += i;
                }
                if(offset == len) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern(String s) {
        if(s.length() < 2) {
            return false;
        }

        String s2 = s.substring(1, s.length()) + s.substring(0, s.length() - 1);
        return s2.indexOf(s) >= 0;
    }

    public boolean repeatedSubstringPatternKMP(String str) {
        int[] prefix = kmp(str);
        int len = prefix[str.length() - 1];
        int n = str.length();
        return (len > 0 && n % (n - len) == 0);
    }

    private int[] kmp(String s) {
        int len = s.length();
        int[] res = new int[len];
        char[] ch = s.toCharArray();
        int i = 0, j = 1;
        res[0] = 0;
        while(i < ch.length && j < ch.length) {
            if(ch[j] == ch[i]) {
                res[j] = i + 1;
                i++;
                j++;
            } else {
                if(i == 0) {
                    res[j] = 0;
                    j++;
                } else {
                    i = res[i - 1];
                }
            }
        }
        return res;
    }

    public boolean repeatedSubstringPatternKMPII(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int j = 0, i = 1;

        while (i < n) {
            if (s.charAt(i) == s.charAt(j)) dp[i++] = ++j;
            else if (j != 0) j = dp[j - 1];
            else i++;
        }
        
        int end = n - 1;
        return dp[end] != 0 && (dp[end] % (n - dp[end])) == 0;
    }

    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();

        int[] kmp = new int[n];

        int i = 1, j = 0;

        char[] chars = s.toCharArray();

        while(i < n) {
            if (chars[i] == chars[j]) kmp[i++] = ++j;
            else if (j != 0) j = kmp[j - 1]; 
            else i++;
        }

        int end = n - 1;
        return kmp[end] != 0 && (kmp[end] % (n - kmp[end])) == 0;
    }



























    public static void main(String[] args) {
        RepeatedSubstringPattern_459 obj = new RepeatedSubstringPattern_459();
        System.out.println(obj.repeatedSubstringPattern("abcdeabcdeabcdeabcde"));

        System.out.println(obj.repeatedSubstringPattern("abab"));

        System.out.println(obj.repeatedSubstringPattern("aba"));
    }
}
