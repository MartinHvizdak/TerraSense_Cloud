package sep4.terrasense_cloud.service.services;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.model.Customer;
import sep4.terrasense_cloud.model.LimitsDTO;
import sep4.terrasense_cloud.model.Terrarium;
import sep4.terrasense_cloud.model.TerrariumDTO;

import java.util.ArrayList;

@Service
public interface TerrariumService {
    Terrarium addTerrarium(Terrarium terrarium);
    Terrarium getTerraruimById(Long id);
  
    ArrayList<TerrariumDTO> getTerrariumsByEmail(String email);

    Terrarium createTerrarium(Terrarium terrarium, String email);

    void alterTerrarium(String email, Terrarium terrarium);

    void deleteTerrarium(String email, Long terrariumId);
}
