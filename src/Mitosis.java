//This code is replicating Mitosis
//Uses an Animal to do so
import java.util.Random;

public class Mitosis {
    // Attributes of Mitosis
    private String[][] mitosisAllelesPassed;

    // Constructor
    public Mitosis(Animal parent) {
        this.mitosisAllelesPassed = generateAlleles(parent);
    }

    // Helper Methods for the Constructor
    // of the two genes a parent chromosome has, only one will be passed down
    private String[][] generateAlleles(Animal p) {
        String[][] alleles = new String[p.getAnimalNumberOfChromosomes()][];
        for (int i = 0; i < p.getAnimalNumberOfChromosomes(); i++) {
            alleles[i] = new String[p.getAnimalGeneNumber(i)];
            //System.out.println(Integer.toString(i) + ": " + p.getAnimalGeneNumber(i));
        }
        //System.out.println();

        String alleleOfGene = "";
        char gene;
        for (int i = 0; i < p.getAnimalNumberOfChromosomes()-1; i++) { //everything but the sex chromosome
            for (int j = 0; j < p.getAnimalGeneNumber(i); j++) {
                //System.out.print(Integer.toString(i) + Integer.toString(j) + " ");
                alleleOfGene = p.getAnimalChromosomeGeneSingleString(i, j);
                gene = passedAllelesNormal(alleleOfGene);
                alleles[i][j] = Character.toString(gene);
            }
            //System.out.print("\n");
        }

        int sexChromosomePass = passedAllelesSex();
        for (int i = 0; i < p.getAnimalGeneNumber(p.getAnimalNumberOfChromosomes()-1); i++) {
            String allele = p.getAnimalChromosomeGeneSingleString(p.getAnimalNumberOfChromosomes()-1,i);
            char a = allele.charAt(sexChromosomePass);
            alleles[p.getAnimalNumberOfChromosomes()-1][i] = Character.toString(a);
        }

        return alleles;
    }

    // chooses to grab either the first or second gene from parent chromosome
    private char passedAllelesNormal(String combo) {
        Random r = new Random();
        boolean first = r.nextBoolean();
        if (first) {
            return combo.charAt(0);
        }
        return combo.charAt(1);
    }

    // chooses to grab either always grab the first or second gene from parent sex chromosome
    private int passedAllelesSex() {
        Random r = new Random();
        boolean first = r.nextBoolean();
        if (first) {
            return 0;
        }
        return 1;
    }

    // Accessor Method listed by return type
    public String[][] getMitosisAllelesPassed() { return this.mitosisAllelesPassed; }

    // Accessor Method returning strings from non-string objects
    public String getMitosisAllelesPassedString() {
        String alleles = "";
        for (int i = 0; i < this.mitosisAllelesPassed.length; i++) {
            for (int j = 0; j < this.mitosisAllelesPassed[i].length; j++) {
                alleles = alleles + /*Integer.toString(i) + Integer.toString(j) +*/ this.mitosisAllelesPassed[i][j] + " ";
            }
            alleles = alleles + "\n";
        }
        return alleles;
    }

    /*public static void main(String[] args) {
        int number = 4;
        int[] array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = i+1;
        }
        Animal test = new Animal(number, array, "Dog");
        System.out.println(test.getAnimalBreed());
        //System.out.println(test.getAnimalChromosomeGeneAllelesString());

        String[][] alleles = {
                {"Aa"},
                {"Aa", "Bb"},
                {"Aa", "Bb", "Cc"},
                {"XY", "Aa", "Bb", "Cc"}
        };

        Animal t = new Animal(number, array, "Dog", alleles);
        System.out.println(t.getAnimalChromosomeGeneAllelesString());
        System.out.println(t.getAnimalBreed());
        for (int i = 0; i < 5; i++) {
            Mitosis m = new Mitosis(t);
            System.out.println(m.getMitosisAllelesPassedString());
        }
    }*/
}