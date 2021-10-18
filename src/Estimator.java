public class Estimator {
    int ConditionTried;
    int ConditionAdded;
    long startTime;
    long endTime;

    Estimator() {
        ConditionTried = 0;
        ConditionAdded = 0;
    }

    void Attempt() {
        ConditionTried += 1;
    }

    void Added() {
        ConditionAdded += 1;
    }

    void start() {
        startTime = System.nanoTime();
    }

    void finished() {
        endTime = System.nanoTime();
    }
}
