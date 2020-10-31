package leetcode;

/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf4);
 */
public class Read_N_Characters_Given_Read4f_157 extends Reader4 {
	/**
	 * @param buf Destination buffer
	 * @param n   Number of characters to read
	 * @return    The number of actual characters read
	 */
	public int read(char[] buf, int n) {
		int index = 0;
		while (true) {
			if (index == n) {
				return index;
			}

			char[] buf4 = new char[4];
			int left = read4(buf4);

			for (int i = 0; i < left; i++) {
				if (index < n) {
					buf[index++] = buf4[i];
				} else {
					return index;
				}
			}

			if (left < 4) {
				return index;
			}
		}
	}
}