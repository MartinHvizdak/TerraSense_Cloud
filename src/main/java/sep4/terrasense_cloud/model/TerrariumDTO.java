package sep4.terrasense_cloud.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Set;

public class TerrariumDTO {
    private long id;
    private String name;
    private double minTemperature;
    private double maxTemperature;
    private double minHumidity;
    private double maxHumidity;
    private int minCO2;
    private int maxCO2;
    private FeedingSchedule feedingSchedule;
    private Set<Animal> animals;
    private Set<Alert> alerts;
    private Set<Reading> readings;

    public TerrariumDTO(long id, String name, double minTemperature, double maxTemperature, double minHumidity, double maxHumidity, int minCO2, int maxCO2, FeedingSchedule feedingSchedule, Set<Animal> animals, Set<Alert> alerts, Set<Reading> readings) {
        this.id = id;
        this.name = name;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.minHumidity = minHumidity;
        this.maxHumidity = maxHumidity;
        this.minCO2 = minCO2;
        this.maxCO2 = maxCO2;
        this.feedingSchedule = feedingSchedule;
        this.animals = animals;
        this.alerts = alerts;
        this.readings = readings;
    }

    public TerrariumDTO(Terrarium terrarium){
        this.id = terrarium.getId();
        this.name = terrarium.getName();
        this.minTemperature = terrarium.getMinTemperature();
        this.maxTemperature = terrarium.getMaxTemperature();
        this.minHumidity = terrarium.getMinHumidity();
        this.maxHumidity = terrarium.getMaxHumidity();
        this.minCO2 = terrarium.getMinCO2();
        this.maxCO2 = terrarium.getMaxCO2();
        this.feedingSchedule = terrarium.getFeedingSchedule();
        this.animals = terrarium.getAnimals();
        this.alerts = terrarium.getAlerts();
        this.readings = terrarium.getReadings();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getMinHumidity() {
        return minHumidity;
    }

    public double getMaxHumidity() {
        return maxHumidity;
    }

    public int getMinCO2() {
        return minCO2;
    }

    public int getMaxCO2() {
        return maxCO2;
    }

    public FeedingSchedule getFeedingSchedule() {
        return feedingSchedule;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public Set<Alert> getAlerts() {
        return alerts;
    }

    public Set<Reading> getReadings() {
        return readings;
    }
}
