package it.musicaltwin.demo.services;

import it.musicaltwin.demo.entities.Genders;
import it.musicaltwin.demo.entities.Users;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.musicaltwin.demo.repositories.GendersRepository;
import it.musicaltwin.demo.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final GendersRepository gendersRepository;

    @Autowired
    public UserService(UserRepository userRepository, GendersRepository gendersRepository) {
        this.userRepository = userRepository;
        this.gendersRepository = gendersRepository;
    }

    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserInfoById(String userId) {
        return userRepository.findById(userId);
    }

    public void addUser(Users user) {
        Optional<Users> userObj = userRepository.findById(user.getId());
        if (userObj.isEmpty()) {
            userRepository.save(user);
        } else {
            System.out.println(user.getId() + " esisteva già.");
        }
    }

    @Transactional
    public void updateUser(String userId, String username, Long genderId) {
        Users userObj = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("The user with this id does not exist."));
        if (username != null && username.length() >= 0) {
            userObj.setUsername(username);
        }

        if (genderId != null) {
            Genders gender = gendersRepository.findById(genderId)
                    .orElseThrow(() -> new IllegalStateException("The gender with this id does not exist."));
            userObj.setGender(gender);
        }
    }
}
