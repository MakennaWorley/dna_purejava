//This code creates a Sex Chromosome
import java.util.Random;

public class SexChromosome {
    // Attributes of Sex Chromosome
    private String[] chromosomeAlleles;

    private int oddsOfRecessiveGeneDefault = 50; //must be atleast 6 to work

    // Constructors
    // default for generating a chromosome with random genes, most likely to be DominateDominate
    public SexChromosome(int number) {
        String[] chromosomeBank = new String[number];
        chromosomeBank[0] = chromosomeRandomizeSex();
        for (int i = 1; i < number; i++) {
            chromosomeBank[i] = chromosomeRandomizeAlleles(i);
        }
        this.chromosomeAlleles = chromosomeBank;
    }

    // generating a sex chromosome with specific sex
    public SexChromosome(int number, String sex) {
        String [] alleles = new String[number];
        if (sex.equals("XX")) {
            alleles[0] = "XX";
        } else {
            alleles[0] = "XY";
        }
        for (int i = 1; i < number; i++) {
            alleles[i] = chromosomeRandomizeAlleles(i);
        }
        this.chromosomeAlleles = alleles;
    }

    // generating a sex chromosome with specific genes
    public SexChromosome(String[] alleles) {
        if (alleles[0].equals("XY") || alleles[0].equals("XX")) {
            this.chromosomeAlleles = alleles;
        } else {
            System.out.println("Invalid, error in code");
            alleles[0] = chromosomeRandomizeSex();
            this.chromosomeAlleles = alleles;
        }
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

    // generates the sex of the chromosome
    private String chromosomeRandomizeSex() {
        Random r = new Random();
        if (r.nextBoolean()) {
            return "XX";
        } else {
            return "XY";
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
            SexChromosome test = new SexChromosome(4);
            System.out.println(test.getChromosomeAllelesString());
        }
        System.out.println();
        String[] alleles = new String[12];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                alleles[j] = "MM";
            }
        }
        SexChromosome test2 = new SexChromosome(alleles);
        System.out.println(test2.getChromosomeAllelesString());
        System.out.println();
        alleles[0] = "XX";
        SexChromosome test3 = new SexChromosome(alleles);
        System.out.println(test3.getChromosomeAllelesString());
    }*/
}
