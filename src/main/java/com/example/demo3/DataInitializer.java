package com.example.demo3;


import com.example.demo3.repository.GroupRepository;
import com.example.demo3.repository.RuleRepository;
import com.example.demo3.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {

//        Rule roleUser = ruleRepository.save(Rule.builder().name("ROLE_USER").build());
//        Rule roleAdmin = ruleRepository.save(Rule.builder().name("ROLE_ADMIN").build());
//
//        Group groupUser = groupRepository.save(Group.builder().name("USERS").rules(asList(roleUser)).build());
//        Group groupAdmin = groupRepository.save(Group.builder().name("ADMINS").rules(asList(roleUser, roleAdmin)).build());
//
//        this.userRepository.save(User.builder()
//                .username("user")
//                .password(this.passwordEncoder.encode("password"))
//                .groups(asList(groupUser))
//                .build()
//        );
//
//        this.userRepository.save(User.builder()
//                .username("admin")
//                .password(this.passwordEncoder.encode("password"))
//                .groups(asList(groupAdmin))
//                .build()
//        );
//
//        log.debug("printing all userRepository...");
//        this.userRepository.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}