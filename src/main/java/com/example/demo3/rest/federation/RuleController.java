package com.example.demo3.rest.federation;


import com.example.demo3.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.*;


@RestController()
@RequestMapping("api/v1/rules")
public class RuleController {

    @Autowired
    RuleRepository ruleRepository;

    @GetMapping()
    public ResponseEntity getAll() {
        return ResponseEntity.status(OK).body(ruleRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(OK).body(ruleRepository.getOne(id));
    }

}
