package com.example.jwtWithDatabase.service;

import com.example.jwtWithDatabase.dao.BasicRepository;
import com.example.jwtWithDatabase.modal.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicService {

    @Autowired
    private BasicRepository basicRepository;

    public void savePerson(Person person) {
        basicRepository.save(person);
    }

    public List<Person> findAll() {
        return basicRepository.findAll();
    }

}
