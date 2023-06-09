package sep4.terrasense_cloud.service.impl;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.database.repository.ReadingsRepository;
import sep4.terrasense_cloud.model.Reading;
import sep4.terrasense_cloud.model.ReadingDTO;
import sep4.terrasense_cloud.service.services.ReadingsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
@Service
public class ReadingsServiceImpl implements ReadingsService {

    ReadingsRepository readingsRepository;
    AlertServiceImpl alertService;

    public ReadingsServiceImpl(ReadingsRepository readingsRepository, AlertServiceImpl alertService)
    {
        this.readingsRepository=readingsRepository;
        this.alertService=alertService;
    }


    public ReadingDTO getReadingById(Long id) {
        try {
            return new ReadingDTO(readingsRepository.findById(id).get());
        }
        catch (NoSuchElementException e)
        {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    @Override
    public ArrayList<Reading> getReadings(int quantity) {
        ArrayList<Reading> readings=readingsRepository.findAllByOrderByTimestampDesc();
        ArrayList<Reading> ret = new ArrayList<>(0);
        for (int i=0;i<quantity;i++)
        {
            ret.add(readings.get(readings.size()-i-1));
        }
        return ret;
    }

    @Override
    public Reading addReading(Reading reading) {
        System.out.println(reading.getCO2()+" "+reading.getTemperature()+" "+reading.getHumidity());
        alertService.checkAlertTrigger(reading);
        return readingsRepository.save(reading);
    }

    @Override
    public ArrayList<ReadingDTO> getReadingsByTimestamps(LocalDateTime start, LocalDateTime end) {
        ArrayList<Reading> readings = readingsRepository.findAllByOrderByTimestampDesc();
        ArrayList<ReadingDTO> returnList = new ArrayList<>();
        for(Reading reading : readings){
            // start <= timestamp <= end
            if((!start.isAfter(reading.getTimestamp())) && !end.isBefore(reading.getTimestamp())){
                returnList.add(new ReadingDTO(reading));
            }
        }
        return returnList;
    }
}
