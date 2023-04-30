package sep4.terrasense_cloud.service.services;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.model.Reading;

import java.util.ArrayList;

@Service
public interface ReadingsService {
    Reading getReadingById(Long id);
    ArrayList<Reading> getReadings(int quantity);
    Reading addReading(Reading reading);
}
