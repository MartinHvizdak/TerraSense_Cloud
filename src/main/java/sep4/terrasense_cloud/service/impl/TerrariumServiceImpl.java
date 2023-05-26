package sep4.terrasense_cloud.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sep4.terrasense_cloud.database.repository.CustomerRepository;
import sep4.terrasense_cloud.database.repository.TerrariumRepository;
import sep4.terrasense_cloud.model.Customer;
import sep4.terrasense_cloud.model.Terrarium;
import sep4.terrasense_cloud.model.TerrariumDTO;
import sep4.terrasense_cloud.service.services.TerrariumService;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
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
            terrariums.forEach((terrarium) -> terrariumDTOS.add(new TerrariumDTO(terrarium)));
            return terrariumDTOS;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TerrariumDTO createTerrarium(TerrariumDTO terrarium, String email){
        try {
            Customer customer = customerRepository.findById(email).get();
            Terrarium findTerrarium  = terrariumRepository.findById(1L).get();
            findTerrarium.setMinCO2(terrarium.getMinCO2());
            findTerrarium.setMaxCO2(terrarium.getMaxCO2());
            findTerrarium.setMinHumidity(terrarium.getMinHumidity());
            findTerrarium.setMaxHumidity(terrarium.getMaxHumidity());
            findTerrarium.setMinTemperature(terrarium.getMinTemperature());
            findTerrarium.setMaxTemperature(terrarium.getMaxTemperature());
            findTerrarium.setUser(customer);
            terrariumRepository.save(findTerrarium);
            return new TerrariumDTO(findTerrarium);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
            return new TerrariumDTO(new Terrarium());
        }
    }

    @Transactional
    @Override
    public void alterTerrarium(String email, TerrariumDTO terrarium){
        try {
            Customer customer = customerRepository.findById(email).get();

            Terrarium findTerrarium = terrariumRepository.getTerrariumByCustomerAndId(customer, 1L);

            findTerrarium.setName(terrarium.getName());
            findTerrarium.setMinCO2(terrarium.getMinCO2());
            findTerrarium.setMaxCO2(terrarium.getMaxCO2());
            findTerrarium.setMinHumidity(terrarium.getMinHumidity());
            findTerrarium.setMaxHumidity(terrarium.getMaxHumidity());
            findTerrarium.setMinTemperature(terrarium.getMinTemperature());
            findTerrarium.setMaxTemperature(terrarium.getMaxTemperature());

            terrariumRepository.alterTerrarium(findTerrarium.getId(), findTerrarium.getName(),
                    findTerrarium.getMinTemperature(), findTerrarium.getMaxTemperature() , findTerrarium.getMinHumidity(),
            findTerrarium.getMaxHumidity(), findTerrarium.getMinCO2(), findTerrarium.getMaxCO2());
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }

    @Transactional
    @Override
    public void deleteTerrarium(String email, Long terrariumId){
        try {
            Customer customer = customerRepository.findById(email).get();

            Terrarium findTerrarium = terrariumRepository.getTerrariumByCustomerAndId(customer, terrariumId);

            terrariumRepository.deleteTerrarium(findTerrarium.getId());
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }
}
