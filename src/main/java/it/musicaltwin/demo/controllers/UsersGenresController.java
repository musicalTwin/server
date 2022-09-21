package it.musicaltwin.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.musicaltwin.demo.entities.UsersGenres;
import it.musicaltwin.demo.services.UsersGenresService;

@RestController
@RequestMapping(path = "api/v1/user-genres")
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

    @PostMapping
    public void addListenedGenres(@RequestBody Map<String, List<String>> body) {
        String id = body.get("id").get(0);
        List<String> genres = body.get("genres");

        genres.forEach(null);
        System.out.println(id);
    }

}
