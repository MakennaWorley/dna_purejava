import java.util.Random;

public class Chromosome {
    // Attributes of Chromosome
    private int chromosomeNumberOfGenes;
    private String[] chromosomeAlleles;

    private int oddsOfRecessiveGeneDefault = 50; //must be atleast 6 to work

    // Constructors
    // default for generating random genes, most likely to be DominateDominate
    public Chromosome(int number) {
        int i = 0;
        String[] chromosomeBank = new String[number];
        while (i < number) {
            chromosomeBank[i] = chromosomeRandomizeAlleles(i+1);
            i++;
        }
        this.chromosomeNumberOfGenes = number;
        this.chromosomeAlleles = chromosomeBank;
    }

    // for specific genotype
    public Chromosome(int number, String[] alleles) {
        this.chromosomeNumberOfGenes = number;
        this.chromosomeAlleles = alleles;
    }

    // helper method for default constructor
    private String chromosomeRandomizeAlleles(int number) {
        String letter = chromosomeGenerateAlleleLetter(number);
        int combo = chromosomeAlleleCode(oddsOfRecessiveGeneDefault);
        String alleleCombo;
        if (combo < 6) {
            alleleCombo = letter.toLowerCase();
        } else {
            alleleCombo = letter;
        }
        // for second allele
        combo = chromosomeAlleleCode(oddsOfRecessiveGeneDefault);
        if (combo < 6) {
            alleleCombo = alleleCombo + letter.toLowerCase();
        } else {
            alleleCombo = alleleCombo + letter;
        }
        //System.out.println(alleleCombo);

        return alleleCombo;
    }

    // helper methods for chromosomeRandomizeAlleles
    private int chromosomeAlleleCode(int numberOfSides) {
        Random r = new Random();
        return r.nextInt(numberOfSides) + 1;
    }

    // helper method to generate Allele letter
    private String chromosomeGenerateAlleleLetter(int letterIndex) {
        if (letterIndex < 0 || letterIndex > 26) {
            System.out.println("Invalid, error in code");
            return null;
        } else {
            char letter = (char) ('A' + letterIndex - 1);
            return Character.toString(letter);
        }
    }

    // Accessor Methods
    public int getChromosomeNumberOfGenes() { return this.chromosomeNumberOfGenes; }

    public String[] getChromosomeAlleles() {
        return this.chromosomeAlleles;
    }

    public String getChromosomeAllelesString() {
        String alleles = "";
        for (int j = 0; j < this.chromosomeAlleles.length; j++) {
            alleles = alleles + this.chromosomeAlleles[j] + " ";
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

        Chromosome test2 = new Chromosome(12, alleles);
        System.out.println(test2.getChromosomeAllelesString());
    }*/
}
