package dataSet2;

public class FitnessCal {

    static int numberOfGenerations = 1000;
    static int NumberOfRule = 10;
    static int[] solution = new int[64];

    public static void setSolution(int[] newSolution) {
        solution = newSolution;
    }

    static void setSolution(String newSolution) {
        solution = new int[newSolution.length()];
        // arry containing solution
        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i + 1);
            if (character.contains("0") || character.contains("1")) {
                solution[i] = Byte.parseByte(character);
            } else {
                solution[i] = 0;
            }
        }
    }

    //fitness calculation
    static int getFitness(Chromosome chroms, int[][] rulesData) {
        int fitness = 0;

        boolean matchedCond;

        // loop rules
        for (int[] ruleBase : rulesData) {

            for (int[] cond : chroms.getRules()) {
                matchedCond = true;

                //see if conditon match with the rule
                for (int k = 0; k < cond.length - 1; k++) {
                    if (cond[k] == ruleBase[k] || cond[k] == 2) {
                        matchedCond = true;
                    } else {
                        matchedCond = false;
                        break;
                    }
                }

                //if matched, improve fitness
                if (matchedCond) {

                    if (cond[cond.length - 1] == ruleBase[ruleBase.length - 1]) {
                        fitness++;
                    }
                    break;
                }
            }
        }

        return fitness;
    }

    //get maximum fitness
    static int getMaxFitness() {
        int maxFitness = solution.length;
        return maxFitness;
    }
}
