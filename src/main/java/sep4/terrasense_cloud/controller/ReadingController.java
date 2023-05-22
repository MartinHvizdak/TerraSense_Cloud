package sep4.terrasense_cloud.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import sep4.terrasense_cloud.model.Reading;
import sep4.terrasense_cloud.service.impl.ReadingsServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;

@CrossOrigin
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
    public ArrayList<Reading> getReadingsByTimestamps(@RequestParam("start") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
                                                      @RequestParam("end") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime endDate)
    {
        // The expected format is yyyy-MM-dd HH:mm:ss
        try{
            return readingsService.getReadingsByTimestamps(startDate, endDate);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @GetMapping("/{id}")
    public Reading getReadingById(@PathVariable("id") long id){
        return readingsService.getReadingById(id);
    }
}
