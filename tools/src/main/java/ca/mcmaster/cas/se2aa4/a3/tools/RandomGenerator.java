package ca.mcmaster.cas.se2aa4.a3.tools;

import java.util.Random;

public class RandomGenerator {
    private final long seed;
    private Random rand;
    private Random mainRandomController;
    public RandomGenerator(){
        //create a seed 
        rand = new Random();
        seed = rand.nextLong();
        mainRandomController = new Random(seed);
        printSeed();
    }
    public RandomGenerator(long seed){
        this.seed = seed;
        mainRandomController = new Random(seed);
        remindSeed();
    }

    public double getNextdouble(){
        return mainRandomController.nextDouble();
    }

    public long getSeed(){
        return seed;
    }


    public double getNextDouble(double lowerBound, double upperBound){
        return mainRandomController.nextDouble(lowerBound, upperBound);
    }

    public int getNextInteger(int lowerBound, int upperBound){
        return mainRandomController.nextInt(lowerBound, upperBound);
    }

    private void printSeed(){
        System.out.println("\nThe seed of this map is: " + seed);
    }

    private void remindSeed(){
        System.out.println("\nThe seed that was used for the map is: " + seed);
    }
    
}
