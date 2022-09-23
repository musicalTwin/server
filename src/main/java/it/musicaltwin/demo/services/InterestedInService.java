package it.musicaltwin.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.musicaltwin.demo.entities.InterestedIn;
import it.musicaltwin.demo.repositories.InterestedInRepository;

@Service
public class InterestedInService {
    private InterestedInRepository intrestedInRepository;

    @Autowired
    public InterestedInService(InterestedInRepository intrestedInRepository) {
        this.intrestedInRepository = intrestedInRepository;
    }

    public List<InterestedIn> findAll() {
        return intrestedInRepository.findAll();
    }

    public void addObj(InterestedIn body) {
        intrestedInRepository.save(body);
    }

    public List<InterestedIn> findByUserId(String userId) {
        return intrestedInRepository.findByUserId(userId);
    }

}