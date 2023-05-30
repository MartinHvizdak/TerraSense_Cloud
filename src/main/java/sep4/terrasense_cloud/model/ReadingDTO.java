package sep4.terrasense_cloud.model;

import java.time.LocalDateTime;

public class ReadingDTO {

    private long id;
    private double temperature;
    private double humidity;
    private int CO2;
    private LocalDateTime timestamp;
    private int terrariumId;

    public ReadingDTO(long id, double temperature, double humidity, int CO2, LocalDateTime timestamp, int terrariumId) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.CO2 = CO2;
        this.timestamp = timestamp;
        this.terrariumId = terrariumId;
    }
    public ReadingDTO(Reading reading) {
        this.id = reading.getId();
        this.temperature = reading.getTemperature();
        this.humidity = reading.getHumidity();
        this.CO2 = reading.getCO2();
        this.timestamp = reading.getTimestamp();
        this.terrariumId =(int)reading.getTerrarium().getId();
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

    public int getTerrariumId() {
        return terrariumId;
    }

    public void setTerrariumId(int terrariumId) {
        this.terrariumId = terrariumId;
    }
}
