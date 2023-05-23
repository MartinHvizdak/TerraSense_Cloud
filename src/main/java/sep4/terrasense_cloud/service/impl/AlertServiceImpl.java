package sep4.terrasense_cloud.service.impl;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.database.repository.AlertRepository;
import sep4.terrasense_cloud.database.repository.TerrariumRepository;
import sep4.terrasense_cloud.model.Alert;
import sep4.terrasense_cloud.model.Reading;
import sep4.terrasense_cloud.model.Terrarium;
import sep4.terrasense_cloud.service.services.AlertService;

import java.time.LocalDateTime;

@Service
public class AlertServiceImpl implements AlertService {

    AlertRepository alertRepository;
    TerrariumRepository terrariumRepository;

    public AlertServiceImpl(AlertRepository alertRepository, TerrariumRepository terrariumRepository) {
        this.alertRepository = alertRepository;
        this.terrariumRepository = terrariumRepository;
    }
}
