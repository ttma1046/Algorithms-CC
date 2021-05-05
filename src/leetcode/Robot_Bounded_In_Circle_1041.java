package leetcode;
/*
On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive one of three instructions:

"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degrees to the right.
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.



Example 1:

Input: instructions = "GGLLGG"
Output: true
Explanation: The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
Example 2:

Input: instructions = "GG"
Output: false
Explanation: The robot moves north indefinitely.
Example 3:

Input: instructions = "GL"
Output: true
Explanation: The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...


Constraints:

1 <= instructions.length <= 100
instructions[i] is 'G', 'L' or, 'R'.
*/
class Robot_Bounded_In_Circle_1041 {
	public boolean isRobotBounded(String instructions) {
		char[] ins = instructions.toCharArray();

		// int[][]	directionL = new int[][] {{0, 1}, { -1, 0}, {0, -1}, {1, 0}};
		// int[][] directionR = new int[][] {{0, 1}, {1, 0}, {0, -1}, { -1, 0}};

		int[][] directions = new int[][] {{0, 1}, { -1, 0}, {0, -1}, {1, 0}};

		int x = 0;
		int y = 0;

		// int[] direction = new int[] {directionL[0][0], directionL[0][1]};

		int[] direction = new int[] {directions[0][0], directions[0][1]};
		int i = 0;
		for (char c : ins) {
			switch (c) {
			case 'G':
				x += direction[0];
				y += direction[1];
				break;
			case 'L':
				i = i == 3 ? 0 : i + 1;
				direction = directions[i];
				break;
			case 'R':
				i = i == 0 ? 3 : i - 1;
				direction = directions[i];
				break;
			default:
				break;
			}
		}

		System.out.println("direction:" + direction[0] + " " + direction[1]);
		System.out.println("x:" + x + ", y:" + y);

		return !(direction[0] == 0 && direction[1] == 1 && (x != 0 || y != 0));
	}

	public static void main(String[] args) {
		Robot_Bounded_In_Circle_1041 robot = new Robot_Bounded_In_Circle_1041();

		String res = "GGLLGG";
		System.out.println(robot.isRobotBounded(res));

		res = "GG";
		System.out.println(robot.isRobotBounded(res));

		res = "GL";
		System.out.println(robot.isRobotBounded(res));

		res = "LLGRL";
		System.out.println(robot.isRobotBounded(res));
	}
}