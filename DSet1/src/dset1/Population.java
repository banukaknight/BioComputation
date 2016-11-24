/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dset1;

 
public class Population {
    
    
 
Individual[] individuals;
    public  Population(int populationSize) {
        
          individuals = new Individual[populationSize];
        // Initialise population
        
            // Loop and create individuals
            for (int i = 0; i < populationSize; i++) {
                Individual newIndividual = new Individual();
                newIndividual.generateIndividual();
                individuals[i] = newIndividual;
            }
        
        
        
    }
  
    /* Getters */
    public Individual getIndividual(int index) {
        return individuals[index];
    }
    
    
}
