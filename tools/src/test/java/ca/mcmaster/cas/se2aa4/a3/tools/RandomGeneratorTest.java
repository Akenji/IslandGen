package ca.mcmaster.cas.se2aa4.a3.tools;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RandomGeneratorTest {
    @Test
    public void sameRandomGenerate() {
        long seed = 123456789;
        RandomGenerator random1 = new RandomGenerator(seed);
        RandomGenerator random2 = new RandomGenerator(seed);

        for (int i = 0; i < 100; i ++){
            assert(random1.getNextdouble() == random2.getNextdouble());
        }
    }

    @Test
    public void withinRange() {
        RandomGenerator random1 = new RandomGenerator();

        double randValue;
        for (int i = 0; i < 100; i ++){
            randValue = random1.getNextDouble(0,100);
            assert(randValue >= 0 && randValue < 100);
        }
    }
    
}
