package leetcode;

/*
Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.

Person A will NOT friend request person B (B != A) if any of the following conditions are true:

age[B] <= 0.5 * age[A] + 7
age[B] > age[A]
age[B] > 100 && age[A] < 100
Otherwise, A will friend request B.

Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.

How many total friend requests are made?

Example 1:

Input: [16,16]
Output: 2
Explanation: 2 people friend request each other.
Example 2:

Input: [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.
Example 3:

Input: [20,30,100,110,120]
Output: 3
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.


Notes:

1 <= ages.length <= 20000.
1 <= ages[i] <= 120.
*/

class Friends_Of_Appropriate_Ages_825 {
	public int numFriendRequests(int[] ages) {
		int[] count = new int[120];
		// 1 <= age <= 120 
		for (int age: ages) count[age - 1]++;

		for (int i = 0; i < count.length; i++) {
			if (count[i] > 0) {
				System.out.println("i:" + i);
				System.out.println("count[i]:" + count[i]);
			}
		}

		int result = 0;
		for (int i = 0; i < count.length; i++) {
			int countA = count[i];

			for (int j = 0; j < count.length; j++) {
				int countB = count[j];

				if ((i + 1) < (j + 1)) continue;
				if (((i + 1) * 0.5 + 7) >= (j + 1)) continue;
				if ((i + 1) < 100 && (j + 1) > 100)  continue;

				result += countB * countA;
				if (i == j) result -= countA;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		// int[] ages = new int[] {16, 16};
		// System.out.println(new Friends_Of_Appropriate_Ages_825().numFriendRequests(ages));

		int[] ages = new int[] {16, 17, 18};
		System.out.println(new Friends_Of_Appropriate_Ages_825().numFriendRequests(ages));

		// ages = new int[] {20, 30, 100, 110, 120};
		// System.out.println(new Friends_Of_Appropriate_Ages_825().numFriendRequests(ages));
	}
}