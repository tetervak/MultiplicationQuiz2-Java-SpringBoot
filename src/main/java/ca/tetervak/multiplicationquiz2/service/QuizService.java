package ca.tetervak.multiplicationquiz2.service;

import ca.tetervak.multiplicationquiz2.model.Grade;
import ca.tetervak.multiplicationquiz2.model.Problem;

import java.util.Random;

public class QuizService {

    private final Random random;

    public QuizService(Random random) {
        this.random = random;
    }

    public QuizService() {
        this(new Random());
    }

    public Problem getRandomProblem(){
        int a = 1 + random.nextInt(9);
        int b = 1 + random.nextInt(9);
        return new Problem(a, b);
    }

    public Grade gradeUserAnswer(Problem problem, String userAnswer){
        try {
            if (problem.getAnswer() == Double.parseDouble(userAnswer)) {
                return Grade.RIGHT_ANSWER;
            } else {
                return Grade.WRONG_ANSWER;
            }
        } catch(NumberFormatException e){
            return Grade.INPUT_ERROR;
        }
    }
}
