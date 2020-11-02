package bitmanipulation;

class Convert_a_Number_to_Hexadecimal_405 {
	char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	public String toHex(int num) {
		if (num == 0) return "0";
		StringBuilder sb = new StringBuilder();
		while (num != 0) {
			sb.append(map[(num & 15)]);
			num = (num >>> 4);
		}
		return sb.reverse().toString();
	}

	public String toHexII(int num) {
		if (num == 0) {
			return "0";
		}

		char[] map = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'e', 'f'};
		int temp = num;
		StringBuilder sb = new StringBuilder();

		while (temp > 0) {
			sb.append(map[temp % 16]);
			temp /= 16;
		}

		while (num != 0) {
			sb.append(map[(num & 15)]);
			num = (num >>> 4);
		}

		return sb.reverse().toString();
	}


	public static void main(String[] args) {
		// System.out.println(new Convert_a_Number_to_Hexadecimal_405().toHex(26));

		// System.out.println(new Convert_a_Number_to_Hexadecimal_405().toHex(27));

		// System.out.println(new Convert_a_Number_to_Hexadecimal_405().toHex(28));

		// System.out.println(new Convert_a_Number_to_Hexadecimal_405().toHex(-1));

		System.out.println(26 & 15);
	}

}