package sep4.terrasense_cloud.service.impl;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.database.repository.ReadingsRepository;
import sep4.terrasense_cloud.model.Reading;
import sep4.terrasense_cloud.service.services.ReadingsService;

import java.util.ArrayList;
import java.util.NoSuchElementException;
@Service
public class ReadingsServiceImpl implements ReadingsService {

    ReadingsRepository readingsRepository;

    public ReadingsServiceImpl(ReadingsRepository readingsRepository)
    {
        this.readingsRepository=readingsRepository;
    }


    public Reading getReadingById(Long id) {
        try {
            return readingsRepository.findById(id).get();
        }
        catch (NoSuchElementException e)
        {
            System.out.println(e.getStackTrace());
            return new Reading(-1,-1,-1);
        }
    }

    @Override
    public ArrayList<Reading> getReadings(int quantity) {
        ArrayList<Reading> readings=readingsRepository.findAllByOrderByTimeDesc();
        ArrayList<Reading> ret = new ArrayList<>(0);
        for (int i=0;i<quantity;i++)
        {
            ret.add(readings.get(readings.size()-i-1));
        }
        return ret;
    }

    @Override
    public Reading addReading(Reading reading) {
        return readingsRepository.save(reading);
    }
}
