package it.musicaltwin.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.musicaltwin.demo.services.MatchService;

@RestController
@RequestMapping(path = "api/v1/find-match")
public class MatchController {
    private final MatchService matchService;
    private final UsersGenresController usersGenresController;

    @Autowired
    public MatchController(MatchService matchService, UsersGenresController usersGenresController) {
        this.matchService = matchService;
        this.usersGenresController = usersGenresController;
    }
    

    // @PostMapping(path = "{userId}")
    // public String test(@PathVariable("userId") String userId) {

    // }

}