package string;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Meta_Strings_gfg {
	// Returns true if str1 and str2 are meta strings
	boolean areMetaStringsII(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();

		// Return false if both are not of equal length
		if (len1 != len2) return false;

		// To store indexes of previously mismatched
		// characters
		int prev = -1, curr = -1, count = 0;

		for (int i = 0; i < len1; i++) {
			// If current character doesn't match
			if (str1.charAt(i) != str2.charAt(i)) {
				// Count number of unmatched character
				count++;

				// If unmatched are greater than 2,
				// then return false
				if (count > 2) return false;

				// Store both unmatched characters of
				// both strings
				prev = curr;
				curr = i;
			}
		}

		// Check if previous unmatched of string1
		// is equal to curr unmatched of string2
		// and also check for curr unmatched character,
		// if both are same, then return true
		return count == 2 &&
		       str1.charAt(prev) == str2.charAt(curr) &&
		       str1.charAt(curr) == str2.charAt(prev);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for ( int x = 0 ; x < t; x++ ) {
			String str1 = br.readLine();
			String str2 = br.readLine();

			System.out.println(areMetaStrings(str1, str2) ? "1" : "0");
		}
	}

	static boolean areMetaStrings(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();

		// Return false if both are not of equal length
		if (len1 != len2) return false;

		// To store indexes of previously mismatched
		// characters
		int index = -1;
		boolean alreadyCompared = false;
		boolean different = false;

		for (int i = 0; i < len1; i++) {
			// If current character doesn't match
			if (str1.charAt(i) != str2.charAt(i)) {
				if (alreadyCompared) {
					return false;
				}

				if (index > 0) {
					if (str1.charAt(i) != str2.charAt(index) || str1.charAt(index) != str2.charAt(i)) {
						return false;
					} else {
						alreadyCompared = true;
					}
				} else {
					index = i;
				}

				different = true;
			}
		}

		return different;
	}

	/*	// Driver method
		public static void main(String args[]) {
			String str1 = "converse";
			String str2 = "conserve";

			System.out.println(new Meta_Strings_gfg().areMetaStrings(str1, str2));

			str1 = "geeks";
			str2 = "keegs";

			System.out.println(new Meta_Strings_gfg().areMetaStrings(str1, str2));

			str1 = "geeks";
			str2 = "geeks";

			System.out.println(new Meta_Strings_gfg().areMetaStrings(str1, str2));

			str1 = "rsting";
			str2 = "string";
			System.out.println(new Meta_Strings_gfg().areMetaStrings(str1, str2));
		}
	*/
}