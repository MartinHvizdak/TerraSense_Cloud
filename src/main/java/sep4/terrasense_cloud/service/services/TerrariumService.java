package sep4.terrasense_cloud.service.services;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.model.Terrarium;

@Service
public interface TerrariumService {
    Terrarium addTerrarium(Terrarium terrarium);
    Terrarium getTerraruimById(Long id);
}
