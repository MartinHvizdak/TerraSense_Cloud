package sep4.terrasense_cloud.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sep4.terrasense_cloud.database.repository.FeedingScheduleRepository;
import sep4.terrasense_cloud.model.FeedingSchedule;
import sep4.terrasense_cloud.model.Terrarium;
import sep4.terrasense_cloud.service.services.FeedingScheduleService;
import sep4.terrasense_cloud.webSockets.WebSocketClient;

import java.time.LocalDate;

@Service
public class FeedingScheduleServiceImpl implements FeedingScheduleService {

    FeedingScheduleRepository feedingScheduleRepository;
    private WebSocketClient webSocketClient;


    public FeedingScheduleServiceImpl(WebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }

    public void setFeedingSchedule(FeedingSchedule feedingSchedule)
    {
        LocalDate time = LocalDate.from(feedingSchedule.getTime());
        int amount= feedingSchedule.getAmount();
        double frequency = feedingSchedule.getFrequency();
        Terrarium terrarium = feedingSchedule.getTerrarium();
        webSocketClient.setFeedingSchedule(time,amount,frequency,terrarium);
    }
}