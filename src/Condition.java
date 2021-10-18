public class Condition {
    static int N;
    int g, h;
    int[][] state;
    Condition parent;

    Condition(int N) {
        Condition.N = N;
        state = new int[][] { { N, 0 }, { N, 0 }, { 1, 0 } };
        g = 0;
    }

    Condition() {
        state = new int[][] { { 0, N }, { 0, N }, { 0, 1 } };
        g = Integer.MAX_VALUE;
    }

    Condition(Condition past, Rule rule) {
        if (past.state[2][0] == 1) {
            state = new int[][] { { past.state[0][0] - rule.m, past.state[0][1] + rule.m },
                    { past.state[1][0] - rule.c, past.state[1][1] + rule.c }, { 0, 1 } };
        } else {
            state = new int[][] { { past.state[0][0] + rule.m, past.state[0][1] - rule.m },
                    { past.state[1][0] + rule.c, past.state[1][1] - rule.c }, { 1, 0 } };
        }
        g = past.g + 1;
        parent = past;
    }

    public boolean equals(Condition c) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                if (c.state[j][i] != state[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
