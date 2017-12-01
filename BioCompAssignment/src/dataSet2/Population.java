package dataSet2;

import java.util.Arrays;
import java.util.Random;

public class Population {

    public static Chromosome[] pop;
    public static Chromosome[] mPool;

    private int bestFitness;

    static Chromosome chroms;

    Chromosome[] Chromosomes;

    // Create new population
    public Population(int populationSize, boolean initialise, int sizeOfGenes, int sizeOfRules) {
        pop = new Chromosome[populationSize];
        mPool = new Chromosome[populationSize];

        if (initialise) {
            //create chromosomes
            for (int i = 0; i < pop.length; i++) {
                pop[i] = new Chromosome(sizeOfGenes, sizeOfRules);

            }
            initialisePopulation();
        }
    }

    public void initialisePopulation() {
        for (Chromosome population : pop) {
            population.randGenes();
        }
    }

    public void fitnessFunctionCal(int[][] rulesData) {
        bestFitness = 0;

//fitness calculation & best fitness
        for (int i = 0; i < pop.length; i++) {

            int fitness = FitnessCal.getFitness(pop[i], rulesData);
            pop[i].setFitness(fitness);

            if (bestFitness < pop[i].getFitness()) {
                bestFitness = pop[i].getFitness();
            }
        }
    }

    public int getbestFitness() {
        return bestFitness;
    }

    public void selection(int sizeOfPopulation) {
        for (int i = 0; i < sizeOfPopulation; i++) {
            mPool[i] = tornamentSelection(sizeOfPopulation);
        }
    }

//crossover mutation
    public void crossover(int sizeOfGenes, int sizeOfRules) {

        for (int i = 0; i < mPool.length / 2; i++) {
            int offset = i * 2;
            Chromosome[] children = singlePointCrossover(mPool[offset], mPool[offset + 1], sizeOfGenes, sizeOfRules);
            mPool[i] = children[0];
            mPool[i + 1] = children[1];
        }
    }

    public void mutation(double MUTATION_RATE) {
        for (int i = 0; i < mPool.length; i++) {
            mPool[i].mutation(MUTATION_RATE);
        }
    }

    public void nextGene() {
        for (int i = 0; i < pop.length; i++) {
            pop[i] = new Chromosome(mPool[i]);
        }
    }

    //singlepoint crossover mutation
    private Chromosome[] singlePointCrossover(Chromosome chroms1, Chromosome chroms2, int sizeOfGenes, int sizeOfRules) {
        Chromosome[] children = new Chromosome[2];
        children[0] = new Chromosome(chroms1);
        children[1] = new Chromosome(chroms2);

        int crossingPoint = new Random().nextInt(sizeOfGenes * sizeOfRules);
        int pointer = 0;
        for (int i = 0; i < chroms1.getRules().length; i++) {
            for (int j = 0; j < chroms1.getGenes(i).length; j++) {
                if (pointer < crossingPoint) {

                    int temp = children[0].getRules()[i][j];
                    children[0].getRules()[i][j] = children[1].getRules()[i][j];
                    children[1].getRules()[i][j] = temp;
                } else {
                    break;
                }
                pointer++;
            }
            if (pointer >= crossingPoint) {
                break;
            }
        }

        return children;
    }

    private Chromosome tornamentSelection(int sizeOfPopulation) {
        Random rand = new Random();
        Chromosome mutate1 = new Chromosome(pop[rand.nextInt(sizeOfPopulation)]);
        Chromosome mutate2 = new Chromosome(pop[rand.nextInt(sizeOfPopulation)]);
        if (mutate1.getFitness() > mutate2.getFitness()) {
            return mutate1;
        } else {
            return mutate2;
        }
    }

    //find chromosome with highest fitness
    public void getbestChromosome() {
        Chromosome chromsB = pop[0];
        for (int i = 1; i < pop.length; i++) {
            if (pop[i].getFitness() > chromsB.getFitness()) {
                chromsB = pop[i];
            }
        }

        System.out.println("Best Chromosomes:");
        for (int i = 0; i < chromsB.getRules().length; i++) {
            System.out.println(Arrays.toString(chromsB.getGenes(i)));
        }
    }
}
