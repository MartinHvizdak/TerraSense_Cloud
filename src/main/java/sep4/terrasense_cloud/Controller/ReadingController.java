package sep4.terrasense_cloud.Controller;

import org.springframework.web.bind.annotation.*;
import sep4.terrasense_cloud.database.repository.ReadingsRepository;
import sep4.terrasense_cloud.model.Reading;
import sep4.terrasense_cloud.service.impl.ReadingsServiceImpl;
import sep4.terrasense_cloud.service.services.ReadingsService;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/?")
public class ReadingController {
    private ReadingsServiceImpl readingsService;
    private Reading reading;

    public ReadingController(ReadingsServiceImpl readingsService) {
        this.readingsService=readingsService;
    }

    @GetMapping("/reading")
    public ArrayList<Reading> getLastReadings(@RequestParam("quanitites") int quanitity){
        return readingsService.getReadings(quanitity);
    }

    @GetMapping("/reading/{id}")
    public Reading getReadingById(@PathVariable("id") long id){
        return readingsService.getReadingById(id);
    }
}
