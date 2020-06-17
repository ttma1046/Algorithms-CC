[Code is at the end]

So I just keep a shared currentNumber showing where we're up to. Each thread has to then check whether or not it should take its turn. If it's not the current thread's turn, it calls wait(), thus suspending and giving up the monitor. After each increment of currentNumber, we need to call notifyAll() so that the other threads are woken and can re-check the condition. They will all be given a chance to run

The implicit locks, (aka monitors) provided by synchronized can be a bit confusing at first to understand, so I recommend reading up on them if you are confused. I personally found these sites useful:

https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html
https://howtodoinjava.com/java/multi-threading/java-synchronized/
You might notice something slightly unusual in my wait code, i.e.

if (currentNumber % 3 != 0 || currentNumber % 5 == 0) {
     wait();
     continue;
}
When perhaps you would have expected the more traditional form of:

while (currentNumber % 3 != 0 || currentNumber % 5 == 0) {
     wait();
}
Seeing as we are always told that waiting conditions must be a loop, right? (Because sometimes it will have to wait more than once for its turn). The reason I have done this though is because otherwise there'd have to be an additional check in the waiting loop for if the currentNumber value is still below n.

while (currentNumber % 3 != 0 || currentNumber % 5 == 0) {
     wait();
	 if (currentNumber > n) return;
}
While this does work, I felt it was probably the more convoluted option. But it's definitely an option, and I'd be interested to hear thoughts on this "design" decision. In effect, it is a wait in a loop, it's just that it utilises the outer loop's condition (by using continue).

I probably could pull some of the repetition into a seperate method. One challenge was that 3 of the methods took a Runnable, and one took an IntConsumer.

class FizzBuzz {
    
    private int n;
    private int currentNumber = 1;
    
    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 3 != 0 || currentNumber % 5 == 0) {
                wait();
                continue;
            }
            printFizz.run();
            currentNumber += 1;
            notifyAll();
        }
    }

    // printBuzz.run() outputs "buzz".
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 5 != 0 || currentNumber % 3 == 0) {
                wait();
                continue;
            }
            printBuzz.run();
            currentNumber += 1;
            notifyAll();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 15 != 0) {
                wait();
                continue;
            }
            printFizzBuzz.run();
            currentNumber += 1;
            notifyAll();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 3 == 0 || currentNumber % 5 == 0) {
                wait();
                continue;
            }
            printNumber.accept(currentNumber);
            currentNumber += 1;
            notifyAll();
        }
    }