package pl.filiphagno;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Getter
public class NumberGeneratorImpl implements NumberGenerator {

    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber,@MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    private final Random random = new Random();
    private final int maxNumber;
    private final int minNumber;

    @Override
    public int next() {
        return random.nextInt(maxNumber - minNumber) + minNumber;
    }

}
