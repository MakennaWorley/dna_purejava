//This code does NOT check if parents are male and female

//This code is replicating Fertilization
//Uses an Animal and Mitosis to do so
public class Fertilization {
    // Attributes of Fertilization
    private String[][] fertilizationAllelesPassed;

    // Constructor
    public Fertilization(Animal parent, Mitosis parent1, Mitosis parent2) {
        int chromosomeNumber = parent.getAnimalNumberOfChromosomes();
        int[] geneNumber = parent.getAnimalNumberOfGenes();
        String animalSpecies = parent.getAnimalName();
        String[][] alleles = createChromosomes(parent1, parent2);
        this.fertilizationAllelesPassed = alleles;
    }

    // Helper Methods for the Constructor
    public String[][] createChromosomes(Mitosis p1, Mitosis p2) {
        String[][] alleles = p1.getMitosisAllelesPassed().clone();
        for (int i = 0; i < p1.getMitosisAllelesPassed().length; i++) {
            for (int j = 0; j < p1.getMitosisAllelesPassed()[i].length; j++) {
                alleles[i][j] = alleles[i][j] + p2.getMitosisAllelesPassed()[i][j];
            }
        }
        return alleles;
    }

    // method that creates the offspring of the pairing
    // needs to be moved
    public Animal createAnimal(int chromosomeNumber, int[] geneNumber, String animal, String[][] chromosomeGene) {
        return new Animal(chromosomeNumber, geneNumber, animal, chromosomeGene);
    }

    // Accessor Methods listed by return type
    public String[][] getFertilizationAllelesPassed() { return this.fertilizationAllelesPassed; }

    /*public static void main(String[] args) {
        int number = 4;
        int[] array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = i+1;
        }
        String[][] alleles1 = {
                {"aa"},
                {"aa", "bb"},
                {"aa", "bb", "cc"},
                {"XX", "aa", "bb", "cc"}
        };
        String[][] alleles2 = {
                {"AA"},
                {"AA", "BB"},
                {"AA", "BB", "CC"},
                {"XY", "AA", "BB", "CC"}
        };

        Animal t = new Animal(number, array, "Dog");
        System.out.println(t.getAnimalChromosomeGeneAllelesString());
        Animal t2 = new Animal(number, array, "Dog");
        System.out.println(t2.getAnimalChromosomeGeneAllelesString());

        Mitosis tM = new Mitosis(t);
        System.out.println(tM.getMitosisAllelesPassedString());
        Mitosis t2M = new Mitosis(t2);
        System.out.println(t2M.getMitosisAllelesPassedString());

        Fertilization f = new Fertilization(t, tM, t2M);
        Animal g = f.createAnimal(t.getAnimalNumberOfChromosomes(), t.getAnimalNumberOfGenes(), t.getAnimalName(), f.fertilizationAllelesPassed);

        System.out.println(g.getAnimalBreed());
        System.out.println(g.getAnimalName());
        System.out.println(g.getAnimalChromosomeGeneAllelesString());
    }*/
}
