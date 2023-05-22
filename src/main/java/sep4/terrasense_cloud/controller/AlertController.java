package sep4.terrasense_cloud.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sep4.terrasense_cloud.service.services.AlertService;

@CrossOrigin(origins = "https://terrasense-service-dot-terrasense.ew.r.appspot.com", maxAge = 3600)
@RestController
@RequestMapping("/alert")
public class AlertController {

    private AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }
}
