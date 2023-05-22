package sep4.terrasense_cloud.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sep4.terrasense_cloud.service.services.FeedingScheduleService;

@CrossOrigin(origins = "https://terrasense-service-dot-terrasense.ew.r.appspot.com", maxAge = 3600)
@RestController
@RequestMapping("/feedingSchedule")
public class FeedingScheduleController {

    private FeedingScheduleService feedingScheduleService;

    public FeedingScheduleController(FeedingScheduleService feedingScheduleService) {
        this.feedingScheduleService = feedingScheduleService;
    }
}
