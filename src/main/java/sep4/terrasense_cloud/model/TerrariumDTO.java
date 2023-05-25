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
}
