package com.example.jwtWithDatabase.controller;

import com.example.jwtWithDatabase.modal.Person;
import com.example.jwtWithDatabase.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BasicController {

    @Autowired
    private BasicService basicService;

    @PostMapping("/person")
    public int savePerson(@RequestBody Person person) {
        basicService.savePerson(person);
        return person.getId();
    }

    @GetMapping("/persons")
    public List<Person> findAll() {
        return basicService.findAll();
    }

}
