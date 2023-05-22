package sep4.terrasense_cloud.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sep4.terrasense_cloud.service.services.AnimalService;

@CrossOrigin
@RestController
@RequestMapping("/animal")
public class AnimalController {

    private AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }
}
