package it.musicaltwin.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.musicaltwin.demo.entities.Cards;
import it.musicaltwin.demo.repositories.CardsRepository;

@Service
public class CardsService {
    private final CardsRepository cardsRepository;

    @Autowired
    public CardsService(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    public void addCard(Cards userCard) {
        cardsRepository.save(userCard);
    }

}
