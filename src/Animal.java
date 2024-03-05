//This code creates an Animal
//Uses Chromosomes and Sex Chromosome to do so
import java.util.Arrays;

public class Animal {
    // Attributes
    private boolean animalBreed;
    private int animalNumberOfChromosomes;
    private int[] animalNumberOfGenes;
    private String animalNameOfAnimal;
    private String[][] animalGeneAlleles;

    // Constructors
    // default for generating random genes
    public Animal(int chromosomeNumber, int[] geneNumber, String animal) {
        if (geneNumber.length != chromosomeNumber) {
            System.out.println("Invalid, error in code");
        }
        String[][] animalGeneAlleleComplete = new String[chromosomeNumber][];
        for (int i = 0; i < chromosomeNumber-1; i++) {
            int index = geneNumber[i];
            Chromosome chromo = new Chromosome(index);
            animalGeneAlleleComplete[i] = chromo.getChromosomeAlleles();
        }
        int gene = geneNumber[chromosomeNumber-1];
        SexChromosome sex = new SexChromosome(gene);
        animalGeneAlleleComplete[chromosomeNumber-1] = sex.getChromosomeAlleles();
        this.animalNumberOfChromosomes = chromosomeNumber;
        this.animalNumberOfGenes = geneNumber;
        this.animalNameOfAnimal = animal;
        this.animalGeneAlleles = animalGeneAlleleComplete;
        this.animalBreed = false;
    }

    // for specific animal
    public Animal(int chromosomeNumber,int[] geneNumber, String animal, String[][] chromosomeGene) {
        if (chromosomeGene[chromosomeNumber-1][0].equals("XY")|| chromosomeGene[chromosomeNumber-1][0].equals("XX")) {
            this.animalNumberOfChromosomes = chromosomeNumber;
            this.animalNumberOfGenes = geneNumber;
            this.animalNameOfAnimal = animal;
            this.animalGeneAlleles = chromosomeGene;
            this.animalBreed = true;
        } else {
            System.out.println("Invalid, error in code");
            /*int[] gene = geneNumber.clone();
            geneNumber[chromosomeNumber-1] = geneNumber[chromosomeNumber-1] + 1; //updates geneNumber

            SexChromosome sex = new SexChromosome(1); //get sex Alleles
            String sexAllele = sex.getChromosomeAllelesStringSINGLE();

            String[][] clone = new String[chromosomeNumber][];
            for (int i = 0; i < chromosomeNumber; i++) {
                clone[i] = new String[geneNumber[i]];
            }

            for (int i = 0; i < chromosomeNumber; i++) { //copy into clone
                for (int j = 0; j < gene[i]; j++) {
                    clone[i][j] = chromosomeGene[i][j];
                }
            }

            for (int i = geneNumber[chromosomeNumber-1]-1; i > 0; i--) {
                clone[chromosomeNumber-1][i] = clone[chromosomeNumber-1][i-1];
            }
            clone[chromosomeNumber-1][0] = sexAllele;*/


            this.animalNumberOfChromosomes = chromosomeNumber;
            this.animalNumberOfGenes = geneNumber;
            this.animalNameOfAnimal = animal;
            this.animalGeneAlleles = chromosomeGene;
            //this.animalGeneAlleles = clone;
            this.animalBreed = true;
        }

    }

    // Accessor Methods
    public int getAnimalNumberOfChromosomes() { return this.animalNumberOfChromosomes; }

    public String getAnimalName() { return this.animalNameOfAnimal; }

    public String[][] getAnimalGeneAlleles() { return this.animalGeneAlleles; }

    public String getAnimalChromosomeGeneAllelesString() {
        String alleles = "";
        for (int i = 0; i < this.animalGeneAlleles.length; i++) {
            for (int j = 0; j < this.animalGeneAlleles[i].length; j++) {
                alleles = alleles + /*Integer.toString(i) + Integer.toString(j) +*/ this.animalGeneAlleles[i][j] + " "; //----------------------------------
            }
            alleles = alleles + "\n";
        }
        return alleles;
    }

    public String getAnimalChromosomeGeneSingleString(int i, int j) {
        return this.animalGeneAlleles[i][j];
    }

    public boolean getAnimalBreed() { return this.animalBreed; }

    public int[] getAnimalNumberOfGenes() { return this.animalNumberOfGenes; }

    public String getAnimalSex() {
        int i = this.animalGeneAlleles.length;
        return this.animalGeneAlleles[i-1][0];
    }

    public int getAnimalGeneNumber(int i) {
        return this.animalGeneAlleles[i].length;
    }

    /*public static void main(String[] args) {
        int number = 6;
        int[] array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = i+1;
        }
        Animal test = new Animal(number, array, "Dog");
        System.out.println(test.getAnimalChromosomeGeneAllelesString());
        System.out.println(test.getAnimalBreed());
        //System.out.println(test.getAnimalGeneNumber(1));
        System.out.println("------");

        int[]a = new int[6];
        a[0] = 3;
        a[1] = 2;
        a[2] = 4;
        a[3] = 3;
        a[4] = 2;
        a[5] = 2;

        String[][] g = new String[number][];
        for (int i = 0; i < a.length; i++) {
            g[i] = new String[a[i]];
            Arrays.fill(g[i], "XX");
        }

        Animal t = new Animal(number, a, "Bunny", g);
        System.out.println(t.getAnimalChromosomeGeneAllelesString());
        System.out.println(t.getAnimalSex());
        System.out.println(t.getAnimalBreed());
        System.out.println("------");
    }*/
}
