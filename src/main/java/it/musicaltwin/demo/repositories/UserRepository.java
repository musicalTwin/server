package it.musicaltwin.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.musicaltwin.demo.entities.Users;

public interface UserRepository extends JpaRepository<Users, String> {
    
}