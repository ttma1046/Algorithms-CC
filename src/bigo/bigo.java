package bigo;

public class Quizs {
	/*
	1
	*/
	int product(int a, int b) {
		int sum = 0;
		for (int i = 0; i < b; i++) {
			sum += a;
		}

		return sum;
	}
	// O(b)

	/*
	2
	*/
	int power(int a, int b) {
		if (b < 0) {
			return 0; // error
		} else if (b == 0) {
			return 1;
		} else {
			return a * power(a, b - 1);
		}
	}
	// O(b)

	/*
	3
	*/
	int mod(int a, int b) {
		if (b <= 0) {
			return -1;
		}
		int div = a / b;
		return a - div * b;
	}
	// O(1);

	/*
	4
	*/
	int div(int a, int b) {
		int count = 0;
		int sum = b;
		while (sum <= a) {
			sum += b;
			count++;
		}

		return count;
	}
	// O(a/b)

	/*
	5. The following code computes the [integer) square root of a number. If the number is not a
	perfect square (there is no integer square root), then it returns -1 .It does this by successive
	guessing. If n is 100, it first guesses SO. Too high? Try something lower - halfway between 1
	and SO. What is its runtime?
	*/
	int sqrt(int n) {
		return sqrt_helper(n, 1, n);
	}

	int sqrt_helper(int n, int min, int max) {
		if (max < min) return -1; // no square root

		int guess = (min + max) / 2;
		if (guess * guess == n) { // found it!
			return guess;
		} else if (guess * guess < n) { // too low
			return sqrt_helper(n, guess + 1, max); // try higher;
		} else { // too high
			return sqrt_helper(n, min, guess - 1); // try lower;
		}
	}
	// O(sqrt(n))

	/*
	6.
	The following code computes the [integer) square root of a number. If the number is not
	a perfect square (there is no integer square root), then it returns -1. It does this by trying
	increasingly large numbers until it finds the right value (or is too high). What is its runtime?
	*/
	int sqrt(int n) {
		for (int guess = 1; guess * guess <= n; guess++) {
			if (guess * guess == n) {
				return guess;
			}
		}
		return -1;
	}
	// O(log n)

	/*
	 7. If a binary search tree is not balanced, how long might it take (worst case) to find an element in it;
	 O(n)
	*/

	/*
	 8. You are looking for a specific value in a binary tree, but the tree is not a binary search tree.
	What is the time complexity of this?
	O(n)
	*/

	/*
	9.
		The appendToNew method appends a value to an array by creating a new, longer array and
		returning this longer array. You've used the appendToNew method to create a copyArray
		function that repeatedly calls appendToNew. How long does copying an array take?
	*/
	int[] copyArray(int[] array) {
		int[] copy = new int[0];
		for (int value : array) {
			copy = appendToNew(copy, value);
		}
		return copy;
	}
	int[] appendToNew(int[] array, int value) {
		// copy all elements over to new array
		int[] bigger = new int[array.length + 1];
		for (int i = 0; i < array.length; i++) {

			bigger[i] = array[i];
		}
		// add new element
		bigger[bigger.length - 1] = value;
		return bigger;
	}

	// 1 + 2 + 3 + .... (arrya.length = n)
	// O(n^2)

	/*
	VI.l0 The following code sums the digits in a number. What is its big 0 time?
	*/
	int sumDigits(int n) {
		int sum = 0;
		while (n > 0) {
			sum += n % 10;
			n /= 10;
		}
		return sum;
	}
	// O(log(10, N))

	/*
	VI.11 The following code prints all strings of length k where the characters are in sorted order. It
	does this by generating all strings of length k and then checking if each is sorted. What is its
	runtime?
	*/
	int numChars = 26;

	void printSortedStrings(int remaining) {
		printSortedStrings(remaining, "");
	}

	void printSortedStrings(int remaining, String prefix) {
		if (remaining == 0) {
			if (islnOrder(prefix)) {
				System.out.println(prefix);
			}
		} else {
			for (int i = e; i < numChars; i++) {
				char c = ithLetter(i);
				printSortedStrings(remaining - 1, prefix + c);
			}
		}
	}

	boolean islnOrder(String s) {
		for (int i = 1; i < s.length(); i++) {
			int prev ithLetter(s.charAt(i - 1));
			int curr = ithLetter(s.charAt(i));
			if (prev > curr) {
				return false;
			}
		}
		return true;
	}

	char ithLetter(int i) {
		return (char)(((int)'a') + i);
	}

	/*
	(kc^k), where k is the length of the string and c is the number of characters in the alphabet. It takes
		O( c^k ) time to generate each string. Then, we need to check that each of these is sorted, which takes
		O(k) time.

		n26^n
	*/

	/* VI.12 The following code computes the intersection (the number of elements in common) of two
	arrays. It assumes that neither array has duplicates. It computes the intersection by sorting
	one array (array b) and then iterating through array a checking (via binary search) if each
	value is in b. What is its runtime?
	*/
	int intersection(int[] a, int[] b) {
		mergesort(b);
		int intersect = 0;
		for (int x : a) {
			if (binarySearch(b, x) >= e) {
				intersect++ ;
			}
		}
		return intersect;
	}
	// O(blogb + alogb)
}
