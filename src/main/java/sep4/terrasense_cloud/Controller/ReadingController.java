package sep4.terrasense_cloud.Controller;

import org.springframework.web.bind.annotation.*;
import sep4.terrasense_cloud.model.Reading;
import sep4.terrasense_cloud.service.impl.ReadingsServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/reading")
public class ReadingController {
    private ReadingsServiceImpl readingsService;
    private Reading reading;

    public ReadingController(ReadingsServiceImpl readingsService) {
        this.readingsService=readingsService;
    }
/*
    @GetMapping("/")
    public ArrayList<Reading> getLastReadings(@RequestParam("quantity") int quantity){
        return readingsService.getReadings(quantity);
    }
*/
    @GetMapping("/")
    @ResponseBody
    public ArrayList<Reading> getReadingsByTimestamps(@RequestParam("start") String startDate,
                                                      @RequestParam("end") String endDate)
    {
        System.out.println(startDate);
        System.out.println(endDate);
        // The expected format is YYYY-mm-dd
        return readingsService.getReadingsByTimestamps(LocalDate.parse(startDate), LocalDate.parse(endDate));
    }


    @GetMapping("/{id}")
    public Reading getReadingById(@PathVariable("id") long id){
        return readingsService.getReadingById(id);
    }
}
