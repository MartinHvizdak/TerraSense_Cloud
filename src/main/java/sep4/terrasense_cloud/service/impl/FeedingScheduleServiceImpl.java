package sep4.terrasense_cloud.service.impl;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.database.repository.FeedingScheduleRepository;
import sep4.terrasense_cloud.service.services.FeedingScheduleService;

@Service
public class FeedingScheduleServiceImpl implements FeedingScheduleService {

    FeedingScheduleRepository feedingScheduleRepository;

    public FeedingScheduleServiceImpl(FeedingScheduleRepository feedingScheduleRepository) {
        this.feedingScheduleRepository = feedingScheduleRepository;
    }
}
