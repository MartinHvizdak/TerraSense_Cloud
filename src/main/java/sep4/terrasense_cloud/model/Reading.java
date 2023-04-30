package sep4.terrasense_cloud.model;

import jakarta.persistence.*;

@Entity
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double temperature;
    private double moisture;
    private int CO2;

    protected Reading(){}
    public Reading(double temperature, double moisture, int CO2) {
        this.temperature = temperature;
        this.moisture = moisture;
        this.CO2 = CO2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getMoisture() {
        return moisture;
    }

    public void setMoisture(double moisture) {
        this.moisture = moisture;
    }

    public int getCO2() {
        return CO2;
    }

    public void setCO2(int CO2) {
        this.CO2 = CO2;
    }
}
