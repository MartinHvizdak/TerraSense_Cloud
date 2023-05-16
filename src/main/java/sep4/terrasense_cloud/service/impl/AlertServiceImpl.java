package sep4.terrasense_cloud.service.impl;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.database.repository.AlertRepository;
import sep4.terrasense_cloud.service.services.AlertService;

@Service
public class AlertServiceImpl implements AlertService {

    AlertRepository alertRepository;

    public AlertServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }
}
