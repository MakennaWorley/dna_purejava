//This code creates an Animal
//Uses Chromosomes and Sex Chromosome to do so
import java.util.Arrays;

public class Animal {
    // Attributes of Animal
    private boolean animalBreed;
    private String animalNameOfAnimal;
    private String[][] animalGeneAlleles;

    // Constructors
    // default for generating an animal with random genes
    public Animal(int chromosomeNumber, int[] geneNumber, String animal) {
        if (geneNumber.length != chromosomeNumber) {
            System.out.println("Invalid, error in code");
        }
        String[][] animalGeneAlleleComplete = new String[chromosomeNumber][];
        for (int i = 0; i < chromosomeNumber-1; i++) {
            Chromosome chromo = new Chromosome(geneNumber[i]);
            animalGeneAlleleComplete[i] = chromo.getChromosomeAlleles();
        }
        SexChromosome sex = new SexChromosome(geneNumber[chromosomeNumber-1]);
        animalGeneAlleleComplete[chromosomeNumber-1] = sex.getChromosomeAlleles();
        this.animalNameOfAnimal = animal;
        this.animalGeneAlleles = animalGeneAlleleComplete;
        this.animalBreed = false;
    }

    public Animal(int chromosomeNumber, int[] geneNumber, String animal, String sex) {
        if (geneNumber.length != chromosomeNumber) {
            System.out.println("Invalid, error in code");
        }
        String[][] animalGeneAlleleComplete = new String[chromosomeNumber][];
        for (int i = 0; i < chromosomeNumber-1; i++) {
            Chromosome chromo = new Chromosome(geneNumber[i]);
            animalGeneAlleleComplete[i] = chromo.getChromosomeAlleles();
        }
        SexChromosome sexChromo = new SexChromosome(geneNumber[chromosomeNumber-1], sex);
        animalGeneAlleleComplete[chromosomeNumber-1] = sexChromo.getChromosomeAlleles();
        this.animalNameOfAnimal = animal;
        this.animalGeneAlleles = animalGeneAlleleComplete;
        this.animalBreed = false;
    }

    // generating an animal with specific genes
    public Animal(int chromosomeNumber,int[] geneNumber, String animal, String[][] chromosomeGene) {
        if (chromosomeGene[chromosomeNumber-1][0].equals("XY")|| chromosomeGene[chromosomeNumber-1][0].equals("XX")) {
            this.animalNameOfAnimal = animal;
            this.animalGeneAlleles = chromosomeGene;
            this.animalBreed = true;
        } else {
            System.out.println("Invalid, error in code");
            //need to replace the "sex" chromosome with the same chromosome alleles moved over by one and XX or XY placed in [0]

            this.animalNameOfAnimal = animal;
            this.animalGeneAlleles = chromosomeGene;
            this.animalBreed = true;
        }

    }

    // Accessor Methods listed by return type
    public boolean getAnimalBreed() { return this.animalBreed; }
    public int getAnimalNumberOfChromosomes() { return this.animalGeneAlleles.length; }

    public int getAnimalGeneNumber(int i) {
        return this.animalGeneAlleles[i].length;
    }

    public String getAnimalName() { return this.animalNameOfAnimal; }

    public String getAnimalChromosomeGeneSingleString(int i, int j) {
        return this.animalGeneAlleles[i][j];
    }

    public int[] getAnimalNumberOfGenes() { //check this----------------------------------------------------------------
        int[] number = new int[this.animalGeneAlleles.length];
        for (int i = 0; i < this.animalGeneAlleles.length; i++) {
            number[i] = this.animalGeneAlleles[i].length;
        }
        return number;
    }

    public String[][] getAnimalGeneAlleles() { return this.animalGeneAlleles; }

    // Accessor Method returning strings from non-string objects
    public String getAnimalChromosomeGeneAllelesString() {
        String alleles = "";
        for (int i = 0; i < this.animalGeneAlleles.length; i++) {
            for (int j = 0; j < this.animalGeneAlleles[i].length; j++) {
                alleles = alleles + this.animalGeneAlleles[i][j] + " ";
            }
            alleles = alleles + "\n";
        }
        return alleles;
    }

    public String getAnimalSex() {
        int i = this.animalGeneAlleles.length;
        return this.animalGeneAlleles[i-1][0];
    }

    public static void main(String[] args) {
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

        int[] a = new int[6];
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
    }
}
