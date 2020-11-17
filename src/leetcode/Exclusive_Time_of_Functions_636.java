package leetcode;
/*
On a single-threaded CPU, we execute a program containing n functions. Each function has a unique ID between 0 and n-1.

Function calls are stored in a call stack: when a function call starts, its ID is pushed onto the stack, and when a function call ends, its ID is popped off the stack. The function whose ID is at the top of the stack is the current function being executed. Each time a function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.

You are given a list logs, where logs[i] represents the ith log message formatted as a string "{function_id}:{"start" | "end"}:{timestamp}". For example, "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3, and "1:end:2" means a function call with function ID 1 ended at the end of timestamp 2. Note that a function can be called multiple times, possibly recursively.

A function's exclusive time is the sum of execution times for all function calls in the program. For example, if a function is called twice, one call executing for 2 time units and another call executing for 1 time unit, the exclusive time is 2 + 1 = 3.

Return the exclusive time of each function in an array, where the value at the ith index represents the exclusive time for the function with ID i.



Example 1:


Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
Output: [3,4]
Explanation:
Function 0 starts at the beginning of time 0, then it executes 2 for units of time and reaches the end of time 1.
Function 1 starts at the beginning of time 2, executes for 4 units of time, and ends at the end of time 5.
Function 0 resumes execution at the beginning of time 6 and executes for 1 unit of time.
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
Example 2:

Input: n = 1, logs = ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]
Output: [8]
Explanation:
Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
Function 0 (initial call) resumes execution then immediately calls itself again.
Function 0 (2nd recursive call) starts at the beginning of time 6 and executes for 1 unit of time.
Function 0 (initial call) resumes execution at the beginning of time 7 and executes for 1 unit of time.
So function 0 spends 2 + 4 + 1 + 1 = 8 units of total time executing.
Example 3:

Input: n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"]
Output: [7,1]
Explanation:
Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
Function 0 (initial call) resumes execution then immediately calls function 1.
Function 1 starts at the beginning of time 6, executes 1 units of time, and ends at the end of time 6.
Function 0 resumes execution at the beginning of time 6 and executes for 2 units of time.
So function 0 spends 2 + 4 + 1 = 7 units of total time executing, and function 1 spends 1 unit of total time executing.
Example 4:

Input: n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8"]
Output: [8,1]
Example 5:

Input: n = 1, logs = ["0:start:0","0:end:0"]
Output: [1]


Constraints:

1 <= n <= 100
1 <= logs.length <= 500
0 <= function_id < n
0 <= timestamp <= 109
No two start events will happen at the same timestamp.
No two end events will happen at the same timestamp.
Each function has an "end" log for each "start" log.
*/
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

class Exclusive_Time_of_Functions_636 {
	public int[] exclusiveTime(int n, List<String> logs) {
		int[] res = new int[n];
		int endTimeStamp = 0;
		Stack<Pair> myStack = new Stack<Pair>();

		for (String log : logs) {
			String[] logArray = log.split(":");

			if (logArray[1].equals("start")) {
				if (!myStack.isEmpty()) {
					Pair perviousStart = myStack.pop();
					int taskId = perviousStart.taskId;
					int perviousStartTimeStamp = perviousStart.timeStamp;

					res[taskId] += Integer.parseInt(logArray[2]) - perviousStartTimeStamp;
				}

				myStack.push(new Pair(Integer.parseInt(logArray[0]), Integer.parseInt(logArray[2])));
			} else if (logArray[1].equals("end")) {
				if (myStack.isEmpty()) {
					int taskId = Integer.parseInt(logArray[0]);
					res[taskId] += Integer.parseInt(logArray[2]) - endTimeStamp;
				} else {
					Pair startTime = myStack.pop();
					int taskId = startTime.taskId;
					int startTimeStamp = startTime.timeStamp;

					endTimeStamp = Integer.parseInt(logArray[2]);

					res[taskId] += endTimeStamp - startTimeStamp + 1;
				}
			}
			// |0|1|2|3|4|5|6|7|
		}

		return res;
	}


	public int[] exclusiveTime(int n, List<String> logs) {
		int[] res = new int[n];
		Stack<Integer> stack = new Stack<>();//store id, not timestamp
		int prev = 0; //store timestamp

		for (String log : logs) {

			String[] strs = log.split(":");

			int id = Integer.parseInt(strs[0]);

			int curr = Integer.parseInt(strs[2]);

			if (strs[1].equals("start")) {
				if (!stack.isEmpty()) {
					res[stack.peek()] += curr - prev;
				}
				stack.push(id);
				prev = curr;
			} else {
				res[stack.pop()] += curr - prev + 1;
				prev = curr + 1;
			}
		}
		return res;
	}


	public int[] exclusiveTime(int n, List<String> logs) {
		int[] result = new int[n];
		Stack<Integer> stack = new Stack<>();
		int prev = 0, currentId = 0;

		for (String log : logs) {
			int firstColon = log.indexOf(':');
			int id = toInt(log, 0, firstColon);

			int lastColon = log.lastIndexOf(':');
			int curr = toInt(log, lastColon + 1, log.length());

			if (log.charAt(firstColon + 1) == 's') {
				if (!stack.isEmpty()) result[stack.peek()] += curr - prev;
				stack.push(id);
				prev = curr;
			} else {
				result[stack.pop()] += curr - prev + 1;
				prev = curr + 1;
			}
		}

		return result;
	}

	private int toInt(String s, int start, int end) {
		int result = 0;

		while (start < end) {
			result = result * 10 + s.charAt(start) - '0';
			start++;
		}

		return result;
	}

	public static void main(String[] args) {
		List<String> strs = new ArrayList<String>();
		strs.add("0:start:0");
		strs.add("0:end:0");

		int[] res = new Exclusive_Time_of_Functions_636().exclusiveTime(1, strs);

		for (int item : res) {
			System.out.println(item);
		}

		/*
				strs = new ArrayList<String>();
				strs.add("0:start:2");
				strs.add("0:end:5");
				strs.add("1:start:7");
				strs.add("1:end:7");

				res = new Exclusive_Time_of_Functions_636().exclusiveTime(2, strs);

				for (int item : res) {
					System.out.println(item);
				}
		*/
		strs = new ArrayList<String>();

		strs.add("0:start:0");
		strs.add("1:start:2");
		strs.add("1:end:5");
		strs.add("0:end:6");

		res = new Exclusive_Time_of_Functions_636().exclusiveTime(2, strs);

		for (int item : res) {
			System.out.println(item);
		}

		// [3,4]

		strs = new ArrayList<String>();

		strs.add("0:start:0");
		strs.add("0:start:2");
		strs.add("0:end:5");
		strs.add("0:start:6");
		strs.add("0:end:6");
		strs.add("0:end:7");

		res = new Exclusive_Time_of_Functions_636().exclusiveTime(1, strs);

		for (int item : res) {
			System.out.println(item);
		}

		// [8]

		strs = new ArrayList<String>();

		strs.add("0:start:0");
		strs.add("0:start:2");
		strs.add("0:end:5");
		strs.add("1:start:6");
		strs.add("1:end:6");
		strs.add("0:end:7");

		res = new Exclusive_Time_of_Functions_636().exclusiveTime(2, strs);

		for (int item : res) {
			System.out.println(item);
		}

		// [7,1]

		strs = new ArrayList<String>();

		strs.add("0:start:0");
		strs.add("0:start:2");
		strs.add("0:end:5");
		strs.add("1:start:7");
		strs.add("1:end:7");
		strs.add("0:end:8");

		res = new Exclusive_Time_of_Functions_636().exclusiveTime(2, strs);

		for (int item : res) {
			System.out.println(item);
		}

		// [8, 1]
	}
}

class Pair {
	int taskId;
	int timeStamp;

	Pair(int x, int y) {
		this.taskId = x;
		this.timeStamp = y;
	}
}