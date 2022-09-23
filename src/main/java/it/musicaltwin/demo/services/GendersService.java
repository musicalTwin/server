package it.musicaltwin.demo.services;

import it.musicaltwin.demo.entities.Genders;
import it.musicaltwin.demo.repositories.GendersRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GendersService {

    private final GendersRepository gendersRepository;

    @Autowired
    public GendersService(GendersRepository gendersRepository) {
        this.gendersRepository = gendersRepository;
    }

    public Genders getByName(String name) {
        return gendersRepository.getByName(name).orElse(new Genders());
    }

    public List<Genders> getAllGenders() {
        return gendersRepository.findAll();
    }

    public Genders getById(Long id) {
        return gendersRepository.findById(id).orElse(new Genders());
    }

}
