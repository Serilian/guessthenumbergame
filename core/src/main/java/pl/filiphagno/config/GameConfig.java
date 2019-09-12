package pl.filiphagno.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import pl.filiphagno.GuessCount;
import pl.filiphagno.MaxNumber;
import pl.filiphagno.MinNumber;

@Configuration
@PropertySource("classpath:/config/game.properties")
@ComponentScan(basePackages = "pl.filiphagno")
public class GameConfig {

    @Value("${game.maxNumber:25}")
    private int maxNumber;

    @Value("${game.guessCount:6}")
    private int guessCount;

    @Value("${game.minNumber:4}")
    private int minNumber;

    @Bean
    @MaxNumber
    public int maxNumberToProvide() {
        return maxNumber;
    }

    @Bean
    @GuessCount
    public int guessCountTotal() {
        return guessCount;
    }

    @Bean
    @MinNumber
    public int minNumber() {
        return minNumber;
    }
}
