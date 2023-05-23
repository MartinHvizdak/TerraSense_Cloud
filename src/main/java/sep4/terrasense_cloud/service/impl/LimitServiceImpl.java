package sep4.terrasense_cloud.service.impl;



import org.hibernate.query.spi.Limit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sep4.terrasense_cloud.model.LimitsDTO;
import sep4.terrasense_cloud.webSockets.WebSocketClient;

import java.time.LocalDate;


public class LimitServiceImpl {
    private WebSocketClient webSocketClient;
    @PostMapping
    public void setFeedingSchedule(@RequestBody LimitsDTO request) {
        int minCO2 = request.getMinCO2();
        int maxCO2 = request.getMaxCO2();
        double minHumidity = request.getMinHumidity();
        double maxHumidity = request.getMaxHumidity();
        double minTemperature = request.getMinTemperature();
        double maxTemperature = request.getMaxTemperature();

        // Call the WebSocketClient method to send the feeding schedule to the IoT device
        webSocketClient.setLimit(minCO2, maxCO2, minHumidity,maxHumidity,minTemperature,maxTemperature);
    }
}
