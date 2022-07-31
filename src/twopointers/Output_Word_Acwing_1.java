package twopointers;

class Output_Word_Acwing_1 {
	void output(String s) {
		int n = s.length();

		for (int i = 0; i < n; ++i) {
			int j = i;
			while(j < n && s.charAt(j) != ' ')
				j++;

			for (int k = i; k < j; ++k)
				System.out.print(s.charAt(k));
			System.out.println();

			i = j;
		}
	}

	public static void main(String[] args) {
		String s = "abc def ghi";

		Output_Word_Acwing_1 obj = new Output_Word_Acwing_1();
		obj.output(s);
	}
}