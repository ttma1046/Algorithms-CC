class DiningPhilosophers {
    Semaphore[] sems;
    public DiningPhilosophers() {
        this.sems = new Semaphore[5];
        for (int i = 0; i < 5; i++) {
            sems[i] = new Semaphore(1);
        }
    }

    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        // philosphers 0 -> 4 are sitting clockwise
        Semaphore left = (philosopher == 4) ? sems[0] : sems[philosopher + 1];
        Semaphore right = sems[philosopher];
        
        if (philosopher % 2 == 0) {
            left.acquire();
            pickLeftFork.run();
            right.acquire();
            pickRightFork.run();
        } else {
            right.acquire();
            pickRightFork.run();
            left.acquire();
            pickLeftFork.run();
        }
        
        eat.run();
        
        putRightFork.run();
        right.release();
        putLeftFork.run();
        left.release();
    }
}