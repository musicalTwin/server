package it.musicaltwin.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.musicaltwin.demo.entities.Genders;
import it.musicaltwin.demo.entities.Users;
import it.musicaltwin.demo.services.GendersService;
import it.musicaltwin.demo.services.UserService;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;
    private final GendersService gendersService;

    @Autowired
    public UserController(UserService userService, GendersService gendersService) {
        this.userService = userService;
        this.gendersService = gendersService;
    }

    @GetMapping
    public List<Users> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "{userId}")
    public Optional<Users> getUserById(@PathVariable("userId") String userId) {
        return userService.getUserInfoById(userId);
    }

    @PostMapping
    public void addUserToDatabase(@RequestBody Users user) {
        Optional<Genders> genderObj = gendersService.getById(user.getGender().getId());
        genderObj.ifPresentOrElse(gender -> {
            user.setGender(gender);
            userService.addUser(user);
        }, () -> {
            throw new IllegalStateException("The gender ID is invalid.");
        });

    }

}
