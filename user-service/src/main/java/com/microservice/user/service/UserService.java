package com.microservice.user.service;

import com.microservice.user.VO.BloodBank;
import com.microservice.user.entity.User;
import com.microservice.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser method of UserService");
        return userRepository.save(user);
    }

    public String donate(String emailId, int pint) {
        BloodBank bank = restTemplate.getForObject("http://localhost:8083/donate/", BloodBank.class );
        int available = bank.getAvailable();
        User user = userRepository.findByEmailId(emailId);
        String name = user.getName();
        String bloodGroup = user.getBloodGroup();
        int totalDonated = user.getTotalDonated();
        totalDonated = totalDonated + pint;
        user.setTotalDonated(totalDonated);

        return "User: " + name + " has donated a total of" + totalDonated;
    }

    public String receive(String emailId, int pint) {
        User user = userRepository.findByEmailId(emailId);
        String name = user.getName();
        String bloodGroup = user.getBloodGroup();
        int totalReceived = user.getTotalDonated();
        totalReceived = totalReceived - pint;
        user.setTotalDonated(totalReceived);
        return "User: " + name + " has received a total of"  + totalReceived;
    }
}
