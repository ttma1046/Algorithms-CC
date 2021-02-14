class Stack {
	int top;
	int maxsize = 100;
	int[] arr = new int[maxsize];

	Stack() {
		top = -1;
	}

	boolean isEmpty() {
		return top < 0;
	}

	void push(int val) {
		if (top == maxsize - 1) {
			System.out.println("Overflow !!");
		} else {
			System.out.println("Enter Value");
			arr[top++] = val;
			System.out.println("Item pushed");
		}
	}

	int pop() {
		if (top == -1) {
			System.out.println("Underflow !!");
			return null;
		} else {
			return arr[top--];
		}
	}

	void display() {
		System.out.println("Printing stack elements .....");
		for (int i = top; i >= 0; i--) {
			System.out.println(arr[i]);
		}
	}
}