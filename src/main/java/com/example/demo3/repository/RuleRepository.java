package com.example.demo3.repository;


import com.example.demo3.domain.federation.Group;
import com.example.demo3.domain.federation.Rule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RuleRepository extends JpaRepository<Rule, Long> {

}
