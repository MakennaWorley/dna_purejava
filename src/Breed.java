//This code does NOT do mutations or parent relationship

//This code is replicating Breeding (as in how many offspring a breeding will produce)
//Uses an Animal and Fertilization to do so
public class Breed {
    // Attributes of Breed
    private Animal mother;
    private Animal father;
    private int breedOffspringNumber;

    // Constructor
    public Breed(Animal mother, Animal father, int number) {
        if (mother.getAnimalSex().equals("XX") && father.getAnimalSex().equals("XY")) {
            this.mother = mother;
            this.father = father;
            this.breedOffspringNumber = number;
        }
    }

    public Animal[] breed() {
        Animal[] offspring = new Animal[this.breedOffspringNumber];
        for (int i = 0; i < this.breedOffspringNumber; i++) {
            Mitosis m = new Mitosis(this.mother);
            Mitosis f = new Mitosis(this.father);
            Fertilization pairing = new Fertilization(this.mother, m, f);
            offspring[i] = pairing.createAnimal(mother.getAnimalNumberOfChromosomes(), mother.getAnimalNumberOfGenes(), mother.getAnimalName(), pairing.getFertilizationAllelesPassed());
        }
        return offspring;
    }

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

        Animal mother = new Animal(number, array, "Dog", "XX"); // create a method in animal that allows a sex to be chosen for a particular animal
        Animal father = new Animal(number, array, "Dog", "XY");
        System.out.println(mother.getAnimalChromosomeGeneAllelesString());
        System.out.println(father.getAnimalChromosomeGeneAllelesString());

        System.out.println("--------");

        Breed pairing = new Breed(mother, father, 7);
        Animal[] offspring = pairing.breed();

        for (int i = 0; i < offspring.length; i++) {
            System.out.println(offspring[i].getAnimalChromosomeGeneAllelesString());
        }
    }*/

}
