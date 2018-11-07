package string;

public class RepeatedString {
    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        if (s == null || s.length() == 0 || n <= 0) {
            return 0;
        }

        long repeatedTimes = n / s.length();

        long result = repeatedTimes * returnHowManyA(s);

        if (n % s.length() > 0) {
            String lastString = s.substring(0, (int)(n % s.length()));
            result += returnHowManyA(lastString);
        }

        return result;
    }

    private static long returnHowManyA(String s) {
        return s.length() - s.replaceAll("a","").length();
    }

    static long repeatedStringII(String s, long n) {
        if (s == null || s.length() == 0 || n <= 0) {
            return 0;
        }

        int A = 0;
        int B = 0;

        for (int i = 0;i < s.length();i++) {
            if (s.charAt(i) == 'a') {
                A++;
            }

            if (i < n % s.length() && s.charAt(i) == 'a') {
                B++;
            }
        }

        return A * (n / s.length()) + B;
    }

    public static void main(String[] args) {
        System.out.println(repeatedStringII("aba", 10));
    }
}
