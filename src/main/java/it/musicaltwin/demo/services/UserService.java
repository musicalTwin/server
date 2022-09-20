package it.musicaltwin.demo.services;

import it.musicaltwin.demo.entities.Users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.musicaltwin.demo.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserInfoById(String userId) {
        return userRepository.findById(userId);
    }
}
