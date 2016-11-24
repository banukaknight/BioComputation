/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dset1;

/**
 *
 * @author bv2-paniyanduw
 */
class Individual {
    
    static int defaultGeneLength = 60;
    private byte[] genes = new byte[defaultGeneLength];
    // Cache
    private int fitness = 0;
    
    // Create a random individual
    public void generateIndividual() {
        for (int i = 0; i < defaultGeneLength; i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
    }
    
     public byte getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }
    
    
    
    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < defaultGeneLength; i++) {
            geneString += getGene(i);
        }
        return geneString;
    }
    
    
    
    
    
    
    
}
