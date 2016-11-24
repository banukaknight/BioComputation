package dset1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author bv2-paniyanduw
 */
public class DSet1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // read in from file ... 
        // fitness by  counting ones
        // assign expecte4d val
        // rules set aswell.
        BufferedReader br = null;
        ArrayList<Item> items1 = new ArrayList<Item>();
        String sLine;
        try {
            File file = new File("H:\\BK 2016-17 (H)\\B Prac\\DSet1\\src\\dset1\\data1.txt");// reads file
            br = new BufferedReader(new FileReader(file));
            sLine = br.readLine();// skip header
            while ((sLine = br.readLine()) != null) {
                String arr[] = sLine.split(" ");
                Item it = new Item((arr[0]), arr[1]);
                items1.add(it);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Select a valid File");
        }

        System.out.println(items1);

        // Create an initial population
        Population myPop = new Population(50);

        //eval
        //printout
        for (int i = 0; i < 5; i++) {
            System.out.println(myPop.getIndividual(i) + " ");
        }

        //fitnellscalc
        String tempstring = "";
        int fitness = 0;

        for (int i = 0; i < 10; i++) {
            int count = 0;

            while (count < 60) {
                for (int j = 0; j < 6; j++) {
                    //get one set of indi from pop to string
                    tempstring += myPop.getIndividual(0).getGene(count);
                    count++;
                }
                if (tempstring == items1.get(0).toString()) {
                    fitness++;
                }
            }
        }

        System.out.println(fitness);

    }

}
