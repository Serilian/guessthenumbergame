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
    private final String RESULT_WON = "game.result.won" ;
    private final String RESULT_LOST = "game.result.lost" ;
    private final String RESULT_INVALID_RANGE = "game.result.invalid-range" ;
    private final String RESULT_FIRST_GUESS = "game.result.first-guess" ;
    private final String RESULT_HIGHER = "game.higher" ;
    private final String RESULT_LOWER = "game.lower" ;
    private final String RESULT_GUESSES_LEST = "game.guesses-left" ;

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
            return getMessage(RESULT_WON, game.getNumber());
        } else if (game.isGameLost()) {
            return getMessage(RESULT_LOST, game.getNumber());
        } else if (!game.isValidNumberRange()) {
            return getMessage(RESULT_INVALID_RANGE);
        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            return getMessage(RESULT_FIRST_GUESS);
        } else {
            String direction = getMessage(RESULT_LOWER);
            if (game.getGuess() < game.getNumber()) {
                direction = getMessage(RESULT_HIGHER);
            }
            return direction + getMessage(RESULT_GUESSES_LEST, game.getRemainingGuesses());
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
