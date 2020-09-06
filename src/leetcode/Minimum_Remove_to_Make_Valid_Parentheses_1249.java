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


/*
Intuition
To make the string valid with minimum removals, we need to get rid of all parentheses that do not have a matching pair.

Push char index into the stack when we see '('.

Pop from the stack when we see ')'.

If the stack is empty, then we have ')' without the pair, and it needs to be removed.
In the end, the stack will contain indexes of '(' without the pair, if any. We need to remove all of them too.

Update: check out the new approach 2 that collects indexes of all mismatched parentheses, and removes them right-to-left.

Approach 1: Stack and Placeholder
We mark removed parentheses with '*', and erase all of them in the end.

Java
*/

class Minimum_Remove_to_Make_Valid_Parentheses_1249 {
  public String minRemoveToMakeValid(String s) {
    if (s == null || s == "") {
      return null;
    }

    Set<Integer> deleteCharSet = new HashSet<Integer>();
    Stack<Integer> myStack = new Stack<Integer>();

    for (int i = 0; i < s.length(); i++) {
      char temp = s.charAt(i);

      if (temp == '(') {
        myStack.push(i);
      } else if (temp == ')') {
        if (myStack.isEmpty()) {
          deleteCharSet.add(i);
        } else {
          myStack.pop();
        }
      }
    }

    int size = myStack.size();

    while (size-- > 0) {
      deleteCharSet.add(myStack.pop());
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (!deleteCharSet.contains(i)) {
        sb.append(s.charAt(i));
      }
    }

    return sb.toString();
  }

  /*
  Complexity Analysis

  Time complexity : O(n)O(n), where nn is the length of the input string.

  There are 3 loops we need to analyze. We also need to check carefully for any library functions that are not constant time.

  The first loop iterates over the string, and for each character, either does nothing, pushes to a stack or adds to a set. Pushing to a stack and adding to a set are both O(1)O(1). Because we are processing each character with an O(1)O(1) operation, this overall loop is O(n)O(n).

  The second loop (hidden in library function calls for the Python code) pops each item from the stack and adds it to the set. Again, popping items from a stack is O(1)O(1), and there are at most nn characters on the stack, and so it too is O(n)O(n).

  The third loop iterates over the string again, and puts characters into a StringBuilder/ list. Checking if an item is in a set and appending to the end of a String Builder or list is O(1)O(1). Again, this is O(n)O(n) overall.

  The StringBuilder.toString() method is O(n)O(n), and so is the "".join(...). So again, this operation is O(n)O(n).

  So this gives us O(4n)O(4n), and we drop the 44 because it is a constant.

  Space complexity : O(n)O(n), where nn is the length of the input string.

  We are using a stack, set, and string builder, each of which could have up to n characters in them, and so require up to O(n)O(n) space.

  When checking your own implementation, watch out for any O(n)O(n) library calls inside loops, as these would make your solution O(n^2)O(n
  2
  ).
  */

  public String minRemoveToMakeValidMy(String s) {
    if (s == null || s == "") {
      return null;
    }

    int openParenthese = 0;
    int balance = 0;

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      char temp = s.charAt(i);

      if (temp == '(') {
        openParenthese++;
        balance++;
      } else if (temp == ')') {
        if (balance == 0) {
          continue;
        }
        balance--;
      }
      sb.append(temp);
    }

    int openToDelete = openParenthese - balance;
    String tempStr = sb.toString();
    sb = new StringBuilder();
    for (int i = 0; i < tempStr.length(); i++) {
      char temp = tempStr.charAt(i);

      /*
      if (temp == '(') {
        openToDelete--;
        if (openToDelete < 0) {
          continue;
        }
      }
      sb.append(temp);
      */

      if (temp == '(') {
        if (openToDelete == 0) {
          continue;
        }
        openToDelete--;
      }
      sb.append(temp);        
    }

    return sb.toString();
  }

  /*
  Complexity Analysis

  Time complexity : O(n)O(n), where nn is the length of the input string.

  Same as the above approaches. We have 2 loops that are looping through up to nn characters, doing an O(1)O(1) operation on each. We also have some O(n)O(n) library function calls outside of the loops.

  Space complexity : O(n)O(n), where nn is the length of the input string.

  Like the previous approach, the string building requires O(n)O(n) space.

  When checking your own implementation, watch out for any O(n)O(n) library functions inside loops, as these would make your solution O(n^2)O(n
  2
  ).
  */

  public static void main(String[] args) {
    System.out.println(new Minimum_Remove_to_Make_Valid_Parentheses_1249().minRemoveToMakeValid("lee(t(c)o)de)"));
  }

  /*
  public String minRemoveToMakeValidI(String s) {
    StringBuilder sb = new StringBuilder(s);
    Stack<Integer> st = new Stack<>();
    for (int i = 0; i < sb.length(); ++i) {
      if (sb.charAt(i) == '(') st.add(i);
      if (sb.charAt(i) == ')') {
        if (!st.empty()) st.pop();
        else sb.setCharAt(i, '*');
      }
    }
    while (!st.empty())
      sb.setCharAt(st.pop(), '*');
    return sb.toString().replaceAll("\\*", "");
  }
  */

  /*
  Approach 2: Stack with Tracking
  Instead of using placeholders, we can track indexes of all mismatched parentheses, and erase them in the end going right-to-left. This idea was inspired by dibdidib.

  We can introduce another stack to collect indexes of mismatched ')', or we can use the same stack and mark mismatched ')' somehow. Here, we just negate the index to indicate ')'.

  Note that I am adding 1 to make the index 1-based. You cannot tell if zero is negated :)

  Java
  */
  /*
  public String minRemoveToMakeValidII(String s) {
    StringBuilder sb = new StringBuilder(s);
    Stack<Integer> st = new Stack();
    for (int i = 0; i < sb.length(); ++i) {
      if (sb.charAt(i) == '(') st.add(i + 1);
      if (sb.charAt(i) == ')') {
        if (!st.empty() && st.peek() >= 0) st.pop();
        else st.add(-(i + 1));
      }
    }
    while (!st.empty())
      sb.deleteCharAt(Math.abs(st.pop()) - 1);
    return sb.toString();
  }
  */
  /*
  If we want to optimize for the worst-case scenario, we should avoid deleteCharAt inside the loop. Instead, we can copy characters that do not appear in the stack into another string builder. Since characters in the stack are naturally sorted, we can use two-pointer technique to do it in the linear time.

  Note that for the OJ test cases, the runtime of this solution is a bit worse than for deleteCharAt.
  */
  /*
  public String minRemoveToMakeValidIII(String s) {
    StringBuilder sb = new StringBuilder(s), sb1 = new StringBuilder();
    Stack<Integer> st = new Stack();
    for (int i = 0; i < sb.length(); ++i) {
      if (sb.charAt(i) == '(') st.add(i + 1);
      if (sb.charAt(i) == ')') {
        if (!st.empty() && st.peek() >= 0) st.pop();
        else st.add(-(i + 1));
      }
    }
    for (int i = 0, j = 0; i < sb.length(); ++i) {
      if (j >= st.size() || i != Math.abs(st.elementAt(j)) - 1) {
        sb1.append(sb.charAt(i));
      } else ++j;
    }
    return sb1.toString();
  }
  */
  /*
  Complexity Analysis

  Time: O(n). We process each character once, or twice for 'single' '('.
  Memory: O(n) for the stack.

  */
}
