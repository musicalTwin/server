package it.musicaltwin.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import it.musicaltwin.demo.entities.Cards;
import it.musicaltwin.demo.entities.Genders;
import it.musicaltwin.demo.entities.Genres;
import it.musicaltwin.demo.entities.InterestedIn;
import it.musicaltwin.demo.entities.Users;
import it.musicaltwin.demo.services.CardsService;
import it.musicaltwin.demo.services.GenresService;
import it.musicaltwin.demo.services.InterestedInService;
import it.musicaltwin.demo.services.UserService;
import it.musicaltwin.demo.services.UsersGenresService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "api/v1/recommendation")
public class RecommendationController {

    private final UsersGenresService usersGenresService;
    private final UserService userService;
    private final GenresService genresService;
    private final CardsService cardsService;
    private final InterestedInService interestedInService;

    private final UsersGenresController usersGenresController;

    @Autowired
    public RecommendationController(
            UsersGenresService usersGenresService,
            UserService userService,
            GenresService genresService,
            UsersGenresController usersGenresController, CardsService cardsService,
            InterestedInService interestedInService) {
        this.usersGenresService = usersGenresService;
        this.userService = userService;
        this.genresService = genresService;
        this.usersGenresController = usersGenresController;
        this.cardsService = cardsService;
        this.interestedInService = interestedInService;
    }

    @GetMapping(path = "{userId}")
    public List<Cards> getRecommendation(@PathVariable("userId") String userId) {

        List<InterestedIn> intrestedIn = interestedInService.findByUserId(userId);
        List<Genders> intrestedInGenres = new ArrayList<Genders>();
        for (var gender : intrestedIn) {
            intrestedInGenres.add(gender.getGender());
        }

        // List of genres that the user who started the search listen to
        List<String> searcherListenedGenres = usersGenresController.getListenedGenres(userId);

        // List of genres listened by every other people (1 user at a time)
        List<String> searchedPersonGenres = new ArrayList<String>();

        // Eventual list of cards of people with similar musical taste
        List<Cards> matchedPeople = new ArrayList<Cards>();
        Integer total = 0, present = 0;

        // For every user in the db other than themselves
        List<Users> users = userService.getUsers();
        for (Long i = 0L; i < users.size(); i++) {
            Users currUser = users.get(i.intValue());
            if (!currUser.getId().equals(userId) && intrestedInGenres.contains(currUser.getGender())) {
                // Getting listened genres of a user
                searchedPersonGenres = usersGenresController
                        .getListenedGenres(currUser.getId());

                // Calculating how much the lists of genres matches
                total = searcherListenedGenres.size();
                present = 0;
                for (Long j = 0L; j < total; j++) {
                    if (searchedPersonGenres.contains(searcherListenedGenres.get(j.intValue()))) {
                        present++;
                    }
                }

                // If the match is higher than 80% add the user to the recommendation list
                if (((float) present / total) * 100 >= 6.00) {
                    Cards userCard = cardsService.findByUserId(currUser.getId());
                    matchedPeople.add(userCard);
                }
            }
        }
        return matchedPeople;
    }
}