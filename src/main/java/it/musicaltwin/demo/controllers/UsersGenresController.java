package it.musicaltwin.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.musicaltwin.demo.entities.Genres;
import it.musicaltwin.demo.entities.Users;
import it.musicaltwin.demo.entities.UsersGenres;
import it.musicaltwin.demo.services.GenresService;
import it.musicaltwin.demo.services.UserService;
import it.musicaltwin.demo.services.UsersGenresService;

@RestController
@RequestMapping(path = "api/v1/user-genres")
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
    public Map<String, String> getListenedGenres(@PathVariable("userId") String userId) {
        List<UsersGenres> genres = usersGenresService.getListenedGenres(userId);
        HashMap<String, String> formattedGenre = new HashMap<String, String>();
        
        int j = 0;
        for(int i = 0; i < genres.size(); i++) {
            j++;
            formattedGenre.put("Genre name " + j, genres.get(i).getGenre().getGenreName().toString());
        }

        return formattedGenre;
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
            for (var genreName : genres) {
                Genres genre = genresService.findByName(genreName);
                System.out.println(genre);
                UsersGenres usersGenres = new UsersGenres(user, genre);
                usersGenresService.addToDatabase(usersGenres);
            }
        }

    }

}
