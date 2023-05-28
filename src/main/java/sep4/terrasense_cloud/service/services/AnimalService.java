package sep4.terrasense_cloud.service.services;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.model.Animal;

@Service
public interface AnimalService {
    Animal addAnimal(Animal animal, Long terrariumId);
    void alterAnimal(Animal animal);
    void deleteAnimal(Long animalId);
}
