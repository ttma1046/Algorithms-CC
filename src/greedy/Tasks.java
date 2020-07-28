package greedy;
import java.util.Arrays;

class Tasks {
    private int calTasks(int [] taskCost, int max) {
        Arrays.sort(taskCost);

        int result = 0;
        int cost = 0;
        for (int i = 0; i < taskCost.length; i++) {
            cost += taskCost[i];
            if (cost > max) {
                break;
            }

            result++;
        }

        return result;
    }
}