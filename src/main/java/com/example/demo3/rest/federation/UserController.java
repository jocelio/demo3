package com.example.demo3.rest.federation;


import com.example.demo3.domain.federation.User;
import com.example.demo3.domain.federation.Group;
import com.example.demo3.repository.GroupRepository;
import com.example.demo3.repository.UserRepository;
import com.example.demo3.repository.UserRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.*;


@RestController()
@RequestMapping("${prefix}/v1/users")
public class UserController {

    @Autowired
    UserRepository usersRepository;

    @Autowired
    UserRuleRepository userRuleRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping()
    public ResponseEntity getAll() {
        return ResponseEntity.status(OK).body(usersRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(OK).body(usersRepository.getOne(id));
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        List<Long> groupsId = user.getGroups().stream().map(Group::getId).collect(toList());
        List<Group> groups = groupRepository.findByIdIn(groupsId);
        user.setGroups(groups);

        user.getUserRules().stream().forEach(ur -> ur.setUser(user));
        User savedUser = usersRepository.save(user);

        return ResponseEntity.status(CREATED).body(savedUser);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id, @RequestBody User user) {
        user.setId(id);

        List<Long> groupsId = user.getGroups().stream().map(Group::getId).collect(toList());
        List<Group> groups = groupRepository.findByIdIn(groupsId);
        user.setGroups(groups);

        user.getUserRules().stream().forEach(ur -> ur.setUser(user));

        return ResponseEntity.status(CREATED).body(usersRepository.save(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        usersRepository.deleteById(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

}
