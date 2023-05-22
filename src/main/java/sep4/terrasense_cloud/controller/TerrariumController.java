package sep4.terrasense_cloud.controller;

import org.springframework.web.bind.annotation.*;
import sep4.terrasense_cloud.model.LimitsDTO;
import sep4.terrasense_cloud.model.Terrarium;
import sep4.terrasense_cloud.service.impl.TerrariumServiceImpl;
import sep4.terrasense_cloud.service.services.TerrariumService;

@RestController
@RequestMapping("/terrarium")
public class TerrariumController {
    private TerrariumService terrariumService;
    private Terrarium terrarium;

    public TerrariumController(TerrariumServiceImpl terrariumService) {
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

    @PostMapping("/cotwo/")
    public Terrarium setCO2(@RequestParam("min") int min,
                            @RequestParam("max") int max){
        terrarium = terrariumService.getTerraruimById(1L);
        terrarium.setMinCO2(min);
        terrarium.setMaxCO2(max);
        terrariumService.addTerrarium(terrarium);
        return terrarium;
    }

    @PostMapping("/limits/")
    public Terrarium setLimits(@RequestBody()LimitsDTO limits){
        terrarium = terrariumService.getTerraruimById(1L);
        terrarium.setMinCO2(limits.getMinCO2());
        terrarium.setMaxCO2(limits.getMaxCO2());
        terrarium.setMinHumidity(limits.getMinHumidity());
        terrarium.setMaxHumidity(limits.getMaxHumidity());
        terrarium.setMinTemperature(limits.getMinTemperature());
        terrarium.setMaxTemperature(limits.getMaxTemperature());
        terrariumService.addTerrarium(terrarium);
        return terrarium;
    }
}
