package com.example.jwtWithDatabase.dao;

import com.example.jwtWithDatabase.modal.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicRepository extends JpaRepository<Person, Integer> {
}
