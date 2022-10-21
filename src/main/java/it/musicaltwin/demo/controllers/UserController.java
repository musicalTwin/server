package it.musicaltwin.demo.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.musicaltwin.demo.Utils;
import it.musicaltwin.demo.entities.Cards;
import it.musicaltwin.demo.entities.Genders;
import it.musicaltwin.demo.entities.Genres;
import it.musicaltwin.demo.entities.InterestedIn;
import it.musicaltwin.demo.entities.Users;
import it.musicaltwin.demo.entities.UsersGenres;
import it.musicaltwin.demo.services.CardsService;
import it.musicaltwin.demo.services.GendersService;
import it.musicaltwin.demo.services.UserService;
import it.musicaltwin.demo.services.UsersArtistsService;
import it.musicaltwin.demo.services.GenresService;
import it.musicaltwin.demo.services.InterestedInService;
import it.musicaltwin.demo.services.UsersGenresService;
import it.musicaltwin.demo.services.UsersSongService;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;
    private final GendersService gendersService;
    private final CardsService cardsService;
    private final GenresService genresService;
    private final UsersGenresService usersGenresService;
    private final InterestedInService interestedInService;
    private final UsersArtistsService usersArtistsService;
    private final UsersSongService usersSongService;

    @Autowired
    public UserController(UserService userService, GendersService gendersService, CardsService cardsService,
            GenresService genresService, UsersGenresService usersGenresService,
            InterestedInService interestedInService,
            UsersArtistsService usersArtistsService,
            UsersSongService usersSongService) {
        this.userService = userService;
        this.gendersService = gendersService;
        this.cardsService = cardsService;
        this.genresService = genresService;
        this.usersGenresService = usersGenresService;
        this.interestedInService = interestedInService;
        this.usersArtistsService = usersArtistsService;
        this.usersSongService = usersSongService;
    }

    // @Autowired
    // public UserController(UserService userService, GendersService gendersService,
    // CardsService cardsService) {
    // this.userService = userService;
    // this.gendersService = gendersService;
    // this.cardsService = cardsService;
    // }

    @GetMapping
    public List<Users> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "generate")
    public void generateUser() {

        for (Integer i = 0; i < 399; i++) {

            String id = Utils.randomString(25);
            Genders gender = gendersService.getById(Utils.randomGenderId(5,
                    1)).orElse(new Genders());
            String username = Utils.randomName(gender.getId());

            Users newUser = new Users(id, username, gender);
            addUserToDatabase(newUser);

            for (Integer j = 0; j < 20; j++) {

                if (j < 3) {
                    Genders IntrestedInGender = gendersService.getById(Utils.randomGenderId(5,
                            1)).orElse(new Genders());
                    InterestedIn interestedIngender = new InterestedIn(newUser, IntrestedInGender);
                    interestedInService.addObj(interestedIngender);

                }

                Long genreId = Utils.randomGenderId(5957, 1);
                Genres genre = genresService.findById(genreId);

                UsersGenres usersGenres = new UsersGenres(newUser, genre);
                usersGenresService.addToDatabase(usersGenres);

                // Random top artists
                Utils.randId("./sample_data/artists-unique.json", 3969, id, usersArtistsService);

                // Random top songs
                Utils.randId("./sample_data/songs-unique.json", 13154, id, usersSongService);
            }
            System.out.println("Aggiunti " + (i + 1) + " utenti");
            System.out.println(username);
        }
    }

    @GetMapping(path = "{userId}")
    public Optional<Users> getUserById(@PathVariable("userId") String userId) {
        return userService.getUserInfoById(userId);
    }

    @PostMapping
    public void addUserToDatabase(@RequestBody Users user) {
        Optional<Genders> genderObj = gendersService.getById(user.getGender().getId());
        Cards userCard = new Cards(user);

        genderObj.ifPresentOrElse(gender -> {
            user.setGender(gender);
            userService.addUser(user);
            cardsService.addCard(userCard);
        }, () -> {
            throw new IllegalStateException("The gender ID is invalid.");
        });

    }

    @PatchMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") String userId,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Long genderId) {

        userService.updateUser(userId, username, genderId);
    }

    @GetMapping(path = "cls")
    public void clearScreen() throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
