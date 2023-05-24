package sep4.terrasense_cloud.service.impl;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.database.repository.CustomerRepository;
import sep4.terrasense_cloud.database.repository.TerrariumRepository;
import sep4.terrasense_cloud.model.Customer;
import sep4.terrasense_cloud.model.Terrarium;
import sep4.terrasense_cloud.model.TerrariumDTO;
import sep4.terrasense_cloud.service.services.TerrariumService;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class TerrariumServiceImpl implements TerrariumService {

    TerrariumRepository terrariumRepository;
    CustomerRepository customerRepository;

    public TerrariumServiceImpl(TerrariumRepository terrariumRepository, CustomerRepository customerRepository) {
        this.terrariumRepository = terrariumRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Terrarium addTerrarium(Terrarium terrarium) {
        return terrariumRepository.save(terrarium);
    }

    @Override
    public Terrarium getTerraruimById(Long id) {
        try{
            return terrariumRepository.findById(id).get();
        }catch (NoSuchElementException e){
            System.out.println(e.getStackTrace());
            return new Terrarium();
        }
    }

    @Override
    public ArrayList<TerrariumDTO> getTerrariumsByEmail(String email) {
        try{
            ArrayList<TerrariumDTO> terrariumDTOS = new ArrayList<>();
            Customer customer = customerRepository.getReferenceById(email);
            Set<Terrarium> terrariums = customer.getTerrariums();
            terrariums.forEach((e) -> terrariumDTOS.add(new TerrariumDTO(e.getId(), e.getName(), e.getMinTemperature(),
                    e.getMaxTemperature(), e.getMinHumidity(), e.getMaxHumidity(), e.getMinCO2(), e.getMaxCO2(),
                    e.getFeedingSchedule(), e.getAnimals(), e.getAlerts(), e.getReadings())));
            return terrariumDTOS;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
