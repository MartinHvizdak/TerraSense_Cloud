package sep4.terrasense_cloud.service.impl;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.database.repository.TerrariumRepository;
import sep4.terrasense_cloud.model.Terrarium;
import sep4.terrasense_cloud.service.services.TerrariumService;

import java.util.NoSuchElementException;

@Service
public class TerrariumServiceImpl implements TerrariumService {

    TerrariumRepository terrariumRepository;

    public TerrariumServiceImpl(TerrariumRepository terrariumRepository) {
        this.terrariumRepository = terrariumRepository;
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
}
