package pl.filiphagno;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.filiphagno.config.GameConfig;


@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("Welcome to the game");

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);

        context.close();
    }
}
