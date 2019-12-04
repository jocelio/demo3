package com.example.demo3.rest.tests;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class HelloPublicResource {

    public Boolean switchy = false;

    @RequestMapping(value = "/switch", method = GET)
    public ResponseEntity<Map<String, Boolean>> listar()
    {
        Map<String, Boolean> switchMap = new HashMap<>();

        switchMap.put("switchStatus", switchy);

        return new ResponseEntity<Map<String, Boolean>>(switchMap, OK);
    }

    @PutMapping("/switch")
    public ResponseEntity<Map<String, Boolean>> update(@RequestBody Boolean value)
    {

        switchy = value;
        Map<String, Boolean> switchMap = new HashMap<>();

        switchMap.put("switchStatus", switchy);

        return new ResponseEntity<Map<String, Boolean>>(switchMap, OK);
    }
}
