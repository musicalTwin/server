package it.musicaltwin.demo.services;

import it.musicaltwin.demo.entities.Genders;
import it.musicaltwin.demo.repositories.GendersRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GendersService {

    private final GendersRepository gendersRepository;

    @Autowired
    public GendersService(GendersRepository gendersRepository) {
        this.gendersRepository = gendersRepository;
    }

    public Optional<Genders> getByName(String name) {
        return gendersRepository.getByName(name);
    }

    public List<Genders> getAllGenders() {
        return gendersRepository.findAll();
    }

    public Optional<Genders> getById(Long id) {
        return gendersRepository.findById(id);
    }

}
