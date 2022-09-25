package it.musicaltwin.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.musicaltwin.demo.entities.Matches;
import it.musicaltwin.demo.services.MatchService;

@RestController
@RequestMapping(path = "api/v1/match")
public class MatchController {
    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public void test(@RequestBody Matches matches) {
        matchService.createMatch(matches);
    }

}