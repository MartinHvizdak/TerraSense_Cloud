package sep4.terrasense_cloud.service.impl;

import sep4.terrasense_cloud.database.repository.TerrariumRepository;
import sep4.terrasense_cloud.model.Terrarium;
import sep4.terrasense_cloud.service.services.TerrariumService;

public class TerrariumServiceImpl implements TerrariumService {

    TerrariumRepository terrariumRepository;

    public TerrariumServiceImpl(TerrariumRepository terrariumRepository) {
        this.terrariumRepository = terrariumRepository;
    }

    @Override
    public Terrarium addTerrarium(Terrarium terrarium) {
        return terrariumRepository.save(terrarium);
    }
}
