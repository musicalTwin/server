package it.musicaltwin.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.musicaltwin.demo.entities.UsersGenres;
import it.musicaltwin.demo.services.UsersGenresService;


@RestController
@RequestMapping(path = "api/v1/user-listened-genres")
public class UsersGenresController {

    @Autowired
    private final UsersGenresService usersGenresService;

    public UsersGenresController(UsersGenresService usersGenresService) {
        this.usersGenresService = usersGenresService;
    }

    @GetMapping(path = "{userId}")
    public List<UsersGenres> getListenedGenres(@PathVariable("userId") String userId) {
        return usersGenresService.getListenedGenres(userId);
    }

}
