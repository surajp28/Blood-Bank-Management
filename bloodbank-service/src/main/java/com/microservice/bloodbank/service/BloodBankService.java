package com.microservice.bloodbank.service;

import com.microservice.bloodbank.entity.BloodBank;
import com.microservice.bloodbank.repository.BloodBankRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BloodBankService {

    @Autowired
    private BloodBankRepository bloodBankRepository;

    public BloodBank donate(BloodBank bloodBank) {
        log.info("Inside saveEntry of BloodBankService");
        String bloodGroup = bloodBank.getBloodGroup();
        int available = bloodBankRepository.findByBloodGroup(bloodGroup).getAvailable();
        available = available + bloodBank.getAvailable();
        bloodBank.setAvailable(available);
        return bloodBankRepository.save(bloodBank);
    }

    public BloodBank receive(BloodBank bloodBank) {
        log.info("Inside saveEntry of BloodBankService");
        String bloodGroup = bloodBank.getBloodGroup();
        int available = bloodBankRepository.findByBloodGroup(bloodGroup).getAvailable();
        available = available - bloodBank.getAvailable();
        bloodBank.setAvailable(available);
        return bloodBankRepository.save(bloodBank);
    }

    public BloodBank findAvailability(String bloodGroup) {
        return bloodBankRepository.findByBloodGroup(bloodGroup);
    }

    public void initialize() {
        BloodBank ap = new BloodBank("A+",1000);
        BloodBank an = new BloodBank("A-",1000);
        BloodBank bp = new BloodBank("B+",1000);
        BloodBank bn = new BloodBank("B-",1000);
        BloodBank abp = new BloodBank("AB+",1000);
        BloodBank abn = new BloodBank("AB-",1000);
        BloodBank op = new BloodBank("O+",1000);
        BloodBank on = new BloodBank("O-",1000);
        bloodBankRepository.save(ap);
        bloodBankRepository.save(an);
        bloodBankRepository.save(bp);
        bloodBankRepository.save(bn);
        bloodBankRepository.save(abp);
        bloodBankRepository.save(abn);
        bloodBankRepository.save(op);
        bloodBankRepository.save(on);
    }
}
