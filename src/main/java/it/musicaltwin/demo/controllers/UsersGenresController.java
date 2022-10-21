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

import it.musicaltwin.demo.Utils;
import it.musicaltwin.demo.entities.Genres;
import it.musicaltwin.demo.entities.Users;
import it.musicaltwin.demo.entities.UsersGenres;
import it.musicaltwin.demo.services.GenresService;
import it.musicaltwin.demo.services.UserService;
import it.musicaltwin.demo.services.UsersGenresService;

@RestController
@RequestMapping(path = "api/v1/users-genres")
public class UsersGenresController {

    private final UsersGenresService usersGenresService;
    private final UserService userService;
    private final GenresService genresService;

    @Autowired
    public UsersGenresController(
            UsersGenresService usersGenresService,
            UserService userService,
            GenresService genresService) {
        this.usersGenresService = usersGenresService;
        this.userService = userService;
        this.genresService = genresService;
    }

    @GetMapping(path = "{userId}")
    public List<UsersGenres> getListenedGenders(@PathVariable("userId") String userId) {
        return usersGenresService.getListenedGenres(userId);
    }

    public List<String> getListenedGenresId(@PathVariable("userId") String userId) {
        return usersGenresService.getListenedGenresId(userId);
    }

    @PostMapping
    public void addListenedGenres(@RequestBody Map<String, List<String>> body) {
        String userId = body.get("id").get(0);
        Users user = userService.getUserInfoById(userId).orElse(null);

        if (user != null) {

            if (usersGenresService.checkIfUserInDb(user)) {
                usersGenresService.removeUserFromDatabase(user);
            }

            List<String> genres = body.get("genres");
            genres = Utils.sortL(genres); // Sorting list (in a loser way L)

            // Removing duplicates from genres
            genres = Utils.removeDuplicates(genres);

            for (var genreName : genres) {
                Genres genre = genresService.findByName(genreName);
                UsersGenres usersGenres = new UsersGenres(user, genre);
                usersGenresService.addToDatabase(usersGenres);
            }
        }
    }
}
