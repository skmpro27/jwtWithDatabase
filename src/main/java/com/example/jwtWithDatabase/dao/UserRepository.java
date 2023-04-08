package com.example.jwtWithDatabase.dao;

import com.example.jwtWithDatabase.modal.DaoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<DaoUser, Integer> {
    DaoUser findByUsername(String username);
}
