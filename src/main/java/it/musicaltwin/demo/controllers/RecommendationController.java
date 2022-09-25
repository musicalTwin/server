package it.musicaltwin.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import it.musicaltwin.demo.Utils;
import it.musicaltwin.demo.entities.Cards;
import it.musicaltwin.demo.entities.Genders;
import it.musicaltwin.demo.entities.InterestedIn;
import it.musicaltwin.demo.entities.Users;
import it.musicaltwin.demo.services.CardsService;
import it.musicaltwin.demo.services.InterestedInService;
import it.musicaltwin.demo.services.MatchService;
import it.musicaltwin.demo.services.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "api/v1/recommendation")
public class RecommendationController {

    private final UserService userService;
    private final CardsService cardsService;
    private final InterestedInService interestedInService;
    private final MatchService matchService;

    private final UsersGenresController usersGenresController;

    @Autowired
    public RecommendationController(
            UserService userService,
            UsersGenresController usersGenresController,
            CardsService cardsService,
            InterestedInService interestedInService,
            MatchService matchService) {
        this.userService = userService;
        this.usersGenresController = usersGenresController;
        this.cardsService = cardsService;
        this.interestedInService = interestedInService;
        this.matchService = matchService;
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
        Map<Cards, Double> matchedPeople = new HashMap<Cards, Double>();
        Integer total = 0, present = 0;

        // For every user in the db other than themselves
        List<Users> users = userService.getUsers();
        for (Long i = 0L; i < users.size(); i++) {

            Users currUser = users.get(i.intValue());
            Cards userCard = cardsService.findByUserId(currUser.getId());
            List<Long> cardsId = matchService.findCardId(userId);

            if ((!currUser.getId().equals(userId)) &&
                intrestedInGenres.contains(currUser.getGender()) &&
                (!cardsId.contains(userCard.getId()))) {
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

                // Matched people sorted by matching
                matchedPeople.put(userCard, ((double) present / total) * 100);
            }
        }

        matchedPeople = Utils.sortByValue(matchedPeople);
        List<Cards> matchedPeopleList = new ArrayList<Cards>(matchedPeople.keySet());        
        Collections.reverse(matchedPeopleList);
        
        return matchedPeopleList;
    }
}