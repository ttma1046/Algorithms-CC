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

		System.out.println(new Convert_a_Number_to_Hexadecimal_405().toHexII(-1));

		System.out.println(26 & 15);

		System.out.println(-1 & 15);

		System.out.println(26 >> 2);
		System.out.println(26 >> 4);
		System.out.println(26 >>> 2);
		System.out.println(26 >>> 4);

		System.out.println(-26 >>> 4);

		byte text = -26;

		System.out.println(text >>> 4);
/*
		System.out.println(10 & 12);
		System.out.println(12 & 10);


		System.out.println(26 & 7);
		System.out.println(26 & 9);
		System.out.println(Math.pow(2, 15));
*/
/*
		0000 0000 0000 0001


		1111 1111 1111 1110

		1111 1111 1111 1111

*/


		/*
		System.out.println(26 & 1);
        //0
		// 26/2 = 13
        System.out.println(13 & 1);

        //1
        // 13/2 = 6

        System.out.println(6 & 1);

        //0
        // 6/2 = 3

        System.out.println(3 & 1);

        //1
        // 3/2 = 1
        System.out.println(1 & 1);

        //1

        11010

        16 + 8 + 2
        26

		*/
	}
}