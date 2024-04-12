//This code creates an Animal
//Uses Chromosomes and Sex Chromosome to do so

import java.util.Arrays;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Animal {
    // Attributes of Animal
    private String animalNameOfAnimal;
    private String animalSex;
    private String[][] animalGeneAlleles;

    private int animalPK;

    // Constructors
    // default for generating an animal with random genes
    public Animal(int chromosomeNumber, int[] geneNumber, String name, String family) {
        if (geneNumber.length != chromosomeNumber) {
            System.out.println("Invalid, error in code");
        }
        String[][] animalGeneAlleleComplete = new String[chromosomeNumber][];
        int[] chromosomePK = new int[chromosomeNumber];
        int familyPK = getFamilyPK(family);
        System.out.println(familyPK);

        for (int i = 0; i < chromosomeNumber - 1; i++) {
            Chromosome chromo = new Chromosome(geneNumber[i]);
            animalGeneAlleleComplete[i] = chromo.getChromosomeAlleles();
            chromosomePK[i] = chromo.getChromosomePK();
        }
        SexChromosome sex = new SexChromosome(geneNumber[chromosomeNumber - 1]);
        animalGeneAlleleComplete[chromosomeNumber - 1] = sex.getChromosomeAlleles();

        this.animalSex = sex.getSexChromosomeSex();
        chromosomePK[chromosomeNumber - 1] = sex.getChromosomePK();

        this.animalPK = animalToDatabase(name, this.animalSex, familyPK);
        this.animalNameOfAnimal = name;
        this.animalGeneAlleles = animalGeneAlleleComplete;

        animalChromosomeBankToDatabase(animalPK, chromosomePK);
    }

    /*public Animal(int chromosomeNumber, int[] geneNumber, String animal, String sex, String family) {
        if (geneNumber.length != chromosomeNumber) {
            System.out.println("Invalid, error in code");
        }
        String[][] animalGeneAlleleComplete = new String[chromosomeNumber][];
        for (int i = 0; i < chromosomeNumber - 1; i++) {
            Chromosome chromo = new Chromosome(geneNumber[i]);
            animalGeneAlleleComplete[i] = chromo.getChromosomeAlleles();
        }
        SexChromosome sexChromo = new SexChromosome(geneNumber[chromosomeNumber - 1], sex);
        animalGeneAlleleComplete[chromosomeNumber - 1] = sexChromo.getChromosomeAlleles();
        this.animalNameOfAnimal = animal;
        this.animalGeneAlleles = animalGeneAlleleComplete;
    }

    // generating an animal with specific genes
    public Animal(int chromosomeNumber, int[] geneNumber, String animal, String family, String[][] chromosomeGene) {
        if (chromosomeGene[chromosomeNumber - 1][0].equals("XY") || chromosomeGene[chromosomeNumber - 1][0].equals("XX")) {
            this.animalNameOfAnimal = animal;
            this.animalGeneAlleles = chromosomeGene;
        } else {
            System.out.println("Invalid, error in code");
            //need to replace the "sex" chromosome with the same chromosome alleles moved over by one and XX or XY placed in [0]

            this.animalNameOfAnimal = animal;
            this.animalGeneAlleles = chromosomeGene;
        }

    }*/

    // Helper Methods for the Constructors Database
    private int getFamilyPK(String family) {
        String sql = "SELECT familyId FROM Family WHERE familyName = ? LIMIT 1";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement t = c.prepareStatement(sql)) {
            t.setString(1, family);
            ResultSet rs = t.executeQuery();
            if (rs.next()) {
                return rs.getInt("familyId");  // Assuming the PK column name is 'id'.
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Database operation failed.");
            e.printStackTrace();
        }

        return -1;  // Return -1 if the family name is not found or any exception occurs.
    }

    private int animalToDatabase(String name, String sex, int familyPK) {
        String sql = "INSERT INTO Animal(animalName, animalSex, animalFamilyId) VALUES(?, ?, ?);";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement t = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            t.setString(1, name);
            t.setString(2, sex);
            t.setInt(3, familyPK);
            t.execute();
            try (ResultSet generatedKeys = t.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Insert operation failed.");
            e.printStackTrace();
        }
        return -1;
    }

    private void animalChromosomeBankToDatabase(int animalPK, int[] chromosomePKs) {
        String sql = "INSERT INTO ChromosomeBank(cBankAnimal, cBankChromosome) VALUES(?, ?);";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement t = c.prepareStatement(sql)) {
            for (int chromosomePK : chromosomePKs) {
                t.setInt(1, animalPK);
                t.setInt(2, chromosomePK);
                t.executeUpdate(); // Execute the update for each chromosome PK
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Insert operation failed.");
            e.printStackTrace();
        }
    }

        // Accessor Methods listed by return type
        public int getAnimalNumberOfChromosomes () {
            return this.animalGeneAlleles.length;
        }

        public int getAnimalGeneNumber ( int i ){
            return this.animalGeneAlleles[i].length;
        }

        public String getAnimalName () {
            return this.animalNameOfAnimal;
        }

        public String getAnimalChromosomeGeneSingleString ( int i, int j){
            return this.animalGeneAlleles[i][j];
        }

        public int[] getAnimalNumberOfGenes ()
        { //check this----------------------------------------------------------------
            int[] number = new int[this.animalGeneAlleles.length];
            for (int i = 0; i < this.animalGeneAlleles.length; i++) {
                number[i] = this.animalGeneAlleles[i].length;
            }
            return number;
        }

        public String[][] getAnimalGeneAlleles () {
            return this.animalGeneAlleles;
        }

        // Accessor Method returning strings from non-string objects
        public String getAnimalChromosomeGeneAllelesString () {
            String alleles = "";
            for (int i = 0; i < this.animalGeneAlleles.length; i++) {
                for (int j = 0; j < this.animalGeneAlleles[i].length; j++) {
                    alleles = alleles + this.animalGeneAlleles[i][j] + " ";
                }
                alleles = alleles + "\n";
            }
            return alleles;
        }

        public String getAnimalSex () {
            int i = this.animalGeneAlleles.length;
            return this.animalGeneAlleles[i - 1][0];
        }

    /*public static void main(String[] args) {
        int number = 6;
        int[] array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = i+1;
        }
        Animal test = new Animal(number, array, "Dog", "test");
        System.out.println(test.getAnimalChromosomeGeneAllelesString());
        //System.out.println(test.getAnimalGeneNumber(1));
        System.out.println("------");

        /*int[] a = new int[6];
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
        System.out.println("------");*/
    //}
    }
