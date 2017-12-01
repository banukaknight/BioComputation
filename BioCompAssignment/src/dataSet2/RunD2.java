package dataSet2;

import dataSet1.RunD1;
import java.awt.Component;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class RunD2 {

    static int maxGenes = 1000;
    static int populationSize = 200;
    static int sizeOfGenes = 5; //num output
    static int sizeOfRules = 7; //inputindiv size
    static double mutateRate = 0.01;

    static String inputFile = "data2.txt";
    static String pathToCreate = "src/CSV_Data2/Data2,PopSize-" + populationSize + ",Genes-" + sizeOfGenes
            + ",MutateRate-" + mutateRate + ".csv";

    
    public static void main(String[] args) {

        System.out.println("Population size-" + populationSize + ",Genes-" + sizeOfGenes
            + ",MutateRate-" + mutateRate);
        
        InputOutput dm = new InputOutput(sizeOfRules);
        //Load input file data2.txt
        int[][] rulesData = dm.readDataFileBoolInts(inputFile);
        //output file
        dm.createNewCSV(pathToCreate);

        // create population
        Population myPopulation = new Population(populationSize, true, sizeOfGenes, sizeOfRules);

        int generationCount = 1;

        //make generations up to number of times specified in maxGenes
        while (generationCount <= maxGenes) {

            //callfitness function
            myPopulation.fitnessFunctionCal(rulesData);
            //print out generation & fitness
            System.out.println("Generation: " + generationCount + " BestFitness: " + myPopulation.getbestFitness());
            //output best fitness to CSV file
            dm.saveBestFitnessToCSV(myPopulation.getbestFitness() + "");

            //call selection, crossover & mutation func
            myPopulation.selection(populationSize);
            myPopulation.crossover(sizeOfGenes, sizeOfRules);
            myPopulation.mutation(mutateRate);
            myPopulation.nextGene();

            generationCount++;
        }

        System.out.println("Count: " + generationCount);
        System.out.println("Dataset-2 Result: ");
        myPopulation.getbestChromosome();

        //saveoutputdata
        dm.closeSaveCSV();

    }
}
