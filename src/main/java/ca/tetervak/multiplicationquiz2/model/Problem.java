package ca.tetervak.multiplicationquiz2.model;

public class Problem {

    private final int a;
    private final int b;
    private final int answer;

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getAnswer() {
        return answer;
    }

    public Problem(int a, int b, int answer) {
        this.a = a;
        this.b = b;
        this.answer = answer;
    }

    public Problem(int a, int b) {
        this(a, b, a * b);
    }

    public Problem() {
        this(1, 1);
    }

    @Override
    public String toString() {
        return String.format("Problem[%d x %d = %d]", a, b, answer);
    }
}
