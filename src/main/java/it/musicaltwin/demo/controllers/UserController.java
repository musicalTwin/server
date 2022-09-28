package it.musicaltwin.demo.controllers;


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

import it.musicaltwin.demo.entities.Cards;
import it.musicaltwin.demo.entities.Genders;
import it.musicaltwin.demo.entities.Users;
import it.musicaltwin.demo.services.CardsService;
import it.musicaltwin.demo.services.GendersService;
import it.musicaltwin.demo.services.GenresService;
import it.musicaltwin.demo.services.UserService;
import it.musicaltwin.demo.services.UsersGenresService;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;
    private final GendersService gendersService;
    private final CardsService cardsService;
    private final GenresService genresService;
    private final UsersGenresService usersGenresService;

    @Autowired
    public UserController(UserService userService, GendersService gendersService, CardsService cardsService,
            GenresService genresService, UsersGenresService usersGenresService) {
        this.userService = userService;
        this.gendersService = gendersService;
        this.cardsService = cardsService;
        this.genresService = genresService;
        this.usersGenresService = usersGenresService;
    }

    @GetMapping
    public List<Users> getUsers() {
        return userService.getUsers();
    }

    // @GetMapping(path = "generate")
    // public void generateUser() {
    // for (Integer i = 0; i < 499; i++) {

    // String id = Utils.randomString(25);
    // String username = Utils.randomString(6);
    // Genders gender = gendersService.getById(Utils.randomGenderId(5,
    // 1)).orElse(new Genders());

    // Users newUser = new Users(id, username, gender);
    // addUserToDatabase(newUser);

    // for (Integer j = 0; j < 20; j++) {

    // Long genreId = Utils.randomGenderId(5957, 1);
    // Genres genre = genresService.findById(genreId);

    // UsersGenres usersGenres = new UsersGenres(newUser, genre);
    // usersGenresService.addToDatabase(usersGenres);
    // }
    // }

    // }

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

}
