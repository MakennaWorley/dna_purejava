//This code does NOT do mutations or parent relationship

//This code is replicating Breeding (as in how many offspring a breeding will produce)
//Uses an Animal and Fertilization to do so
public class Breed {
    // Attributes of Breed
    private Animal mother;
    private Animal father;
    private int breedOffspringNumber;
    //private int breedParentRelationship;
    //private double breedChanceOfMutation;

    // Constructor
    public Breed(Animal mother, Animal father, int number) {
        if (mother.getAnimalSex().equals("XX") && father.getAnimalSex().equals("XY")) {
            this.mother = mother;
            this.father = father;
            this.breedOffspringNumber = number;
            //this.breedParentRelationship = calculateBreedParentRelatioship();
            //this.breedChanceOfMutation = calculateBreedMutationChance();
        }
    }

}
