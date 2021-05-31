package string;

public class ZigZag_6 {
    /*
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number
     * of rows like this: (you may want to display this pattern in a fixed font for
      better legibility)
     *
     *  P   A   H   N
     *  A P L S I I G
     *  Y   I   R
     *
     * And then read line by line: "PAHNAPLSIIGYIR"
     *
     * Write the code that will take a string and make this conversion given a
     * number of rows:
     *
     * string convert(string s, int numRows);
     *
     * Example 1:
     *
     * Input: s = "PAYPALISHIRING", numRows = 3
     * Output: "PAHNAPLSIIGYIR"
     *
     * Example 2:
     *
     * Input: s = "PAYPALISHIRING", numRows = 4
     * Output: "PINALSIGYAHRPI"
     *
     * Explanation:
     *
     *  P     I     N
     *  A   L S   I G
     *  Y A   H R
     *  P     I
     *
     * Example 3:
     *
     * Input: s = "A", numRows = 1
     * Output: "A"
     *
     * /*n=numRows
     * Δ=2n-2    1                           2n-1                         4n-3
     * Δ=        2                     2n-2  2n                    4n-4   4n-2
     * Δ=        3               2n-3        2n+1              4n-5       .
     * Δ=        .           .               .               .            .
     * Δ=        .       n+2                 .           3n               .
     * Δ=        n-1 n+1                     3n-3    3n-1                 5n-5
     * Δ=2n-2    n                           3n-2                         5n-4
     * that's the zigzag pattern the question asked!
     * Be careful with nR=1 && nR=2
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c: s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }
}