package recursion;

import java.util.Stack;

class TowersofHanoi_8point6 {
    /*
     * void moveDisks(int n, Tower origin, Tower buffer, Tower destination) {
     * if(n<=0)return; // Base case
     * 
     * moveDisks(n - 1, origin, destination, buffer); // move top n - 1 disks from
     * origin to buffer, using destination as a buffer.
     * 
     * moveTop(origin, destination); // move top from origin to destination.
     * 
     * moveDisks(n - 1, buffer, origin, destination); // move top n - 1 distks from
     * buffer to destination, using origin as a buffer. }
     */

    void main(String[] args) {
        int n = 3;
        Tower[] towers = new Tower[n];

        for (int i = 0; i < 3; i++) {
            towers[i] = new Tower(i);
        }

        for (int i = n - 1; i >= 0; i--) {
            towers[0].add(i);
        }

        towers[0].moveDisks(n, towers[1], towers[2]);
    }

    class Tower {
        private Stack<Integer> disks;
        private int index;

        public Tower(int i) {
            disks = new Stack<Integer>();
            index = i;
        }

        public int index() {
            return index;
        }

        public void add(int d) {
            if (!disks.isEmpty() && disks.peek() <= d) {
                System.out.println("Error plcacing disk " + d);
            } else {
                disks.push(d);
            }
        }

        public void moveTopTo(Tower t) {
            int top = disks.pop();
            t.add(top);
        }

        public void moveDisks(int n, Tower buffer, Tower destination) {
            if (n > 0) {
                moveDisks(n - 1, destination, buffer);
                moveTopTo(destination);
                buff.moveDisks(n - 1, this, destination);
            }
        }
    }
}