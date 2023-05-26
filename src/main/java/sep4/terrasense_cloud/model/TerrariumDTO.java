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

    public TerrariumDTO(long id, String name, double minTemperature, double maxTemperature, double minHumidity, double maxHumidity, int minCO2, int maxCO2) {
        this.id = id;
        this.name = name;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.minHumidity = minHumidity;
        this.maxHumidity = maxHumidity;
        this.minCO2 = minCO2;
        this.maxCO2 = maxCO2;
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
}
