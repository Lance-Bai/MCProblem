import java.util.Iterator;
import java.util.Vector;

public class Checker {
    private int N, k;

    Checker(int N, int k) {
        this.N = N;
        this.k = k;
    }

    boolean CheckLegal(Condition condition) {
        // static station analysis
        // The number of people is in range?
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (condition.state[j][i] < 0 || condition.state[j][i] > N) {
                    return false;
                }
            }
        }
        // M>=C when M!=0?
        for (int i = 0; i < 2; i++) {
            if (condition.state[0][i] < condition.state[1][i] && condition.state[0][i] != 0) {
                return false;
            }
        }

        // dynamic stations analysis
        int m = Math.abs(condition.state[0][0] - condition.parent.state[0][0]);
        int c = Math.abs(condition.state[1][0] - condition.parent.state[1][0]);
        // The number of people on boat available?
        if (m + c <= 0 || m + c > k) {
            return false;
        }
        // M>=C when M!=0 on board?
        if (m < c && m != 0) {
            return false;
        }

        // All test finished!
        return true;
    }

    Condition CheckSameState(Condition condition, Vector<Condition> map){
        Iterator<Condition> it = map.iterator();
        while(it.hasNext()){
            Condition tmp = it.next();
            if(condition.equals(tmp)){
                return tmp;
            }
        }
        return null;
    }

}
