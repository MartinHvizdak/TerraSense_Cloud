package sep4.terrasense_cloud.controller;

import org.springframework.web.bind.annotation.*;
import sep4.terrasense_cloud.model.Animal;
import sep4.terrasense_cloud.model.Terrarium;
import sep4.terrasense_cloud.service.services.AnimalService;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/create/")
    public Animal createAnimal(@RequestBody Animal animal, @RequestParam("terrariumId") Long terrariumId){
        return animalService.addAnimal(animal, terrariumId);
    }

    @PutMapping("/alter/")
    public void alterAnimal(@RequestBody Animal animal){
        animalService.alterAnimal(animal);
    }

    @DeleteMapping("/delete/")
    public void deleteAnimal(@RequestParam("animalId") Long animalId ){
        animalService.deleteAnimal(animalId);
    }
}
