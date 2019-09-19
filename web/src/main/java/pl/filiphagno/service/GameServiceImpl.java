package pl.filiphagno.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.filiphagno.Game;
import pl.filiphagno.MessageGenerator;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class GameServiceImpl implements GameService {

    private final Game game;
    private final MessageGenerator messageGenerator;

    @Autowired
    public GameServiceImpl(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    @PostConstruct
    public void init() {
        log.info("Number is : {}", game.getNumber());
        log.info("Main Message: {}", messageGenerator.getMainMessage());
    }

    @Override
    public boolean isGameOver() {
        return game.isGameLost() || game.isGameWon();
    }

    @Override
    public String getMainMessage() {
        return messageGenerator.getMainMessage();
    }

    @Override
    public String getResultMessage() {
        return messageGenerator.getResultMessage();
    }

    @Override
    public void checkGuess(int guess) {
        game.setGuess(guess);
        game.check();
    }

    @Override
    public void reset() {
        game.reset();
    }
}
