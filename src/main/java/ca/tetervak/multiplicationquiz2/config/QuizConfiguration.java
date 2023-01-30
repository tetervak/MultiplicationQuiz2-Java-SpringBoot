package ca.tetervak.multiplicationquiz2.config;

import ca.tetervak.multiplicationquiz2.service.QuizService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class QuizConfiguration {

    @Bean
    public QuizService provideQuizService(){
        return new QuizService(new SecureRandom());
    }
}
