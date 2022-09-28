package it.musicaltwin.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.musicaltwin.demo.entities.Matches;
import it.musicaltwin.demo.repositories.MatchRepository;

@Service
public class MatchService {
    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void createMatch(Matches matches) {
        matchRepository.save(matches);
    }

    public List<Long> findCardId(String userId) {
        return matchRepository.findCardId(userId);
    }

    public boolean test(String userId, Long cardId) {
        return matchRepository.aaa(userId, cardId).isEmpty();
    }

    // public Boolean checkIfAlreadyMatched(Cards currUserCard, Cards userCard) {
    // var giovanni = matchRepository.checkIfAlreadyMatched(currUserCard.getId(),
    // currUserCard.getUser().getId(),
    // userCard.getId(), userCard.getUser().getId());
    // return !giovanni.isEmpty();

    // }
    
}