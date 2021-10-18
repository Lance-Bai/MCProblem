public class Heuristic {
    static int CalculateMethod0(Condition c) {
        return 0;
    }

    static int CalculateMethod1(Condition c) {
        return c.state[0][0] + c.state[1][0];
    }

    static int CalculateMethod2(Condition c) {
        return c.state[0][0] + c.state[1][0] - 2 * c.state[2][0];
    }
}
