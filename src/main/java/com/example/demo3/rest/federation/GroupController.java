package com.example.demo3.rest.federation;


import com.example.demo3.domain.federation.Group;
import com.example.demo3.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;


@RestController()
@RequestMapping("api/v1/groups")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @GetMapping()
    public ResponseEntity getAll() {
        return ResponseEntity.status(OK).body(groupRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(OK).body(groupRepository.getOne(id));
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Group group) {
        group.setId(null);
        return ResponseEntity.status(CREATED).body(groupRepository.save(group));
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id, @RequestBody Group group) {
        group.setId(id);
        return ResponseEntity.status(CREATED).body(groupRepository.save(group));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        groupRepository.deleteById(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

}
