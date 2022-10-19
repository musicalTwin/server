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
import it.musicaltwin.demo.services.UsersArtistsService;
import it.musicaltwin.demo.services.UsersSongService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final UsersArtistsService usersArtistsService;
    private final UsersSongService usersSongService;

    private final UsersGenresController usersGenresController;

    @Autowired
    public RecommendationController(
            UserService userService,
            UsersGenresController usersGenresController,
            CardsService cardsService,
            InterestedInService interestedInService,
            MatchService matchService,
            UsersArtistsService usersArtistsService,
            UsersSongService usersSongService) {
        this.userService = userService;
        this.usersGenresController = usersGenresController;
        this.cardsService = cardsService;
        this.interestedInService = interestedInService;
        this.matchService = matchService;
        this.usersArtistsService = usersArtistsService;
        this.usersSongService = usersSongService;
    }

    @GetMapping(path = "{userId}")
    public List<Cards> getRecommendation(@PathVariable("userId") String userId) {

        List<InterestedIn> intrestedIn = interestedInService.findByUserId(userId);
        List<Genders> intrestedInGenders = new ArrayList<Genders>();
        List<Cards> requestSender = new ArrayList<Cards>();

        // Genders in which the user is interested in
        for (var gender : intrestedIn) {
            intrestedInGenders.add(gender.getGender());
        }

        // List of genres that the user who started the search listen to
        List<String> searcherListenedGenres = usersGenresController.getListenedGenres(userId);

        // List of top artists of the user who started the search
        List<String> searcherTopArtists = usersArtistsService.getTopArtists(userId);

        // List of top songs of the user who started the search
        List<String> searcherTopSongs = usersSongService.getTopSongs(userId);

        // List of genres listened by every other user (1 user at a time)
        List<String> searchedUserGenres = new ArrayList<String>();

        // List of top artists by every other user (1 user at a time)
        List<String> searchedUserArtists = new ArrayList<String>();

        // List of genres listened by every other user (1 user at a time)
        List<String> searchedUserSongs = new ArrayList<String>();

        // Eventual list of cards of user with similar musical taste
        Map<Cards, Double> matchedUser = new HashMap<Cards, Double>();

        Integer total = 0, tempTotal = 0, present = 0;
        double average = 0;
        short diff = 0;

        // For every user in the db other than themselves
        List<Users> users = userService.getUsers();
        for (Long i = 0L; i < users.size(); i++) {

            Users currUser = users.get(i.intValue());
            Cards currUserCard = cardsService.findByUserId(currUser.getId());
            List<Long> cardsId = matchService.findCardId(userId);

            // If the current user is not yourself
            if ((!currUser.getId().equals(userId)) &&
            // If the current user is the gender you are interested in
                    intrestedInGenders.contains(currUser.getGender()) &&
                    // If you don't have already matched the user
                    (!cardsId.contains(currUserCard.getId()))) {
                // If nobody sent a request to text you
                if (matchService.test(currUser.getId(), cardsService.findByUserId(userId).getId())) {

                    present = 0;
                    average = 0;

                    // Getting listened genres of current user
                    searchedUserGenres = usersGenresController.getListenedGenres(currUser.getId());

                    // Getting top artists of current user
                    searchedUserArtists = usersArtistsService.getTopArtists(currUser.getId());

                    // Getting top songs of current user
                    searchedUserSongs = usersSongService.getTopSongs(currUser.getId());

                    // Genres list of the 2 user of same size
                    if (searcherListenedGenres.size() > searchedUserGenres.size()) {
                        diff = (short) (searcherListenedGenres.size() - searchedUserGenres.size());

                        for (short k = 0; k < diff; k++) {
                            searchedUserGenres.add("");
                        }

                    } else {
                        diff = (short) (searchedUserGenres.size() - searcherListenedGenres.size());

                        for (short k = 0; k < diff; k++) {
                            searcherListenedGenres.add("");
                        }
                    }

                    // Calculating how much the lists of genres match
                    tempTotal = searcherListenedGenres.size();
                    for (Long j = 0L; j < tempTotal; j++, total++) {
                        if (searchedUserGenres.contains(searcherListenedGenres.get(j.intValue()))) {
                            present++;
                        }
                    }
                    average = ((double) present / tempTotal) * 100;

                    // Calculating how much the lists of top artists match
                    tempTotal = searchedUserArtists.size();
                    present = 0;
                    for (Long j = 0L; j < tempTotal; j++, total++) {
                        try {
                            if (searchedUserArtists.contains(searcherTopArtists.get(j.intValue()))) {
                                present++;
                            }

                        } catch (Exception e) {
                            System.out.println("Artists\nUser id " + userId + "\nJ: " + j + "\n");
                        }
                    }
                    average += ((double) present / tempTotal) * 100;

                    // Calculating how much the lists of top songs match
                    tempTotal = searchedUserSongs.size();
                    present = 0;
                    for (Long j = 0L; j < tempTotal; j++, total++) {
                        try {
                            if (searchedUserSongs.contains(searcherTopSongs.get(j.intValue()))) {
                                present++;
                            }
                        } catch (Exception e) {
                            System.out.println("Songs\nUser id " + userId + "\nJ: " + j + "\n");
                        }
                    }
                    average += ((double) present / tempTotal) * 100;

                    // Matched user with percentage of matching
                    matchedUser.put(currUserCard, (double) average);
                }
                // If somebody sent a request to text you
                else {
                    requestSender.add(currUserCard);
                }
            }
        }

        // Sorting the HashMap by the percentage of matching and casting to List
        matchedUser = Utils.sortByValue(matchedUser);
        List<Cards> matchedUserList = new ArrayList<Cards>(matchedUser.keySet());
        Collections.reverse(matchedUserList);

        // Add user that request to text you at the beginnig of the list
        for (Integer i = 0; i < requestSender.size(); i++) {
            matchedUserList.add(i, requestSender.get(i));
        }

        return matchedUserList;
    }
}