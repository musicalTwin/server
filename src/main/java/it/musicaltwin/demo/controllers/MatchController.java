package it.musicaltwin.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.musicaltwin.demo.entities.Cards;
import it.musicaltwin.demo.entities.Matches;
import it.musicaltwin.demo.services.CardsService;
import it.musicaltwin.demo.services.MatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "api/v1/match")
public class MatchController {
    private final MatchService matchService;
    private final CardsService cardsService;

    @Autowired
    public MatchController(MatchService matchService, CardsService cardsService) {
        this.matchService = matchService;
        this.cardsService = cardsService;
    }

    @PostMapping
    public void match(@RequestBody Matches matches) {
        matchService.createMatch(matches);
    }

    @GetMapping(value = "/check-match")
    public Boolean checkIfMatched(@RequestParam String userId1, @RequestParam String userId2) {
        Cards card1 = cardsService.findByUserId(userId1);
        Cards card2 = cardsService.findByUserId(userId2);

        return matchService.checkIfAlreadyMatched(card1, card2);
    }

}