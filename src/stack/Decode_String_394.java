package stack;

class Decode_String_394 {
	public String decodeString(String s) {
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ']') {
				List<Character> decodeString = new ArrayList<Character>();

				while(stack.peek() != '[') {
					decodeString.add(stack.pop());
				}

				stack.pop();

				int k = 0;
				int base = 1;


				while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
					k += (stack.pop() - '0') * base

					base *= 10;
				}


				while (k != 0) {
					for (int j = decodeString.length() - 1; j >= 0; j--) {
						stack.push(decodeString.charAt(j));
					}

					k--;
				}
			} else {
				stack.push(s.charAt(i));
			}


			char[] result = new char[stack.size()];

			for (int i = result.length - 1; i > 0; i--) {
				result[i] = stack.pop();
			}

			resturn new String(result);
		}
	}

	public String decodeString(String s) {
		Deque<Character> queue = new LinkedList<>();
		for (char c : s.toCharArray()) queue.offer(c);
		return helper(queue);
	}

	public String helper(Deque<Character> queue) {
		StringBuilder sb = new StringBuilder();
		int num = 0;
		while (!queue.isEmpty()) {
			char c = queue.poll();
			if (Character.isDigit(c)) {
				num = num * 10 + c - '0';
			} else if (c == '[') {
				String sub = helper(queue);
				for (int i = 0; i < num; i++) sb.append(sub);
				num = 0;
			} else if (c == ']') {
				break;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}