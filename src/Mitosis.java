import java.util.Random;

public class Mitosis {
    // Attributes
    private String[][] mitosisAllelesPassed;

    // Constructor
    public Mitosis(Animal parent) {
        this.mitosisAllelesPassed = generateAlleles(parent);
    }

    // helper method for constructor
    private String[][] generateAlleles(Animal p) { //this has an error
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

    private char passedAllelesNormal(String combo) {
        Random r = new Random();
        boolean first = r.nextBoolean();
        if (first) {
            return combo.charAt(0);
        }
        return combo.charAt(1);
    }

    private int passedAllelesSex() {
        Random r = new Random();
        boolean first = r.nextBoolean();
        if (first) {
            return 0;
        }
        return 1;
    }

    // Accessor Method
    public String[][] getMitosisAllelesPassed() { return this.mitosisAllelesPassed; }

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

    public static void main(String[] args) {
        int number = 6;
        int[] array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = i+1;
        }
        Animal test = new Animal(number, array, "Dog", false);
        System.out.println(test.getAnimalChromosomeGeneAllelesString());

        Mitosis m = new Mitosis(test);
        System.out.println(m.getMitosisAllelesPassedString());
    }

}