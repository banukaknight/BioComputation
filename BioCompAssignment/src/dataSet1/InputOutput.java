package dataSet1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputOutput {

    private final int ruleSize;
    static int Vars = 5;
    private final int[] variables;
    public int dclass;

    File f;
    FileWriter fw;

    public InputOutput(int ruleSize) {
        this.ruleSize = ruleSize;

        this.variables = new int[Vars];
    }

    public int[][] readDataFileBoolInts(String inputFile) {
        int[][] rules;
        //arraylist to store rules/data
        ArrayList<Integer[]> rulesAL = new ArrayList<>();
//new scanner to read input file
        Scanner scan = new Scanner(InputOutput.class.getResourceAsStream("/InputData/" + inputFile));
        //skip 1st line
        scan.nextLine();
//read through each line
        while (scan.hasNextLine()) {
            Integer[] newRule;

            String parts = scan.nextLine().replace(" ", "");

            //save data to an array of integer
            newRule = new Integer[parts.length()];
            for (int i = 0; i < newRule.length; i++) {
                newRule[i] = Integer.parseInt("" + parts.charAt(i));
            }
            rulesAL.add(newRule);
        }

        //arraylist to int[][]
        rules = new int[rulesAL.size()][rulesAL.get(0).length];
        for (int i = 0; i < rulesAL.size(); i++) {
            for (int j = 0; j < rulesAL.get(i).length; j++) {
                rules[i][j] = rulesAL.get(i)[j];
            }
        }
System.out.println("Input Data/Rules: ");
        //print input/rules
        for (int i = 0; i < rules.length; i++) {
            
            System.out.println(Arrays.toString(rules[i]));
        }

        return rules;
    }

    public int getVar(int index) {
        return variables[index];
    }

    public void setCond(int index, int value) {
        variables[index] = value;
    }

    public int size() {
        return variables.length;
    }

    public void setDclass(int index) {
        dclass = index;
    }

    public void createNewCSV(String pathToCreate) {
        f = new File(pathToCreate);
        try {
            if (!f.exists()) {
                f.createNewFile();
            } else {
                f.delete();
                f.createNewFile();
            }
            fw = new FileWriter(f);
            fw.append("Fitnesses");
            fw.append('\n');
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void saveBestFitnessToCSV(String bestFitness) {
        try {
            fw.append(bestFitness);
            fw.append("\n");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void closeSaveCSV() {
        try {
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
