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

    @Override
    public boolean checkAlertTrigger(Reading reading) {
        boolean output = false;
        try{
            Terrarium terrarium = terrariumRepository.getReferenceById(1L);
            if (reading.getCO2() > terrarium.getMaxCO2()){
                alertRepository.save(new Alert("CO2", LocalDateTime.now(), String.valueOf(reading.getCO2()), "high", terrarium));
                output = true;
            } else if (reading.getCO2() < terrarium.getMinCO2()) {
                alertRepository.save(new Alert("CO2", LocalDateTime.now(), String.valueOf(reading.getCO2()), "high", terrarium));
                output = true;
            }
            if (reading.getHumidity() > terrarium.getMaxHumidity()){
                alertRepository.save(new Alert("Humidity", LocalDateTime.now(), String.valueOf(reading.getHumidity()), "high", terrarium));
                output = true;
            }
            else if (reading.getHumidity() < terrarium.getMinHumidity()){
                alertRepository.save(new Alert("Humidity", LocalDateTime.now(), String.valueOf(reading.getHumidity()), "high", terrarium));
                output = true;
            }
            if (reading.getTemperature() > terrarium.getMaxTemperature()){
                alertRepository.save(new Alert("Temperature", LocalDateTime.now(), String.valueOf(reading.getTemperature()), "high", terrarium));
                output = true;
            }
            else if (reading.getTemperature() < terrarium.getMaxTemperature()){
                alertRepository.save(new Alert("Temperature", LocalDateTime.now(), String.valueOf(reading.getTemperature()), "high", terrarium));
                output = true;
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return output;
    }
}
