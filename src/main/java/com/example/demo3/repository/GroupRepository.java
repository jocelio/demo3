package com.example.demo3.repository;


import com.example.demo3.domain.federation.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByIdIn(List<Long> idList);

}
