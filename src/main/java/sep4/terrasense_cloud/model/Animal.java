package sep4.terrasense_cloud.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String picture;

    private String gender;

    private String species;

    private LocalDate dateOfBirth;

    private int age;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "terrariumId", referencedColumnName = "id")
    private Terrarium terrarium;

    public Animal() {
    }

    public Animal(String name, String picture, String gender, String species, LocalDate dateOfBirth, int age, Terrarium terrarium) {
        this.name = name;
        this.picture = picture;
        this.gender = gender;
        this.species = species;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.terrarium = terrarium;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Terrarium getTerrarium() {
        return terrarium;
    }

    public void setTerrarium(Terrarium terrarium) {
        this.terrarium = terrarium;
    }
}
