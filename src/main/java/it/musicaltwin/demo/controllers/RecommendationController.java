package it.musicaltwin.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import it.musicaltwin.demo.entities.Cards;
import it.musicaltwin.demo.services.GenresService;
import it.musicaltwin.demo.services.UserService;
import it.musicaltwin.demo.services.UsersGenresService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "api/v1/recommendation")
public class RecommendationController {

    private final UsersGenresService usersGenresService;
    private final UserService userService;
    private final GenresService genresService;

    @Autowired
    public RecommendationController(
        UsersGenresService usersGenresService,
        UserService userService,
        GenresService genresService) {
            this.usersGenresService = usersGenresService;
            this.userService = userService;
            this.genresService = genresService;
        }

    @PostMapping(path = "{userId}")
    public List<Cards>getRecommendation(@PathVariable("userId") String userId) {

        UsersGenresController usersGenresController = new UsersGenresController(this.usersGenresService, this.userService, this.genresService);
        List<String> searcherListenedGenres = usersGenresController.getListenedGenres(userId); // List of genres that the user who started the search listen to
        List<String> searchedPersonGenres = new ArrayList<String>(); // List of genres listened by every other people (1 user at a time)
        List<Cards> matchedPeople = new ArrayList<Cards>(); // Eventual list of cards of people with similar musical taste
        Integer total = 0, present = 0;

        // For every user in the db other than themselves
        for(Long i  = 0L; i < userService.getUsers().size(); i++) {
            if(!userService.getUsers().get(i.intValue()).getId().equals(userId)) {
                // Getting listened genres of a user
                searchedPersonGenres = usersGenresController.getListenedGenres(userService.getUsers().get(i.intValue()).getId());

                // Calculating how much the lists of genres matches
                total = 0; present = 0;
                for(Long j = 0L; j < searcherListenedGenres.size(); j++, total++) {
                    if(searchedPersonGenres.contains(searcherListenedGenres.get(j.intValue()))) {
                        present++;
                    }
                }

                // If the match is higher than 80% add the user to the recommendation list
                if((present / total) * 100 >= 80) {
                    Cards userCard = new Cards(userService.getUsers().get(i.intValue()));
                    matchedPeople.add(userCard);
                }
            }
        }
        System.out.println(matchedPeople);
        return matchedPeople;
    }
}