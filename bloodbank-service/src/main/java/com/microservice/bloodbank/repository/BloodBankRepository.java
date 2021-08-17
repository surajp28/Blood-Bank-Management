package com.microservice.bloodbank.repository;

import com.microservice.bloodbank.entity.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {

    BloodBank findByBloodGroup(String bloodGroup);
}
