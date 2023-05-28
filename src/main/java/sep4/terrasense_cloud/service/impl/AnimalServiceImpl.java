package sep4.terrasense_cloud.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sep4.terrasense_cloud.database.repository.AnimalRepository;
import sep4.terrasense_cloud.database.repository.TerrariumRepository;
import sep4.terrasense_cloud.model.Animal;
import sep4.terrasense_cloud.model.Terrarium;
import sep4.terrasense_cloud.service.services.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService {

    AnimalRepository animalRepository;
    TerrariumRepository terrariumRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository, TerrariumRepository terrariumRepository) {
        this.animalRepository = animalRepository;
        this.terrariumRepository = terrariumRepository;
    }

    @Override
    public Animal addAnimal(Animal animal, Long terrariumId){
        try {
            Terrarium terrarium = terrariumRepository.findById(terrariumId).get();
            animal.setTerrarium(terrarium);
            return animalRepository.save(animal);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
            return new Animal();
        }
    }

    @Transactional
    @Override
    public void alterAnimal(Animal animal){
        try {
            Animal animal1 = animalRepository.findById(animal.getId()).get();
            animal1.setName(animal.getName());
            animal1.setPicture(animal.getPicture());
            animal1.setGender(animal.getGender());
            animal1.setSpecies(animal.getSpecies());
            animal1.setDateOfBirth(animal.getDateOfBirth());
            animalRepository.alterAnimal(animal1.getId(), animal1.getName(),
                    animal1.getPicture(), animal1.getGender(), animal1.getSpecies(), animal1.getDateOfBirth());
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }

    @Transactional
    @Override
    public void deleteAnimal(Long animalId){
        try {
            animalRepository.deleteById(animalId);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }
}
