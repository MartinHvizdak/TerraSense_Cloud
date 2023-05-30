package sep4.terrasense_cloud.service.services;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.model.Reading;
import sep4.terrasense_cloud.model.ReadingDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public interface ReadingsService {
    ReadingDTO getReadingById(Long id);
    ArrayList<Reading> getReadings(int quantity);
    Reading addReading(Reading reading);
    ArrayList<ReadingDTO> getReadingsByTimestamps(LocalDateTime start, LocalDateTime end);
}
