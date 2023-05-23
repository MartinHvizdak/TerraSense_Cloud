package sep4.terrasense_cloud.controller;

import sep4.terrasense_cloud.service.services.LimitService;

public class LimitController {

    private LimitService limitService;

    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }
}
