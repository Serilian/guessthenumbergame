package pl.filiphagno;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class MessageGeneratorImpl implements MessageGenerator {

    private Game game;
    private final MessageSource messageSource;
    private final String MAIN_MESSAGE = "game.main.message" ;

    @Autowired
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE, game.getSmallest(), game.getLargest());
    }

    @Override
    public String getResultMessage() {
        if (game.isGameWon()) {
            return "You Won!! " + "The number was: " + game.getNumber();
        } else if (game.isGameLost()) {
            return "You Lost!! " + "The number was: " + game.getNumber();
        } else if (!game.isValidNumberRange()) {
            return "Invalid number range";
        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            return "What is your first guess?";
        } else {
            String direction = "Lower";
            if (game.getGuess() < game.getNumber()) {
                direction = "Higher";
            }
            return direction + "! You have " + game.getRemainingGuesses() + " guesses left";
        }
    }

    @PostConstruct
    private void init() {
        log.info("Instantiated Message Generator, game is {}", game);
    }

    private String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

}
