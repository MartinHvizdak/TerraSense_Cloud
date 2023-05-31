package sep4.terrasense_cloud.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;

    private LocalDateTime timestamp;

    private String value;

    private String severity;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "terrariumId", referencedColumnName = "id")
    private Terrarium terrarium;

    public Alert() {
    }

    public Alert(String type, LocalDateTime timestamp, String value, String severity, Terrarium terrarium) {
        this.type = type;
        this.timestamp = timestamp;
        this.value = value;
        this.severity = severity;
        this.terrarium = terrarium;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Terrarium getTerrarium() {
        return terrarium;
    }

    public void setTerrarium(Terrarium terrarium) {
        this.terrarium = terrarium;
    }
}
