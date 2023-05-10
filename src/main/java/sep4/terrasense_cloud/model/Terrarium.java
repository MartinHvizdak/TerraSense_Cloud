package sep4.terrasense_cloud.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Terrarium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private double minTemperature;

    private double maxTemperature;

    private double minHumidity;

    private double maxHumidity;

    private int minCO2;

    private int maxCO2;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "email", referencedColumnName = "email")
    private User user;

    @OneToOne(mappedBy = "terrarium")
    private FeedingSchedule feedingSchedule;

    @OneToMany(mappedBy = "terrarium", cascade = CascadeType.ALL)
    private Set<Animal> animals;

    @OneToMany(mappedBy = "terrarium", cascade = CascadeType.ALL)
    private Set<Alert> alerts;

    @OneToMany(mappedBy = "terrarium", cascade = CascadeType.ALL)
    private Set<Reading> readings;

    public Terrarium() {
    }

    public Terrarium(String name, double minTemperature, double maxTemperature, double minHumidity, double maxHumidity, int minCO2, int maxCO2, User user) {
        this.name = name;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.minHumidity = minHumidity;
        this.maxHumidity = maxHumidity;
        this.minCO2 = minCO2;
        this.maxCO2 = maxCO2;
        this.user = user;
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

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(double minHumidity) {
        this.minHumidity = minHumidity;
    }

    public double getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(double maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    public int getMinCO2() {
        return minCO2;
    }

    public void setMinCO2(int minCO2) {
        this.minCO2 = minCO2;
    }

    public int getMaxCO2() {
        return maxCO2;
    }

    public void setMaxCO2(int maxCO2) {
        this.maxCO2 = maxCO2;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
