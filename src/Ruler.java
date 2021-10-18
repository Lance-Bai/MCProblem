import java.util.Iterator;
import java.util.Vector;

public class Ruler {
    Vector<Rule> rules;
    Iterator<Rule> itRules;

    Ruler(Condition c, int k) {
        rules = new Vector<Rule>();
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j <= k; j++) {
                rules.add(new Rule(i, j));
            }
        }
        itRules = rules.iterator();
    }

    boolean hasRule() {
        return itRules.hasNext();
    }

    Rule nextRule() {
        return itRules.next();
    }
}

class Rule {
    int m;
    int c;

    Rule(int M, int C) {
        m = M;
        c = C;
    }
}
