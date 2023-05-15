package sep4.terrasense_cloud.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sep4.terrasense_cloud.model.Terrarium;
import sep4.terrasense_cloud.service.impl.TerrariumServiceImpl;
import sep4.terrasense_cloud.service.services.TerrariumService;

@RestController
@RequestMapping("/alert")
public class AlertController {
    private TerrariumService terrariumService;
    private Terrarium terrarium;

    public AlertController(TerrariumServiceImpl terrariumService) {
        this.terrariumService = terrariumService;
    }

    @PostMapping("/temperature/")
    public Terrarium setTemperature(@RequestParam("min") double min,
                                    @RequestParam("max") double max){
        terrarium = terrariumService.getTerraruimById(1L);
        terrarium.setMinTemperature(min);
        terrarium.setMaxTemperature(max);
        terrariumService.addTerrarium(terrarium);
        return terrarium;
    }

    @PostMapping("/humidity/")
    public Terrarium setHumidity(@RequestParam("min") double min,
                                 @RequestParam("max") double max){
        terrarium = terrariumService.getTerraruimById(1L);
        terrarium.setMinHumidity(min);
        terrarium.setMaxHumidity(max);
        terrariumService.addTerrarium(terrarium);
        return terrarium;
    }

    @PostMapping("/co2/")
    public Terrarium setCO2(@RequestParam("min") int min,
                            @RequestParam("max") int max){
        terrarium = terrariumService.getTerraruimById(1L);
        terrarium.setMinCO2(min);
        terrarium.setMaxCO2(max);
        terrariumService.addTerrarium(terrarium);
        return terrarium;
    }
}
