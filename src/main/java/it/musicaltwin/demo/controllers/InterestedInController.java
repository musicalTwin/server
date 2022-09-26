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
import it.musicaltwin.demo.entities.InterestedIn;
import it.musicaltwin.demo.entities.Users;
import it.musicaltwin.demo.services.GendersService;
import it.musicaltwin.demo.services.InterestedInService;
import it.musicaltwin.demo.services.UserService;

@RestController
@RequestMapping(path = "api/v1/interested-in")
public class InterestedInController {

    private final InterestedInService interestedInService;
    private final UserService usersService;
    private final GendersService gendersService;

    @Autowired
    public InterestedInController(InterestedInService intrestedInService, UserService usersService,
            GendersService gendersService) {
        this.interestedInService = intrestedInService;
        this.usersService = usersService;
        this.gendersService = gendersService;
    }

    @GetMapping
    public List<InterestedIn> getAll() {
        return interestedInService.findAll();
    }

    @PostMapping
    public void addIntrested(@RequestBody List<InterestedIn> body1) {
        Optional<Users> tmpObj = usersService.getUserInfoById(body1.get(0).getUser().getId());
        tmpObj.ifPresent(user -> {
            interestedInService.removeAllByUserId(user.getId());
        });

        for (var i : body1) {
            Optional<Users> userObj = usersService.getUserInfoById(i.getUser().getId());
            Optional<Genders> gendersObj = gendersService.getById(i.getGender().getId());

            userObj.ifPresentOrElse(userobj -> {
                gendersObj.ifPresentOrElse(gender -> {
                    InterestedIn interestedIn = new InterestedIn(userobj, gender);
                    interestedInService.addObj(interestedIn);
                }, () -> {
                    throw new IllegalStateException("The provided gender id doesn't exist");
                });
            }, () -> {
                throw new IllegalStateException("The provided user id is invalid");
            });
        }

    }

    @GetMapping("{userId}")
    public List<InterestedIn> getByUserId(@PathVariable("userId") String userId) {
        return interestedInService.findByUserId(userId);
    }

}
