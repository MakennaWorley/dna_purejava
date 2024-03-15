//This code creates a Chromosome
import java.util.Random;

public class Chromosome {
    // Attributes of Chromosome
    private String[] chromosomeAlleles;

    private int oddsOfRecessiveGeneDefault = 50; //must be atleast 6 to work

    // Constructors
    // default for generating a chromosome with random genes, most likely to be DominateDominate
    public Chromosome(int number) {
        String[] chromosomeBank = new String[number];
        for (int i = 0; i < number; i++) {
            chromosomeBank[i] = chromosomeRandomizeAlleles(i+1);
        }
        this.chromosomeAlleles = chromosomeBank;
    }

    // generating a chromosome with specific genes
    public Chromosome(String[] alleles) {
        this.chromosomeAlleles = alleles;
    }

    // Helper Methods for the Constructors
    // creates the randomized genes
    private String chromosomeRandomizeAlleles(int number) {
        String alleleCombo;
        if (chromosomeAlleleCode(oddsOfRecessiveGeneDefault) < 6) {
            alleleCombo = chromosomeGenerateAlleleLetter(number).toLowerCase();
        } else {
            alleleCombo = chromosomeGenerateAlleleLetter(number);
        }
        // for second allele
        if (chromosomeAlleleCode(oddsOfRecessiveGeneDefault) < 6) {
            alleleCombo = alleleCombo + chromosomeGenerateAlleleLetter(number).toLowerCase();
        } else {
            alleleCombo = alleleCombo + chromosomeGenerateAlleleLetter(number);
        }

        return alleleCombo;
    }

    // calculates the chance of getting a random recessive gene
    private int chromosomeAlleleCode(int numberOfSides) {
        Random r = new Random();
        return r.nextInt(numberOfSides) + 1;
    }

    // generates the Allele letter
    private String chromosomeGenerateAlleleLetter(int letterIndex) {
        if (letterIndex < 0 || letterIndex > 26) {
            System.out.println("Invalid, error in code");
            return null;
        } else {
            char letter = (char) ('A' + letterIndex - 1);
            return Character.toString(letter);
        }
    }

    // Accessor Methods listed by return type
    public int getChromosomeNumberOfGenes() { return this.chromosomeAlleles.length; }

    public String[] getChromosomeAlleles() {
        return this.chromosomeAlleles;
    }

    // Accessor Methods returning strings from non-string objects
    public String getChromosomeAllelesString() {
        String alleles = "";
        for (int j = 0; j < this.chromosomeAlleles.length; j++) {
            alleles = alleles + this.chromosomeAlleles[j] + " ";
        }
        return alleles;
    }

    public String getChromosomeAllelesStringSINGLE() {
        String alleles = "";
        for (int j = 0; j < this.chromosomeAlleles.length; j++) {
            alleles = this.chromosomeAlleles[j];
        }
        return alleles;
    }

    /*public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Chromosome test = new Chromosome(7);
            System.out.println(test.getChromosomeAllelesString());
        }
        String[] alleles = new String[12];
        for (int j = 0; j < 12; j++) {
            alleles[j] = "MM";
        }

        Chromosome test2 = new Chromosome(alleles);
        System.out.println(test2.getChromosomeAllelesString());
    }*/
}
