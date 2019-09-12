package pl.filiphagno;

public interface Game {
    int getNumber();

    void setGuess(int guess);

    int getSmallest();

    int getRemainingGuesses();

    void reset();

    int getGuess();

    int getLargest();

    int getGuessCount();

    void check();

    boolean isValidNumberRange();

    boolean isGameWon();

    boolean isGameLost();
}
