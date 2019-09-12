package pl.filiphagno;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class GameImpl implements Game {

    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

    private NumberGenerator numberGenerator;
    private int guessCount;

    private int number;
    private int guess;
    private int smallest;
    private int largest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    @PostConstruct
    @Override
    public void reset() {
        log.info("Game PostConstruct");
        smallest = numberGenerator.getMinNumber();
        guess = 0;
        remainingGuesses = guessCount;
        largest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
        log.debug("Number is {}", number);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("In game preDestroy");
    }

    //public methods

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    public int getLargest() {
        return largest;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    @Override
    public int getGuessCount() {
        return guessCount;
    }

    @Override
    public void check() {
        checkValidNumberRange();
        if (validNumberRange) {
            if (guess > number) {
                largest = guess - 1;
            }

            if (guess < number) {
                smallest = guess + 1;
            }
        }
        remainingGuesses--;
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    private void checkValidNumberRange() {
        validNumberRange = (guess >= smallest) && (guess <= largest);
    }
}
