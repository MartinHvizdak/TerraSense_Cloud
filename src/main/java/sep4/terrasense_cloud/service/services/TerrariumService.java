package sep4.terrasense_cloud.service.services;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.model.LimitsDTO;
import sep4.terrasense_cloud.model.Terrarium;
import sep4.terrasense_cloud.model.TerrariumDTO;

import java.util.ArrayList;

@Service
public interface TerrariumService {
    Terrarium addTerrarium(Terrarium terrarium);
    Terrarium getTerrariumById(Long id);
  
    ArrayList<TerrariumDTO> getTerrariumsByEmail(String email);

    TerrariumDTO createTerrarium(TerrariumDTO terrarium, String email);

    void alterTerrarium(String email, TerrariumDTO terrarium);

    void deleteTerrarium(String email, Long terrariumId);

    void setLimits(LimitsDTO request);

    void setTempLimits(double min, double max);


}
