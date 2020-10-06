package string;

class Sol {
    public String addBinary(String a, String b) {
        char[] aChars = a.toCharArray(), bChars = b.toCharArray();
        int i = aChars.length, j = bChars.length, carry = 0;

        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int sum = carry;
           if (i >= 0) sum += aChars[i--] - '0';
           if (j >= 0) sum += bChars[j--] - '0';

           sb.append(sum % 2);
           carry = sum / 2;
        }

        if (carry != 0) sb.append(carry);

        return sb.reverse().toString();
    }
}