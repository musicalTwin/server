package it.musicaltwin.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.musicaltwin.demo.entities.Genders;
import it.musicaltwin.demo.entities.InterestedIn;
import it.musicaltwin.demo.entities.Users;
import it.musicaltwin.demo.services.GendersService;
import it.musicaltwin.demo.services.InterestedInService;
import it.musicaltwin.demo.services.UserService;

@RestController
@RequestMapping(path = "api/v1/interested-in")
public class InterestedInController {

    private final InterestedInService intrestedInService;
    private final UserService usersService;
    private final GendersService gendersService;

    @Autowired
    public InterestedInController(InterestedInService intrestedInService, UserService usersService,
            GendersService gendersService) {
        this.intrestedInService = intrestedInService;
        this.usersService = usersService;
        this.gendersService = gendersService;
    }

    @GetMapping
    public List<InterestedIn> getAll() {
        return intrestedInService.findAll();
    }

    @PostMapping
    public void addIntrested(@RequestBody InterestedIn body) {
        Optional<Users> user = usersService.getUserInfoById(body.getUser().getId());
        Optional<Genders> gender = gendersService.getById(body.getGender().getId());

        user.ifPresentOrElse(userObj -> {
            body.setUser(userObj);
            gender.ifPresentOrElse(genderObj -> {
                body.setGender(genderObj);
                intrestedInService.addObj(body);
            }, () -> {
                throw new IllegalStateException("The provided genderId doesn't exist");
            });
        }, () -> {
            throw new IllegalStateException("The provided userId doesn't exist");
        });

    }

}
