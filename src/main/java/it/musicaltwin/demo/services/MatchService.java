package it.musicaltwin.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.musicaltwin.demo.entities.Cards;
import it.musicaltwin.demo.entities.Matches;
import it.musicaltwin.demo.entities.Users;
import it.musicaltwin.demo.repositories.MatchRepository;

@Service
public class MatchService {
    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void createMatch(Matches matches) {
        Optional<Matches> optionalMatch = matchRepository.findMatch(matches.getCard().getId(),
                matches.getUser().getId());
        if (!optionalMatch.isPresent()) {
            matchRepository.save(matches);
        }
    }

    public List<Long> findCardId(String userId) {
        return matchRepository.findCardId(userId);
    }

    public boolean test(String userId, Long cardId) {
        return matchRepository.aaa(userId, cardId).isEmpty();
    }

    public Boolean checkIfAlreadyMatched(Cards currUserCard, Cards userCard) {
        Users currUser = currUserCard.getUser();
        Users user = userCard.getUser();

        List<Matches> matchList = matchRepository.checkIfAlreadyMatched(
                currUserCard.getId(),
                userCard.getId(),
                currUser.getId(),
                user.getId());
        return matchList.size() > 1;

    }

}