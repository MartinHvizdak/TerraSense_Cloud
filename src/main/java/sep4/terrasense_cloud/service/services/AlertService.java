package sep4.terrasense_cloud.service.services;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.model.Reading;

@Service
public interface AlertService {
    boolean checkAlertTrigger(Reading reading);
}
