package greedy;
class RestoreIpAddress {
	int n;
	String s;
	LinkedList<String> segments = new LinkedList<String>();
	ArrayList<String> output = new ArrayList<String>();

	public boolean valid(String segment) {
		/*
		Check if the current segment is valid :
		1. less or equal to 255
		2. the first character could be '0'
		only if the segment is equal to '0'
		*/
		int m = segment.length();
		if (m > 3)
			return false;
		return (segment.charAt(0) != '0') ? (Integer.valueOf(segment) <= 255) : (m == 1);
	}

	public void update_output(int curr_pos) {
		/*
		Append the current list of segments
		to the list of solutions
		*/
		String segment = s.substring(curr_pos + 1, n);
		if (valid(segment)) {
			segments.add(segment);
			output.add(String.join(".", segments));
			segments.removeLast();
		}
	}

	public void backtrack(int prev_pos, int dots) {
		/*
		prev_pos : the position of the previously placed dot
		dots : number of dots to place
		*/
		// The current dot curr_pos could be placed
		// in a range from prev_pos + 1 to prev_pos + 4.
		// The dot couldn't be placed
		// after the last character in the string.
		int max_pos = Math.min(n - 1, prev_pos + 4);
		for (int curr_pos = prev_pos + 1; curr_pos < max_pos; curr_pos++) {
			String segment = s.substring(prev_pos + 1, curr_pos + 1);
			if (valid(segment)) {
				segments.add(segment);  // place dot
				if (dots - 1 == 0)      // if all 3 dots are placed
					update_output(curr_pos);  // add the solution to output
				else
					backtrack(curr_pos, dots - 1);  // continue to place dots
				segments.removeLast();  // remove the last placed dot
			}
		}
	}

	public List<String> restoreIpAddresses(String s) {
		n = s.length();
		this.s = s;
		backtrack(-1, 3);
		return output;
	}

	public List<String> restoreIpAddressesII(String s) {
		List<String> res = new ArrayList<String>();
		int len = s.length();
		for (int i = 1; i < 4 && i < len - 2; i++) {
			for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
				for (int k = j + 1; k < j + 4 && k < len; k++) {
					String s1 = s.substring(0, i),
					       s2 = s.substring(i, j),
					       s3 = s.substring(j, k),
					       s4 = s.substring(k, len);
					if (isValid(s1)
					        && isValid(s2)
					        && isValid(s3)
					        && isValid(s4)) {
						res.add(s1 + "." + s2 + "." + s3 + "." + s4);
					}
				}
			}
		}
		return res;
	}

	public boolean isValid(String s) {
		if (s.length() > 3 || s.length() == 0 || (s.charAt(0) == '0' && s.length() > 1) || Integer.parseInt(s) > 255)
			return false;
		return true;
	}

	public List<String> restoreIpAddressesIII(String ipaddresses) {
		List<String> result = new ArrayList<>();
		doRestore(result, "", ipaddresses, 0);
		return result;
	}

	private void doRestore(List<String> result, String path, String ipaddresses, int k) {
		if (ipaddresses.isEmpty() || k == 4) {
			if (ipaddresses.isEmpty() && k == 4) {
				result.add(path.substring(1));
			}
			return;
		}
		for (int i = 1; i <= (ipaddresses.charAt(0) == '0' ? 1 : 3) && i <= ipaddresses.length(); i++) { // Avoid leading 0
			String part = ipaddresses.substring(0, i);
			if (Integer.valueOf(part) <= 255)
				doRestore(result, path + "." + part, ipaddresses.substring(i), k + 1);
		}
	}

	class Solution {
		private List<String> ipAddresses = new ArrayList<>();

		public List<String> restoreIpAddresses(String s) {
			dfs(s, new int[4], 0, 0);

			return ipAddresses;
		}

		private void dfs(String s, int[] ipSections, int pos, int parsed) {
			if (pos == 4) {
				if (parsed == s.length()) {
					StringBuilder ip = new StringBuilder();
					while (pos > 0) {
						ip.append(ipSections[4 - pos]);
						if (pos > 1) {
							ip.append('.');
						}

						pos--;
					}

					ipAddresses.add(ip.toString());
				}
			} else {
				int rem = s.length() - parsed;
				int lo = Math.max(1, rem - 3 * (3 - pos));
				int hi = Math.min(3, rem - (3 - pos));

				int curr = 0;
				for (int i = 1; i <= hi && (i == 1 || curr > 0) && curr <= 25; i++) {
					curr = curr * 10 + s.charAt(parsed + i - 1) - '0';

					if (i >= lo && curr <= 255) {
						ipSections[pos] = curr;
						dfs(s, ipSections, pos + 1, parsed + i);
					}
				}
			}
		}
	}
}