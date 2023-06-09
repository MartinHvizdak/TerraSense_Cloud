package sep4.terrasense_cloud.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double temperature;
    private double humidity;
    private int CO2;

    @Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime timestamp;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "terrariumId", referencedColumnName = "id")
    private Terrarium terrarium;

    public Reading(){

    }
    public Reading(double temperature, double humidity, int CO2) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.CO2 = CO2;
        this.timestamp = LocalDateTime.now();
    }

    public Reading(double temperature, double humidity, int CO2, LocalDateTime timestamp, Terrarium terrarium) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.CO2 = CO2;
        this.timestamp = timestamp;
        this.terrarium = terrarium;
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

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getCO2() {
        return CO2;
    }

    public void setCO2(int CO2) {
        this.CO2 = CO2;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Terrarium getTerrarium() {
        return terrarium;
    }

    public void setTerrarium(Terrarium terrarium) {
        this.terrarium = terrarium;
    }
}
