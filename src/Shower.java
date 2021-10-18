import java.util.Arrays;
import java.util.Stack;

public class Shower {
    Stack<Condition> path;

    Shower() {
        path = new Stack<Condition>();
    }

    void show(Condition termination, Estimator est) {
        while (termination != null) {
            path.add(termination);
            termination = termination.parent;
        }
        System.out.println("_L__R_");
        while (!path.isEmpty()) {
            int[][] s = path.pop().state;
            System.out.println(
                    Arrays.toString(s[0]) + "\n" + Arrays.toString(s[1]) + "\n" + Arrays.toString(s[2]) + "\n");
        }
        System.out.println("There are " + est.ConditionAdded + " conditions accessed in total");
        System.out.println("There are " + est.ConditionTried + " times we access the conditions");
        System.out.println("The search program takes " + (est.endTime - est.startTime) / 10000000.0 + " milliseconds in total");
    }
}
