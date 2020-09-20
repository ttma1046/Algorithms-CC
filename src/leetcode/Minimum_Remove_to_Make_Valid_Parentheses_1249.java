package leetcode;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;

/*

Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.


Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"
Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
Example 4:

Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"


Constraints:

1 <= s.length <= 10^5
s[i] is one of  '(' , ')' and lowercase English letters.
*/

class Minimum_Remove_to_Make_Valid_Parentheses_1249 {
  public String minRemoveToMakeValidStackMy(String s) {
    if (s == null || s.length() == 0 || s == "") {
      return s;
    }

    Set<Integer> mySet = new HashSet<Integer>();
    Stack<Integer> myStack = new Stack<Integer>();

    // O(n)
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(') {
        myStack.push(i);
      } else if (c == ')') {
        if (myStack.size() > 0) {
          myStack.pop();
        } else {
          mySet.add(i);
        }
      }
    }

    int size = myStack.size();
    // O(n)
    while (size-- > 0) {
      mySet.add(myStack.pop());
    }

    StringBuilder sb = new StringBuilder();

    // O(n)
    for (int i = 0; i < s.length(); i++) {
      if (!mySet.contains(i)) {
        sb.append(s.charAt(i));
      }
    }

    // O(n)
    return sb.toString();
  }

  private StringBuilder removeInvalidClosing(String k, char start, char end) {
    StringBuilder sb = new StringBuilder();

    int balance = 0;
    for (int index = 0; index < k.length(); index++) {
      char c = k.charAt(index);
      if (c == start) {
        balance++;
      } else if (c == end) {
        if (balance == 0) continue;
        balance--;
      }
      sb.append(c);
    }

    return sb;
  }

  public String minRemoveToMakeValidMySecond(String s) {
    StringBuilder result = removeInvalidClosing(s, '(', ')');
    result = removeInvalidClosing(result.reverse().toString(), ')', '(');
    return result.reverse().toString();
  }

  public String minRemoveToMakeValidMyThird(String s) {
    StringBuilder sb = new StringBuilder();

    int balance = 0, open = 0;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == '(') {
        open++;
        balance++;
      } else if (c == ')') {
        if (balance == 0) continue;
        balance--;
      }

      sb.append(c);
    }

    int openToKeep = open - balance;

    String tempStr = sb.toString();
    sb = new StringBuilder();
    for (int i = tempStr.length() - 1; i >= 0 ; i--) {
      char temp = tempStr.charAt(i);

      if (temp == '(' && balance-- > 0) {
        if (openToKeep == 0) {
          continue;
        }
        openToKeep--;
      }
      sb.append(temp);
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(new Minimum_Remove_to_Make_Valid_Parentheses_1249().minRemoveToMakeValidMyThird("l(e)))et((co)d(e"));
  }

  public String minRemoveToMakeValid(String s) {
    int left = 0;
    char[] letters = s.toCharArray();
    for (int i = 0; i < letters.length; i++) {
      if (letters[i] == '(') {
        left++;
      } else if (letters[i] == ')') {
        if (left > 0) {
          left--;
        } else {
          letters[i] = ' ';
        }
      }
    }
    for (int i = letters.length - 1; i >= 0; i--) {
      if (left > 0 && letters[i] == '(') {
        letters[i] = ' ';
        left--;
      }
    }
    int write = 0;
    for (int i = 0; i < letters.length; i++) {
      if (letters[i] != ' ') {
        letters[write++] = letters[i];
      }
    }
    return new String(letters, 0, write);
  }
}
