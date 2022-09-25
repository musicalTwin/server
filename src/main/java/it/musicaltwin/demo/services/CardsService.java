package it.musicaltwin.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.musicaltwin.demo.entities.Cards;
import it.musicaltwin.demo.entities.Users;
import it.musicaltwin.demo.repositories.CardsRepository;

@Service
public class CardsService {
    private final CardsRepository cardsRepository;

    @Autowired
    public CardsService(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    public void addCard(Cards userCard) {
        Users user = userCard.getUser();
        Optional<Cards> cardObj = cardsRepository.findByUserId(user.getId());
        if (cardObj.isEmpty()) {
            cardsRepository.save(userCard);
        } else {
            System.out.println("La card di " + user.getId() + " esisteva già.");
        }
    }

    public Cards findByUserId(String id) {
        return cardsRepository.findByUserId(id).orElse(new Cards());
    }

}
