package com.example.montaneralbertomyikea.repositories;

import com.example.montaneralbertomyikea.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
/*
    List<User> readAll();
*/
}
