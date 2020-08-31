package dp;

class Decode_Ways_91 {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int length = s.length();

        int[] dp = new int[length + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i < length + 1; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            // Check if successful two digit decode is possible.
            int twoDigit = Integer.valueOf(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[length];
    }

    public static void main(String[] args) {
        System.out.println(new Decode_Ways_91().numDecodings("12"));
        System.out.println(new Decode_Ways_91().numDecodings("226"));
        System.out.println(new Decode_Ways_91().numDecodings("2226"));

    }
}