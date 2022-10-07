package it.musicaltwin.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.musicaltwin.demo.entities.Users;
import it.musicaltwin.demo.entities.UsersArtists;
import it.musicaltwin.demo.services.UserService;
import it.musicaltwin.demo.services.UsersArtistsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "api/v1/users-artists")
public class UsersArtistsController {

    private final UsersArtistsService usersArtistsService;
    private final UserService userService;

    @Autowired
    public UsersArtistsController(
            UsersArtistsService usersArtistsService,
            UserService userService) {
        this.usersArtistsService = usersArtistsService;
        this.userService = userService;
    }

    @GetMapping(path = "{userId}")
    public List<String> getTopArtists(@PathVariable("userId") String userId) {
        return usersArtistsService.getTopArtists(userId);
    }

    @PostMapping
    public void addTopArtists(@RequestBody Map<String, List<String>> body) {
        String userId = body.get("id").get(0);
        Users user = userService.getUserInfoById(userId).orElse(null);

        if (user != null) {

            if (usersArtistsService.checkIfUserInDb(user)) {
                usersArtistsService.removeUserFromDatabase(user);
            }

            System.out.println("Qua ci arriviamo");
            List<String> aritsts = body.get("artists");
            UsersArtists usersArtists;
            for (String artist : aritsts) {
                usersArtists = new UsersArtists(userId, artist);
                usersArtistsService.addToDatabase(usersArtists);
            }
        }
    }
}
