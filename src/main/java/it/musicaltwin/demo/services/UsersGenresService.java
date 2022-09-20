package it.musicaltwin.demo.services;

import it.musicaltwin.demo.entities.UsersGenres;
import it.musicaltwin.demo.repositories.UsersGenresRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersGenresService {

    private final UsersGenresRepository usersGenresRepository;

    @Autowired
    public UsersGenresService (UsersGenresRepository usersGenresRepository) {
        this.usersGenresRepository = usersGenresRepository;
    }

    public List<UsersGenres> getListenedGenres(String userId) {
        return usersGenresRepository.findListenedGenres(userId);
    }
}
