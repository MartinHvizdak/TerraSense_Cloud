package sep4.terrasense_cloud.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class FeedingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime time;

    private int amount;

    private double frequency;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "terrariumId")
    private Terrarium terrarium;

    public FeedingSchedule() {
    }

    public FeedingSchedule(LocalDateTime time, int amount, double frequency, Terrarium terrarium) {
        this.time = time;
        this.amount = amount;
        this.frequency = frequency;
        this.terrarium = terrarium;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public Terrarium getTerrarium() {
        return terrarium;
    }

    public void setTerrarium(Terrarium terrarium) {
        this.terrarium = terrarium;
    }
}
