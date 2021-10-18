import java.util.Comparator;
import java.util.Vector;

public class App {
    public static void main(String[] args) throws Exception {
        int N = 5, k = 3;
        Condition origin = new Condition(N);
        Condition termination = new Condition();
        Vector<Condition> OpenMap = new Vector<Condition>();
        Vector<Condition> ClosedMap = new Vector<Condition>();
        ConditionComparator cmp = new ConditionComparator();
        Checker checker = new Checker(N, k);
        Shower shower = new Shower();
        Estimator est = new Estimator();

        est.start();
        OpenMap.add(origin);
        while (!OpenMap.isEmpty()) {
            Condition tmp = OpenMap.remove(0);
            ClosedMap.add(tmp);
            if (tmp.equals(termination)) {
                if (tmp.g < termination.g) {
                    termination = tmp;
                    break;
                }
            } else {
                Ruler ruler = new Ruler(tmp, k);
                while (ruler.hasRule()) {
                    Condition next = new Condition(tmp, ruler.nextRule());
                    if (checker.CheckLegal(next)) {
                        next.h = Heuristic.CalculateMethod2(next);
                        Condition inOpen = checker.CheckSameState(next, OpenMap);
                        Condition inClosed = checker.CheckSameState(next, ClosedMap);
                        // A station can in open map or closed map or not in both.
                        if (inOpen != null) {
                            if (next.g < inOpen.g) {
                                // better than the same station in open map, replace it.
                                OpenMap.remove(inOpen);
                                OpenMap.add(next);
                                OpenMap.sort(cmp);
                                est.Attempt();
                            }
                        } else if (inClosed != null) {
                            if (next.g < inClosed.g) {
                                // better than the same station in closed map, add it to open map and remove
                                // that in closed map
                                ClosedMap.remove(inClosed);
                                OpenMap.add(next);
                                OpenMap.sort(cmp);
                                est.Attempt();
                            }
                        } else {
                            // first accessed, add to open map
                            OpenMap.add(next);
                            OpenMap.sort(cmp);
                            est.Attempt();
                            est.Added();
                        }
                    }
                }
            }
        }
        est.finished();
        shower.show(termination, est);
    }
}

class ConditionComparator implements Comparator<Condition> {

    @Override
    public int compare(Condition o1, Condition o2) {
        return (o1.g + o1.h) - (o2.g + o2.h);
    }

}