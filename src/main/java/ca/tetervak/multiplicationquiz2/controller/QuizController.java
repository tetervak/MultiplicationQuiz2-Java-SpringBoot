package ca.tetervak.multiplicationquiz2.controller;

import ca.tetervak.multiplicationquiz2.model.Problem;
import ca.tetervak.multiplicationquiz2.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class QuizController {

    private final Logger log = LoggerFactory.getLogger(QuizController.class);

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @RequestMapping(value={"/", "new-problem"})
    public String newProblem(HttpSession session){
        log.trace("newProblem() is called");
        Problem problem = quizService.getRandomProblem();
        log.debug("problem = " + problem);
        session.setAttribute("problem", problem);
        return "NewProblem";
    }

    @RequestMapping( "see-answer")
    public String seeAnswer(HttpSession session){
        log.trace("seeAnswer() is called");
        Problem problem = (Problem) session.getAttribute("problem");
        if (problem == null) {
            log.debug("The session data is not found.");
            return "SessionExpired";
        } else {
            return "SeeAnswer";
        }
    }

    @RequestMapping("try-again")
    public String tryAgain(HttpSession session){
        log.trace("tryAgain() is called");
        Problem problem = (Problem) session.getAttribute("problem");
        if (problem == null) {
            log.debug("The session data is not found.");
            return "SessionExpired";
        } else {
            return "TryAgain";
        }
    }

    @RequestMapping("check-answer")
    public String checkAnswer(
            @RequestParam String userAnswer,
            HttpSession session,
            Model model){
        log.trace("checkAnswer() is called");
        log.debug("userAnswer=" + userAnswer);
        Problem problem = (Problem) session.getAttribute("problem");
        if (problem == null) {
            log.debug("The session data is not found.");
            return "SessionExpired";
        } else {
            model.addAttribute("userAnswer", userAnswer);
            switch (quizService.gradeUserAnswer(problem, userAnswer)) {
                case RIGHT_ANSWER:
                    return "RightAnswer";
                case WRONG_ANSWER:
                    return "WrongAnswer";
                case INPUT_ERROR:
                    return "BadInput";
            }
        }
        throw new AssertionError("should never be reached");
    }
}
