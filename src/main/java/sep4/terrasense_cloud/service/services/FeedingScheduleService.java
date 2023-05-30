package sep4.terrasense_cloud.service.services;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.model.FeedingSchedule;

@Service
public interface FeedingScheduleService {
    void setFeedingSchedule(FeedingSchedule feedingSchedule);
}