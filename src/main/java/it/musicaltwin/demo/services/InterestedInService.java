package it.musicaltwin.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.musicaltwin.demo.entities.InterestedIn;
import it.musicaltwin.demo.repositories.InterestedInRepository;

@Service
public class InterestedInService {
    private InterestedInRepository interestedInRepository;

    @Autowired
    public InterestedInService(InterestedInRepository interestedInRepository) {
        this.interestedInRepository = interestedInRepository;
    }

    public List<InterestedIn> findAll() {
        return interestedInRepository.findAll();
    }

    public void addObj(InterestedIn body) {
        interestedInRepository.save(body);
    }

    public List<InterestedIn> findByUserId(String userId) {
        return interestedInRepository.findByUserId(userId).orElse(new ArrayList<InterestedIn>());
    }

    public void removeObj(InterestedIn i) {
        interestedInRepository.delete(i);
    }

    public void removeAllByUserId(String id) {
        interestedInRepository.deleteByUserId(id);
    }

}