package dataSet2;

import java.util.Random;

public class Chromosome {

    private int[][] genes;

    private int fitness = 0;

    public Chromosome() {
    }

    public Chromosome(int sizeOfGenes, int sizeOfRules) {
        genes = new int[sizeOfGenes][sizeOfRules];
    }

    public Chromosome(Chromosome copy) {
        //save a copy of gene and fitness
        int[][] tempGenes = copy.getRules();
        genes = new int[tempGenes.length][tempGenes[0].length];
        for (int i = 0; i < tempGenes.length; i++) {
            for (int j = 0; j < tempGenes[i].length; j++) {
                genes[i][j] = tempGenes[i][j];
            }
        }

        fitness = copy.getFitness();
    }

    //randomize chromosome
    public void randGenes() {
        Random rand = new Random();
        for (int[] gene : genes) {

            for (int j = 0; j < gene.length - 1; j++) {
                gene[j] = rand.nextInt(3);
            }

            gene[gene.length - 1] = rand.nextInt(2);
        }
    }

    //mutate chromosome
    public void mutation(double mutateRate) {
        Random rand = new Random();
        for (int[] gene : genes) {
            for (int j = 0; j < gene.length - 1; j++) {
                if (mutateRate > Math.random()) {
                    gene[j] = rand.nextInt(3);
                }
            }
            if (mutateRate > Math.random()) {
                gene[gene.length - 1] = rand.nextInt(2);
            }
        }
    }

    public int[][] getRules() {
        return genes;
    }

    public void setRules(int[][] genes) {
        this.genes = genes;
    }

    public int[] getGenes(int index) {
        return genes[index];
    }

    public void setGenes(int index, int[] gene) {
        this.genes[index] = gene;
    }

    public int sizeOfGenes() {
        return genes.length;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < sizeOfGenes(); i++) {
            geneString += getGenes(i);
        }
        return geneString;
    }
}
