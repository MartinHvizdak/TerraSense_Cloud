package sep4.terrasense_cloud.service.impl;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.database.repository.AnimalRepository;
import sep4.terrasense_cloud.service.services.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService {

    AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }
}
