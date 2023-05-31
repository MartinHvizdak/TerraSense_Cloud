package sep4.terrasense_cloud.controller;

import org.springframework.web.bind.annotation.*;
import sep4.terrasense_cloud.model.FeedingSchedule;
import sep4.terrasense_cloud.service.services.FeedingScheduleService;

import java.time.LocalDate;

@RestController
@RequestMapping("/feedingSchedule")
public class FeedingScheduleController {

    private FeedingScheduleService feedingScheduleService;

    public FeedingScheduleController(FeedingScheduleService feedingScheduleService) {
        this.feedingScheduleService = feedingScheduleService;
    }

    @PostMapping
    public void setFeedingSchedule(@RequestBody FeedingSchedule request) {

        // Call the WebSocketClient method to send the feeding schedule to the IoT device
        feedingScheduleService.setFeedingSchedule(request);
    }
}