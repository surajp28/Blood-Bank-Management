package com.microservice.bloodbank.controller;

import com.microservice.bloodbank.entity.BloodBank;
import com.microservice.bloodbank.service.BloodBankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class BloodBankController {
    @Autowired
    private BloodBankService bloodBankService;

    @PostMapping("donate/")
    public BloodBank donate(@RequestBody BloodBank entry){
        log.info("Inside done method of BloodBankController");
        return bloodBankService.donate(entry);
    }

    @PostMapping("receive/")
    public BloodBank receive(@RequestBody BloodBank entry){
        log.info("Inside receive method of BloodBankContoller");
        return bloodBankService.receive(entry);
    }

    @PostMapping("/init")
    public void init(){
        bloodBankService.initialize();
    }

    @GetMapping("{id}/")
    public BloodBank findAvailability(@PathVariable("id") String bloodGroup) {
        log.info("Inside findAvailability method of BloodbankController");
        return bloodBankService.findAvailability(bloodGroup);
    }
}

