package it.musicaltwin.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.musicaltwin.demo.entities.Users;
import it.musicaltwin.demo.entities.UsersSongs;
import it.musicaltwin.demo.services.UserService;
import it.musicaltwin.demo.services.UsersSongService;

@RestController
@RequestMapping(path = "api/v1/users-songs")
public class UsersSongsController {
    
    private final UsersSongService usersSongService;
    private final UserService userService;

    @Autowired
    public UsersSongsController(
            UsersSongService usersSongService,
            UserService userService) {
        this.usersSongService = usersSongService;
        this.userService = userService;
    }

    @GetMapping(path = "{userId}")
    public List<String> getTopArtists(@PathVariable("userId") String userId) {
        return usersSongService.getTopSongs(userId);
    }

    @PostMapping
    public void addTopSongs(@RequestBody Map<String, List<String>> body) {
        String userId = body.get("id").get(0);
        Users user = userService.getUserInfoById(userId).orElse(null);

        if (user != null) {

            if (usersSongService.checkIfUserInDb(user)) {
                usersSongService.removeUserFromDatabase(user);
            }

            List<String> songs = body.get("songs");
            UsersSongs usersSongs;
            for (String song : songs) {
                usersSongs = new UsersSongs(userId, song);
                usersSongService.addToDatabase(usersSongs);
            }
        }
    }

}