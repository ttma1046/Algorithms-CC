package string;

/*
A valid number can be split up into these components (in order):

	A decimal number or an integer.
	(Optional) An 'e' or 'E', followed by an integer.

A decimal number can be split up into these components (in order):

	(Optional) A sign character (either '+' or '-').
	One of the following formats:
	One or more digits, followed by a dot '.'.
	One or more digits, followed by a dot '.', followed by one or more digits.
	A dot '.', followed by one or more digits.

An integer can be split up into these components (in order):

	(Optional) A sign character (either '+' or '-').
	One or more digits.

for (char c: s.toCharArray()) {
	optinal if (+ or -) continue; -> if digits continue end; -> if . continue end; -> if digits continue end
	optinal	if (+ or -) continue; -> if . continue; -> if digits continue end
	optinal	if (+ or -) continue; -> if digits continue end

	-> if e or E continue -> optinal if (+ or -) continue; -> if digits continue end
}

1. when + or -, return false if not first or after exponent
2. when ., return false if already dot
3. when exponent, return false if already exponent or no digital yet
4. when number, set flag

For example, all the following are valid numbers: [
	"2",
	"0089",
	"-0.1",
	"+3.14",
	"4.",
	"-.9",
	"2e10",
	"-90E3",
	"3e+7",
	"+6e-1",
	"53.5e93",
	"-123.456e789"
], while the following are not valid numbers: [
	"abc",
	"1a",
	"1e",
	"e3",
	"99e2.5",
	"--6",
	"-+3",
	"95a54e53"
].

Given a string s, return true if s is a valid number.

Example 1:

Input: s = "0"
Output: true

Example 2:

Input: s = "e"
Output: false

Example 3:

Input: s = "."
Output: false

Example 4:

Input: s = ".1"
Output: true

Constraints:

1 <= s.length <= 20
s consists of only English letters (both uppercase and lowercase), digits (0-9), plus '+', minus '-', or dot '.'.
*/
class Valid_Number_65 {
    /*
    1. when + or -, return false if not first or after exponent
    2. when ., return false if already dot or after exponent
    3. when exponent, return false if already exponent or no digital yet
    4. when number, set flag
    */
    public boolean isNumber(String s) {
        boolean seenDigit = false;
        boolean seenExponent = false;
        boolean seenDot = false;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                seenDigit = true;
            } else if (c == '+' || c == '-') {
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') return false;
            } else if (c == 'e' || c == 'E') {
                if (seenExponent || !seenDigit) return false;
                seenExponent = true;
                seenDigit = false;
            } else if (c == '.') {
                if (seenDot || seenExponent) return false;
                seenDot = true;
            } else {
                return false;
            }
        }

        return seenDigit;
    }

    /*
    Time complexity: O(N), where N is the length of s.

    We simply iterate over the input once.

    The number of operations we perform for each character in the input is independent of the length of the string,

    and therefore only requires constant time. This results in N * O(1) = O(N) N * O(1) = O(N).

    Space complexity: O(1).

    Regardless of the input size, we only store 3 variables, seenDigit, seenExponent, and seenDot.
    */


    // This is the DFA we have designed above
    private final List<Map<String, Integer>> dfa = List.of(
                Map.of("digit", 1, "sign", 2, "dot", 3),
                Map.of("digit", 1, "dot", 4, "exponent", 5),
                Map.of("digit", 1, "dot", 3),
                Map.of("digit", 4),
                Map.of("digit", 4, "exponent", 5),
                Map.of("sign", 6, "digit", 7),
                Map.of("digit", 7),
                Map.of("digit", 7)
            );

    // These are all of the valid finishing states for our DFA.
    private final Set<Integer> validFinalStates = Set.of(1, 4, 7);

    public boolean isNumber(String s) {
        int currentState = 0;
        String group = "";

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (Character.isDigit(curr)) {
                group = "digit";
            } else if (curr == '+' || curr == '-') {
                group = "sign";
            } else if (curr == 'e' || curr == 'E') {
                group = "exponent";
            } else if (curr == '.') {
                group = "dot";
            } else {
                return false;
            }

            if (!dfa.get(currentState).containsKey(group)) {
                return false;
            }

            currentState = dfa.get(currentState).get(group);
        }

        return validFinalStates.contains(currentState);
    }

    /*
    Time complexity: O(N), where N is the length of s.

    We simply iterate through the input once. 

    The number of operations we perform for each character in the input is independent of the length of the string, and therefore each operation requires constant time. So we get N * O(1) = O(N).

    Space complexity: O(1).

    We will construct the same DFA regardless of the input size.
    */

    public static void main(String[] args) {
        Valid_Number_65 obj = new Valid_Number_65();
    }
}