package amazon;

/*
Given a string s consisting of items as "*" and closed compartments as an open and close "|", an array of starting indices startIndices, and an array of ending indices endIndices, determine the number of items in closed compartments within the substring between the two indices, inclusive.

An item is represented as an asterisk *
A compartment is represented as a pair of pipes | that may or may not have items between them.
Example:
s = '|**|*|*'
startIndices = [1,1]
endIndices = [5,6]

The String has a total 2 closed compartments, one with 2 items and one with 1 item. For the first par of indices, (1,5), the substring is '|**|*'. There are 2 items in a compartment.
For the second pair of indices, (1,6), the substring is '|**|*|' and there 2+1=3 items in compartments.
Both of the answers are returned in an array. [2,3].
*/
class Result {
	public static List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {
		// Write your code here
		
		}
}