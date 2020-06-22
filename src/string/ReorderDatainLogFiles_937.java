package string;
import java.util.Arrays;
import java.util.Comparator;
import java.lang.Character;

/*
You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.

It is guaranteed that each log has at least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.

The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.
The digit-logs should be put in their original order.

Return the final order of the logs.

Example 1:

Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]

Constraints:

0 <= logs.length <= 100
3 <= logs[i].length <= 100
logs[i] is guaranteed to have an identifier, and a word after the identifier.
*/
class ReorderDatainLogFiles_937 {
    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> comparer = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String[] split1 = s1.split(" ", 2);
                String[] split2 = s2.split(" ", 2);

                boolean isDigitOne = Character.isDigit(split1[1].charAt(0));
                boolean isDigitTwo = Character.isDigit(split2[1].charAt(0));

                if (!isDigitOne && !isDigitTwo) {
                    int comp = split1[1].compareTo(split2[1]);
                    if (comp != 0) return comp;
                    return split1[0].compareTo(split2[0]);
                }

                return isDigitOne ? (isDigitTwo ? 0 : 1) : -1;
            }
        };

        Arrays.sort(logs, comparer);
        return logs;
    }

    Comparator<String> comparer = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            int s1SpaceIndex = s1.indexOf(" ");
            int s2SpaceIndex = s2.indexOf(" ");
            char c1 = s1.charAt(s1SpaceIndex + 1);
            char c2 = s2.charAt(s2SpaceIndex + 1);

            if (c1 <= '9') {
                if (c2 <= '9') {
                    return 0;
                }
                return 1;
            }
            if (c2 <= '9') return -1;

            int preCompute = s1.substring(s1SpaceIndex + 1).compareTo(s2.substring(s2SpaceIndex + 1));
            if (preCompute == 0) return s1.substring(0, s1SpaceIndex).compareTo(s2.substring(0, s2SpaceIndex));
            return preCompute;
        }
    }

    public static void main(String[] args) {
        String[] result = new ReorderDatainLogFiles_937().reorderLogFiles(new String[] {
                    "dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"
                });

        for(String item : result) {
            System.out.println(item);
        }
    }
}