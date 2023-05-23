package sep4.terrasense_cloud.model;

public class LimitsDTO {
    private int minCO2;
    private int maxCO2;
    private double minHumidity;
    private double maxHumidity;
    private double minTemperature;
    private double maxTemperature;

    public LimitsDTO(int minCO2, int maxCO2, double minHumidity, double maxHumidity, double minTemperature, double maxTemperature) {
        this.minCO2 = minCO2;
        this.maxCO2 = maxCO2;
        this.minHumidity = minHumidity;
        this.maxHumidity = maxHumidity;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public int getMinCO2() {
        return minCO2;
    }

    public int getMaxCO2() {
        return maxCO2;
    }

    public double getMinHumidity() {
        return minHumidity;
    }

    public double getMaxHumidity() {
        return maxHumidity;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }
}
